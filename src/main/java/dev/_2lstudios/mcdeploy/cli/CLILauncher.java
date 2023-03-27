package dev._2lstudios.mcdeploy.cli;

import dev._2lstudios.mcdeploy.cli.commands.InstallCommand;

import picocli.CommandLine;

public class CLILauncher {
    public static void main(String[] args) {
        new CommandLine(new InstallCommand()).execute(args);
    }
}
