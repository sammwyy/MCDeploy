use clap::{Args, Subcommand};

#[derive(Subcommand)]
pub enum Commands {
    /// Run server on current directory.
    Run(Run),
    /// Install a server on current directory.
    Install(Install),
}

#[derive(Args)]
pub struct Run {
    #[arg(short = 'd', long = "directory")]
    pub dir: Option<String>,

    #[arg(short = 'f', long = "file")]
    pub file: Option<String>,

    #[arg(short = 'j', long = "java")]
    pub java: Option<String>,
}

#[derive(Args)]
pub struct Install {
    #[arg(short = 'd', long = "directory")]
    pub dir: Option<String>,

    #[arg(short = 'o', long = "out")]
    pub out: Option<String>,

    #[arg(short = 's', long = "software")]
    pub software: Option<String>,

    #[arg(short = 'v', long = "version")]
    pub version: Option<String>,
}
