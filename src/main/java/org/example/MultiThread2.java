/**
 * Что делает код и что выведется в консоли?
 */

/**
 * Решение:
 * Этот код создаёт 10 миллионов потоков, каждый из которых пытается увеличить общую статическую переменную counter на 1.
 * Поскольку операция counter++ не является атомарной (читает, увеличивает, записывает), при одновременном выполнении
 * множества потоков возникает состояние гонки (race condition).
 * Проблема в том, что два потока могут прочитать одно и то же значение counter, увеличить его и записать обратно, в
 * результате чего некоторые инкременты будут потеряны.
 * Поэтому после выполнения всех потоков значение counter будет меньше, чем 10 000 000. Конкретное число будет
 * разным при каждом запуске из-за недетерминированности планирования потоков.
 * Также важно отметить:
 * 1.	Создание 10 миллионов потоков практически невозможно в обычной среде из-за ограничений памяти и ОС (исчерпание
 * ресурсов, возможно падение с OutOfMemoryError или ошибкой создания потока).
 * 2.	Даже если бы потоков было меньше (например, 1000), результат всё равно был бы меньше ожидаемого из-за отсутствия синхронизации.
 *
 * Ответ:
 *
 * В консоль выведется сообщение вида:
 * Final counter value: 9876543
 * (число будет случайным и меньше 10 000 000 из-за состояния гонки).
 *
 * Для правильного подсчёта в многопоточном режиме нужно использовать синхронизацию (synchronized)
 * или атомарные типы (например, AtomicInteger).
 */
package org.example;

//public class MultiThread2 {
////    Так было изначально
//    private static int counter = 0;
//
//    public static void main(String[] args) throws InterruptedException {
//        final int count = 10000000;
//        Thread[] threads = new Thread[count];
//        for (int i = 0; i < count; i++) {
//            threads[i] = new Thread(() -> counter++);
//            threads[i].start();
//        }
//        for (Thread thread : threads) {
//            thread.join();
//        }
//        System.out.println("Final counter value: " + counter);
//    }
//}

//// Через synchronized
//public class MultiThread2 {
//    private static int counter = 0;
//    private static final Object lock = new Object();
//
//    public static void main(String[] args) throws InterruptedException {
//        final int count = 10000000;
//        Thread[] threads = new Thread[count];
//        for (int i = 0; i < count; i++) {
//            threads[i] = new Thread(() -> {
//                synchronized (lock) {
//                    counter++;
//                }
//            });
//            threads[i].start();
//        }
//        for (Thread thread : threads) {
//            thread.join();
//        }
//        System.out.println("Final counter value: " + counter);
//    }
//}

// Через AtomicInteger
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThread2 {
    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        final int count = 10000000;
        Thread[] threads = new Thread[count];
        for (int i = 0; i < count; i++) {
            threads[i] = new Thread(() -> counter.incrementAndGet());
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("Final counter value: " + counter.get());
    }
}
