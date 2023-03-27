package dev._2lstudios.mcdeploy;

import java.io.IOException;

import dev._2lstudios.mcdeploy.errors.MCDException;
import dev._2lstudios.mcdeploy.errors.NoSuchSoftwareException;
import dev._2lstudios.mcdeploy.errors.NoSuchVersionException;
import dev._2lstudios.mcdeploy.errors.SoftwareFetchException;
import dev._2lstudios.mcdeploy.software.LocalSoftwareArtifact;
import dev._2lstudios.mcdeploy.software.RunOptions;
import dev._2lstudios.mcdeploy.software.Software;
import dev._2lstudios.mcdeploy.software.SoftwareArtifact;
import dev._2lstudios.mcdeploy.software.SoftwareManager;
import dev._2lstudios.mcdeploy.utils.StdListener;

public class MCDeploy {
    public static final String VERSION = "0.0.1";

    private SoftwareManager softwares;

    public MCDeploy() {
        this.softwares = new SoftwareManager();
    }

    public SoftwareManager getSoftwareManager() {
        return this.softwares;
    }

    public SoftwareArtifact getArtifact(String artifactId) throws NoSuchSoftwareException, NoSuchVersionException {
        String[] parts = artifactId.split("@");
        String softwareId = parts[0];
        String versionId = parts.length > 1 ? parts[1] : "latest";

        Software software = this.softwares.getSoftware(softwareId);
        if (software == null) {
            throw new NoSuchSoftwareException(softwareId);
        }

        SoftwareArtifact artifact = software.getArtifact(versionId);
        if (artifact == null) {
            throw new NoSuchVersionException(versionId);
        }

        return artifact;
    }

    public void install(String artifactId, RunOptions options) throws MCDException {
        SoftwareArtifact artifact = this.getArtifact(artifactId);
        try {
            artifact.install(options);
        } catch (IOException | InterruptedException e) {
            throw new MCDException("Error installing artifact: " + e.getLocalizedMessage(), e);
        }
    }

    public int runLocal(String jarFile, StdListener listener, RunOptions options) throws MCDException {
        SoftwareArtifact artifact = new LocalSoftwareArtifact(jarFile);
        try {
            return artifact.run(options, listener);
        } catch (IOException | InterruptedException e) {
            throw new MCDException("Error installing artifact: " + e.getLocalizedMessage(), e);
        }
    }

    public int runLocalAndPipe(String jarFile, RunOptions options) throws MCDException {
        return this.runLocal(jarFile, (chr) -> {
            System.out.print(chr);
        }, options);
    }

    public int run(String artifactId, StdListener listener, RunOptions options) throws MCDException {
        SoftwareArtifact artifact = this.getArtifact(artifactId);
        try {
            return artifact.run(options, listener);
        } catch (IOException | InterruptedException e) {
            throw new MCDException("Error installing artifact: " + e.getMessage(), e);
        }
    }

    public int runAndPipe(String artifactId, RunOptions options) throws MCDException {
        return this.run(artifactId, (chr) -> {
            System.out.print(chr);
        }, options);
    }

    public void prepare() throws SoftwareFetchException {
        this.softwares.download();
    }
}
