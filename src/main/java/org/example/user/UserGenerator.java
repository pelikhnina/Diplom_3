package org.example.user;

import java.util.Random;

public class UserGenerator {
    private static final Random random = new Random();

    private static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }

        return result.toString();
    }

    public User generateRandomUser() {
        String name = generateRandomString(5);  // Допустим, имя состоит из 5 символов
        String email = name.toLowerCase() + "@" + generateRandomString(5).toLowerCase() + ".com";
        String password = generateRandomString(6);  // Пароль длиной 6 символов

        return new User(email, password, name);
    }

}