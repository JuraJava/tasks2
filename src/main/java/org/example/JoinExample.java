package org.example;

public class JoinExample {
    public static void main(String[] args) throws InterruptedException {
        // 1. СОЗДАЁМ ЦЕЛЕВОЙ поток
        Thread targetThread = new Thread(() -> {
            try {
                Thread.sleep(2000); // Имитация работы
                System.out.println("ЦЕЛЕВОЙ поток завершил работу");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        targetThread.start(); // Запускаем ЦЕЛЕВОЙ поток

        System.out.println("ТЕКУЩИЙ поток (main) продолжает работу...");

        // 2. ТЕКУЩИЙ поток (main) вызывает join() для targetThread
        targetThread.join(); // ← ТЕКУЩИЙ поток ждёт завершения ЦЕЛЕВОГО

        System.out.println("ТЕКУЩИЙ поток (main) возобновил работу");
    }
}
