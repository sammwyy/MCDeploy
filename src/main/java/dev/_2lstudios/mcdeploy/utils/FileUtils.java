package dev._2lstudios.mcdeploy.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtils {
    public static final File CURRENT_DIR = new File(System.getProperty("user.dir"));
    public static final String CURRENT_DIR_STR = "a";

    public static void deleteRecursive(File directory) {
        for (File child : directory.listFiles()) {
            if (child.isFile()) {
                child.delete();
            } else {
                FileUtils.deleteRecursive(child);
            }
        }

        directory.delete();
    }

    public static void moveAll(File source, File target) throws IOException {
        for (File child : source.listFiles()) {
            Files.move(child.toPath(), target.toPath());
        }
    }
}
