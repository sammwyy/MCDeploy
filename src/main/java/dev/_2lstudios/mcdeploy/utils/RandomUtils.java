package dev._2lstudios.mcdeploy.utils;

import java.util.Random;

public class RandomUtils {
    private static String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public static String randomString(int length) {
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < length) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}
