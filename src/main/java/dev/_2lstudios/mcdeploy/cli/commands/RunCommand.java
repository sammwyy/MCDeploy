package dev._2lstudios.mcdeploy.cli.commands;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

import dev._2lstudios.mcdeploy.MCDeploy;
import dev._2lstudios.mcdeploy.cli.Logger;
import dev._2lstudios.mcdeploy.errors.MCDException;
import dev._2lstudios.mcdeploy.software.RunOptions;
import dev._2lstudios.mcdeploy.utils.FileUtils;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "run", description = "Run server in current directory.")
public class RunCommand implements Callable<Integer> {
    @Option(description = "Enable/disable console input and output", defaultValue = "true", names = { "-c",
            "--console" })
    private boolean console;

    @Option(description = "Directory where install server", names = { "-d", "--directory" })
    private File cwd;

    @Option(description = "Accept EULA", defaultValue = "false", names = { "-e", "--eula" })
    private boolean eula;

    @Option(description = "Jar file", defaultValue = "", names = { "-f", "--file" })
    private String file;

    @Option(description = "Java runtime binary", defaultValue = "java", names = { "-j", "--java" })
    private String java;

    @Override
    public Integer call() throws IOException {
        MCDeploy mcd = new MCDeploy();
        RunOptions options = new RunOptions().setCWD(this.cwd).setEula(this.eula).setJava(this.java);

        File dotMCDFile = new File(options.cwd, ".mcdeploy");
        if (this.file == null || this.file.isEmpty()) {
            this.file = FileUtils.readFile(dotMCDFile);
        }

        String banner = "Launching artifact:\n";
        banner += "> Console: " + this.console + "\n";
        banner += "> Eula: " + this.eula + "\n";
        banner += "> Jar file: " + this.file + "\n";
        banner += "> JRE binary: " + options.java + "\n";
        banner += "> Working directory: " + options.cwd.getAbsolutePath() + "\n";
        Logger.info(banner);

        try {
            int exitCode = mcd.runLocal(this.file, this.console ? System.out::print : null, options);
            Logger.info("Process exited with code: " + exitCode);
        } catch (MCDException e) {
            Logger.crit(e.getMessage());
        }

        return 0;
    }
}
