package com.ifmo.epampractice.utilities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public final class SecurityUtilities {

    private SecurityUtilities() {
    }

    public static String generateSalt() {
        final int leftLimit = 48;
        final int rightLimit = 122;
        final int targetStringLength = 15;
        Random random = new Random();
        final int leftABorder = 57;
        final int rightABorder = 65;
        final int leftNBorder =  90;
        final int rightNBorder = 97;

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= leftABorder || i >= rightABorder) && (i <= leftNBorder || i >= rightNBorder))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }


    public static String generateHash(final String password, final String salt) {
        String md5 = "";
        if (null == password) {
            return null;
        }
        String passWithSalt = password + salt;

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(passWithSalt.getBytes(), 0, passWithSalt.length());
            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Cannot to get md5 hash from input string", e);
        }
        return md5;
    }
}
