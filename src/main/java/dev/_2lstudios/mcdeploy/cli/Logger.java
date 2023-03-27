package dev._2lstudios.mcdeploy.cli;

public class Logger {
    static void print(String message) {
        System.out.println("[MCDeploy] " + message);
    }

    public static void crit(String message) {
        print("CRIT " + message);
    }

    public static void info(String message) {
        print("INFO " + message);
    }

    public static void warn(String message) {
        print("WARN " + message);
    }
}
