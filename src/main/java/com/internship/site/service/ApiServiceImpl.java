package com.internship.site.service;

import java.util.Random;

/**
 * Класс с доп. функциями к проекту
 */
public class ApiServiceImpl implements ApiService {
    /**
     * Генерация токена на основе рандома
     * @param len длина токена
     */
    public String generateToken(int len)  {
        String lets = "qwertyuiopasdfghjklzxcvbnm1234567890";
        String result = "";

        Random rand = new Random();

        for (int i = 0; i < len; ++i) {
            result += lets.charAt(rand.nextInt(lets.length()));
        }

        return result;
    }
}
