package dev._2lstudios.mcdeploy.cli;

import picocli.CommandLine;

public class CLILauncher {
    public static void main(String[] args) {
        new CommandLine(new CLI()).execute(args);
    }
}
