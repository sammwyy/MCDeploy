package dev._2lstudios.mcdeploy.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

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
            File targetChild = new File(target, child.getName());
            Files.move(child.toPath(), targetChild.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public static String readFile(File target) throws IOException {
        return Files.readString(target.toPath());
    }

    public static void writeFile(File target, String content) throws IOException {
        Files.writeString(target.toPath(), content, StandardCharsets.UTF_8);
    }
}
