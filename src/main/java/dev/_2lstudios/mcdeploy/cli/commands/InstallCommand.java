package dev._2lstudios.mcdeploy.cli.commands;

import java.io.File;
import java.util.concurrent.Callable;

import dev._2lstudios.mcdeploy.MCDeploy;
import dev._2lstudios.mcdeploy.cli.Logger;
import dev._2lstudios.mcdeploy.errors.MCDException;
import dev._2lstudios.mcdeploy.software.RunOptions;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "install", description = "Install a server artifact.")
public class InstallCommand implements Callable<Integer> {
    @Option(description = "Directory where install server", names = { "-d", "--directory" })
    private File cwd;

    @Option(description = "Java runtime binary", defaultValue = "java", names = { "-j", "--java" })
    private String java;

    @Option(description = "Server software", defaultValue = "vanilla", names = { "-s", "--software" })
    private String software;

    @Option(description = "Server version", defaultValue = "latest", names = { "-v", "--version" })
    private String version;

    @Override
    public Integer call() {
        MCDeploy mcd = new MCDeploy();
        RunOptions options = new RunOptions().setCWD(this.cwd).setJava(this.java);
        String artifactId = this.software + "@" + this.version;

        try {
            Logger.info("Fetching for versions...");
            mcd.prepare();

            Logger.info("Preparing for download artifact: " + artifactId);
            mcd.install(artifactId, options);
            Logger.info("Installed successfully.");

        } catch (MCDException e) {
            Logger.crit(e.getMessage());
        }

        return 0;
    }
}
