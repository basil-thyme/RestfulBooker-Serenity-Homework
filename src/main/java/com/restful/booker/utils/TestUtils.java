package com.restful.booker.utils;

import net.bytebuddy.utility.RandomString;

import java.util.Random;

public class TestUtils {
    public static String getRandomValue() {
        Random random = new Random();
        int randomInt = random.nextInt(100000);
        return Integer.toString(randomInt);
    }

    public static String getRandomText() {
        String randomText;
        RandomString random = new RandomString(10);
        return randomText = random.nextString();
    }
}
