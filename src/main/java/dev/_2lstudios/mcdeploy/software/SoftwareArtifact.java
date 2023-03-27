package dev._2lstudios.mcdeploy.software;

import java.io.File;
import java.io.IOException;

import dev._2lstudios.mcdeploy.utils.DownloadUtils;
import dev._2lstudios.mcdeploy.utils.FileUtils;
import dev._2lstudios.mcdeploy.utils.ProcessUtils;
import dev._2lstudios.mcdeploy.utils.StdListener;

public class SoftwareArtifact {
    private String url;
    private Software software;
    private String version;

    public SoftwareArtifact(String url, Software software, String version) {
        this.url = url;
        this.software = software;
        this.version = version;
    }

    public String getID() {
        return this.software.getID() + "@" + this.version;
    }

    public String getJARName() {
        if (this.software.getInstaller() == null) {
            return this.software.getID() + "-" + this.version + ".jar";
        } else {
            return this.software.getInstaller().getJAR().replace("{version}", this.version);
        }
    }

    public void download(File directory, boolean ignoreExist) throws IOException {
        File finalFile = new File(directory, this.getJARName());
        DownloadUtils.download(this.url, finalFile, ignoreExist);
    }

    public void install(RunOptions options) throws IOException, InterruptedException {
        SoftwareInstaller installer = this.software.getInstaller();

        if (installer == null) {
            this.download(options.cwd, true);
        } else {
            File installerFile = DownloadUtils.downloadTemp(this.url);
            File tempDir = installerFile.getParentFile();

            String cmd = installer.getExecute().replace("{version}", version).replace("{java}", options.java);
            int status = ProcessUtils.runAndWait(cmd, tempDir);

            if (status == 0) {
                String archive = installer.getArchive();
                File archiveFile = new File(tempDir, archive);
                FileUtils.moveAll(archiveFile, options.cwd);
            }

            FileUtils.deleteRecursive(tempDir);
        }

        FileUtils.writeFile(new File(options.cwd, ".mcdeploy"), this.getJARName());
    }

    public int run(RunOptions options, StdListener listener) throws IOException, InterruptedException {
        String cmd = options.java + " -jar " + this.getJARName();
        File cwd = options.cwd;

        if (options.eula) {
            FileUtils.writeFile(new File(cwd, "eula.txt"), "eula=true");
        }

        return ProcessUtils.runAndWait(cmd, cwd, listener);
    }

    public void installAndRun(RunOptions options, StdListener listener) throws IOException, InterruptedException {
        this.install(options);
        this.run(options, listener);
    }
}
