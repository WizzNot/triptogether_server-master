package com.example.buysell;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {
    public static String hashPassword(String password) {
        try {
            // Создаем экземпляр MessageDigest с алгоритмом SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Преобразуем пароль в байтовый массив
            byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
            // Вычисляем хэш пароля
            byte[] hashBytes = md.digest(passwordBytes);
            // Преобразуем байтовый массив хэша в шестнадцатеричное представление
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            // Возвращаем шестнадцатеричное представление хэша
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Обработка ошибки, если алгоритм хэширования не найден
        }
        return null;
    }
    String password = "my_password";
    String hashedPassword = hashPassword(password);
}