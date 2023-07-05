package com.opencart.managers;

import com.github.javafaker.Faker;

public class FakeDataManager {
    private static Faker fakerObject = new Faker();

    public static String getRandomEmail() {
        return fakerObject.internet().emailAddress();
    }

    public static String getRandomData(String fieldName) {
        if (fieldName!=null) {
            if (fieldName.toLowerCase().contains("email")) {
                return getRandomEmail();
            } else if (fieldName.toLowerCase().contains("name")) {
                return getRandomName();
            } else if (fieldName.toLowerCase().contains("pass") || fieldName.toLowerCase().contains("password")) {
                return getRandomPassword();
            }
        }


        return "";
    }

    public static String getRandomName() {
        return fakerObject.funnyName().name();
    }

    public static String getRandomPassword(int min, int max) {
        return fakerObject.internet().password(min, max);
    }

    public static String getRandomPassword() {
        return fakerObject.internet().password();
    }


}
