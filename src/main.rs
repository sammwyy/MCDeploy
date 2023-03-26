use std::{
    env,
    fs::{self, File},
    io::{self, BufRead, BufReader, Write},
    path::PathBuf,
    process::Command,
    str::FromStr,
};

use clap::Parser;
use cli::Cli;
use commands::{Commands, Install, Run};
use reqwest::blocking::get;

pub mod cli;
pub mod commands;
pub mod server_software;

fn get_current_working_dir() -> std::io::Result<PathBuf> {
    env::current_dir()
}

fn read_launcher_file(path: PathBuf) -> String {
    let file = path.join(".mcdeploy");
    let content = fs::read_to_string(file).expect("Should have been able to read the file");
    return content;
}

fn write_launcher_file(path: PathBuf, jar_name: &String) -> std::io::Result<()> {
    let mut file = File::create(path.join(".mcdeploy"))?;
    file.write_all(jar_name.as_bytes())?;
    Ok(())
}

fn download_file(path: &String, url: &String) {
    let mut resp = get(url).expect("[MCDeploy] Failed downloading file.");
    let mut out = File::create(path).expect("[MCDeploy] Failed to create file.");
    io::copy(&mut resp, &mut out).expect("[MCDeploy] Failed to copy content");
    println!("[MCDeploy] Finished.");
}

fn func_install(input: &Install) {
    let dir;

    if input.dir.is_some() {
        dir = PathBuf::from_str(input.dir.clone().unwrap().as_str()).unwrap();
    } else {
        dir = get_current_working_dir().unwrap();
    }

    let software_arg = input.software.clone().unwrap_or("vanilla".to_string());
    let mut version_arg = input.version.clone().unwrap_or("latest".to_string());
    println!(
        "[MCDeploy] Preparing download of {} version {}",
        software_arg, version_arg
    );

    let mut versions = server_software::read_from_url_default().unwrap();
    let software = versions.get_mut(&software_arg);

    if software.is_none() {
        println!("[MCDeploy] Error: Unknown software {}", software_arg);
        return;
    }

    let software = software.unwrap();

    if version_arg == "latest" {
        version_arg = software.versions.clone().get(0).unwrap().to_string();
    }

    if !software.versions.contains(&version_arg) {
        println!(
            "[MCDeploy] Error: software {} doesn't contains version {}\nAvailable versions:",
            software_arg, version_arg
        );
        for v in software.versions.clone() {
            println!(" - {}", v);
        }
        return;
    }

    let out;

    if input.out.is_some() {
        out = input.out.clone().unwrap();
    } else {
        out = format!("{}-{}.jar", software_arg, version_arg);
    }

    let target_dir = dir
        .join(out.clone())
        .to_str()
        .unwrap()
        .to_string()
        .replace("/", "\\");

    println!("[MCDeploy] Downloading file: {}", out);

    if software.resources.is_some() {
        let resources = software.resources.clone().unwrap();
        let version_url = resources.get(&version_arg).unwrap();
        download_file(&target_dir, version_url);
    } else if software.url.is_some() {
        let url = software.url.clone().unwrap();
        let version_url = &url.replace("{version}", &version_arg);
        download_file(&target_dir, version_url);
    }

    write_launcher_file(dir, &out).unwrap();
}

fn func_run(input: &Run) {
    let mut dir = get_current_working_dir().unwrap();

    if input.dir.is_some() {
        dir = PathBuf::from_str(input.dir.clone().unwrap().as_str()).unwrap();
    }

    let file = input
        .file
        .clone()
        .unwrap_or(read_launcher_file(dir.clone()));
    let java = input.java.clone().unwrap_or("java".to_string());

    println!("[MCDeploy] Running server:");
    println!(" - DIR: {}", dir.clone().to_str().unwrap());
    println!(" - JRE: {}", java.clone());
    println!(" - JAR: {}", file.clone());
    println!("");

    let mut result = Command::new(java)
        .current_dir(dir)
        .arg("-jar")
        .arg(file)
        .spawn()
        .expect("failed to execute process");

    if result.stdout.is_some() {
        let reader = BufReader::new(result.stdout.as_mut().unwrap());

        reader
            .lines()
            .filter_map(|line| line.ok())
            .for_each(|line| println!("{}", line));
    }

    if result.stderr.is_some() {
        let reader = BufReader::new(result.stderr.as_mut().unwrap());

        reader
            .lines()
            .filter_map(|line| line.ok())
            .for_each(|line| println!("{}", line));
    }

    let exit = result.wait();
    println!(
        "[MCDeploy] Process end with status: {}",
        exit.unwrap().code().unwrap()
    );
}

fn main() {
    let cli = Cli::parse();

    match &cli.command {
        Commands::Install(install) => func_install(install),
        Commands::Run(run) => func_run(run),
    }
}
