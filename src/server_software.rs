use std::collections::HashMap;

use reqwest::blocking::Client;
use serde::{Deserialize, Serialize};
use thiserror::Error;

#[derive(Serialize, Deserialize)]
#[serde(rename_all(deserialize = "camelCase"))]
pub struct ServerSoftware {
    #[serde(rename = "type")]
    pub software_type: String,
    pub versions: Vec<String>,
    pub url: Option<String>,
    pub resources: Option<HashMap<String, String>>,
    pub installer: Option<ServerSoftwareInstaller>,
}

#[derive(Serialize, Deserialize)]
#[serde(rename_all(deserialize = "camelCase"))]
pub struct ServerSoftwareInstaller {
    pub temp: bool,
    pub execute: String,
    pub archive: String,
    pub jar: String,
}

#[derive(Error, Debug)]
pub enum ManifestError {
    #[error("{0}")]
    Request(#[from] reqwest::Error),

    #[error("{0}")]
    Json(#[from] serde_json::Error),
}

// https://sammwyy.github.io/minecraft-db/data/servers.json

pub fn read_manifest_from_str(
    string: &str,
) -> Result<HashMap<String, ServerSoftware>, ManifestError> {
    let manifest: HashMap<String, ServerSoftware> = serde_json::from_str(&string)?;
    return Ok(manifest);
}

pub fn read_from_url(url: &str) -> Result<HashMap<String, ServerSoftware>, ManifestError> {
    let client = Client::new();
    let response = client.get(url).send()?;

    let data: HashMap<String, ServerSoftware> = serde_json::from_reader(response)?;
    return Ok(data);
}

pub fn read_from_url_default() -> Result<HashMap<String, ServerSoftware>, ManifestError> {
    return read_from_url("https://sammwyy.github.io/minecraft-db/data/servers.json");
}
