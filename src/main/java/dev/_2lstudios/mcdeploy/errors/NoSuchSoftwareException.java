package dev._2lstudios.mcdeploy.errors;

public class NoSuchSoftwareException extends MCDException {
    private String software;

    public NoSuchSoftwareException(String software) {
        super("No such software named " + software);
        this.software = software;
    }

    public String getSoftware() {
        return this.software;
    }
}
