package dev._2lstudios.mcdeploy.errors;

public class MCDException extends Exception {
    public MCDException(String message) {
        super(message);
    }

    public MCDException(String message, Exception child) {
        super(message, child);
    }
}
