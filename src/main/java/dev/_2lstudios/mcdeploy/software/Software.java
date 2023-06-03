package dev._2lstudios.mcdeploy.software;

import java.util.List;
import java.util.Map;

public class Software {
    protected String id;
    private SoftwareInstaller installer;
    private Map<String, String> resources;
    private String url;
    private String type;
    public List<String> versions;

    public String getID() {
        return this.id;
    }

    public SoftwareInstaller getInstaller() {
        return this.installer;
    }

    public SoftwareArtifact getArtifact(String version) {
        if (version == null || version.equals("latest") || version.isEmpty()) {
            version = this.versions.get(0);
        } else if (!this.versions.contains(version)) {
            return null;
        }

        if (this.resources != null && this.resources.containsKey(version)) {
            String artifactUrl = this.resources.get(version);
            return new SoftwareArtifact(artifactUrl, this, version);
        } else {
            String artifactUrl = this.url.replace("{version}", version);
            return new SoftwareArtifact(artifactUrl, this, version);
        }
    }

    public SoftwareArtifact getArtifact() {
        return this.getArtifact("latest");
    }

    public SoftwareType getType() {
        return SoftwareType.valueOf(this.type.toUpperCase());
    }
}
