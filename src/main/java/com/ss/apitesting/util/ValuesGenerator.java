package com.ss.apitesting.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Helps generating IDs, dates etc. for POST/PUT requests
 */
public class ValuesGenerator {

    private static Random generator = new Random();
    /**
     * Generates an integer ID in given range
     * @param min - minimal value (inclusive)
     * @param max - maximum value (exclusive)
     * @return an ID from range [min, max-1]
     */
    public static int generateId(int min, int max) {
        return generator.nextInt(max-min) + min;
    }

    /**
     * Generates an integer ID
     * @return an ID from range [100, 999]
     */
    public static int generateId() {
        return generateId(100, 1000);
    }

    /**
     * Generates current date
     */
    public static Date generateDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * Generates current date in predefined format
     */
    public static String generateDateString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = generateDate();
        return formatter.format(date);
    }
}
