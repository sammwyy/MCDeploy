package dev._2lstudios.mcdeploy.cli;

import java.util.concurrent.Callable;

import dev._2lstudios.mcdeploy.cli.commands.InstallCommand;
import dev._2lstudios.mcdeploy.cli.commands.RunCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        // Application name.
        name = "mcdeploy",
        // Application description
        description = "Download and deploy minecraft servers with one command.\n",
        // CLI subcommands
        subcommands = {
                InstallCommand.class,
                RunCommand.class,
        })
public class CLI implements Callable<Integer> {
    @Override
    public Integer call() {
        CommandLine.usage(this, System.out);
        return 0;
    }
}
