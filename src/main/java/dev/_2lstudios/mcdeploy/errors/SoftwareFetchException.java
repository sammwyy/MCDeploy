package dev._2lstudios.mcdeploy.errors;

public class SoftwareFetchException extends MCDException {
    public SoftwareFetchException(Exception child) {
        super("Cannot fetch software list.", child);
    }
}
