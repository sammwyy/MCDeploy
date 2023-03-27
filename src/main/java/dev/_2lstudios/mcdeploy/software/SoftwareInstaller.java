package dev._2lstudios.mcdeploy.software;

public class SoftwareInstaller {
    private String archive;
    private String execute;
    private String jar;
    private boolean temp;

    public String getArchive() {
        return this.archive;
    }

    public String getExecute() {
        return this.execute;
    }

    public String getJAR() {
        return this.jar;
    }

    public boolean isRunInTempDirectory() {
        return this.temp;
    }
}
