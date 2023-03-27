package dev._2lstudios.mcdeploy.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class DownloadUtils {
    public static File download(String url, File target, boolean ignoreExist) throws IOException {
        if (target.exists() && !ignoreExist) {
            return target;
        } else {
            InputStream in = new URL(url).openStream();
            Files.copy(in, Paths.get(target.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
            return target;
        }
    }

    public static File downloadTemp(String url) throws IOException {
        String tmpdir = System.getProperty("java.io.tmpdir");
        File dir = new File(tmpdir, RandomUtils.randomString(16));
        dir.mkdirs();

        String fileName = new File(url).getName();
        File file = new File(dir, fileName);
        return DownloadUtils.download(url, file, false);
    }

    public static String readStringFromURL(String requestURL) throws IOException {
        try (Scanner scanner = new Scanner(new URL(requestURL).openStream(),
                StandardCharsets.UTF_8.toString())) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
    }
}
