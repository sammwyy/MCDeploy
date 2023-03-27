package dev._2lstudios.mcdeploy.errors;

public class NoSuchVersionException extends MCDException {
    private String version;

    public NoSuchVersionException(String version) {
        super("No such version named " + version);
        this.version = version;
    }

    public String getVersion() {
        return this.version;
    }
}
