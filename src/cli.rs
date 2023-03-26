use clap::{command, Parser};

use crate::commands::Commands;

#[derive(Parser)]
#[command(author, version)]
#[command(
    about = "MCDeploy - Minecraft Server Deployment Utility.",
    long_about = "MCDeploy - Minecraft Server Deployment Utility."
)]
pub struct Cli {
    #[command(subcommand)]
    pub command: Commands,
}
