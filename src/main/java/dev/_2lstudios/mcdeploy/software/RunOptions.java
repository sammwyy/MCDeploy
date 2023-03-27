package dev._2lstudios.mcdeploy.software;

import java.io.File;

import dev._2lstudios.mcdeploy.utils.FileUtils;

public class RunOptions {
    public File cwd = FileUtils.CURRENT_DIR;
    public boolean eula = false;
    public String java = "java";

    public RunOptions setCWD(File cwd) {
        if (cwd != null) {
            this.cwd = cwd;
        }
        return this;
    }

    public RunOptions setEula(boolean eula) {
        this.eula = eula;
        return this;
    }

    public RunOptions setJava(String java) {
        if (java != null && !java.isEmpty()) {
            this.java = java;
        }
        return this;
    }
}
