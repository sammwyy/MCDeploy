package dev._2lstudios.mcdeploy.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessUtils {
    public static int runAndWait(String cmd, File cwd, StdListener listener) throws IOException, InterruptedException {
        Process proc = Runtime.getRuntime().exec(cmd, new String[] {}, cwd);

        if (listener != null) {
            try (InputStreamReader isr = new InputStreamReader(proc.getInputStream())) {
                int c;
                while ((c = isr.read()) >= 0) {
                    String value = String.valueOf((char) c);
                    listener.onReceive(value);
                }
            }
            return proc.exitValue();
        } else {
            return proc.waitFor();
        }
    }

    public static int runAndWait(String cmd, File cwd) throws IOException, InterruptedException {
        return runAndWait(cmd, cwd, null);
    }
}
