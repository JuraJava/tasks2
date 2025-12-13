/**
 * Что выведет метод exception()?
 */

package org.example;

import java.io.IOException;

public class Main6 {
    public static void main(String[] args) {
        exception();
    }

    private static void exception() {
        try (AutoCloseable a = createResources()) {
            throw new IllegalArgumentException("Исключение в try");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Перехватили исключение");
        }
    }

    private static AutoCloseable createResources() {
        return () -> {
            System.out.println("Начинаем закрывать ресурс");
            throw new IOException("Exception");
        };
    }

}
