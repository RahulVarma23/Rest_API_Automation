package org.javarevision.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {

    public static String generateRandomStringValue (int charCount){
        return RandomStringUtils.random(charCount, true, false);
    }

    public static int generateRandomIntValue (int charCount){
        return Integer.parseInt(RandomStringUtils.random(charCount, false, true));
    }

    public static String generateRandomAlphaNumericValue (int charCount){
        return RandomStringUtils.random(charCount, true, true);
    }
}
