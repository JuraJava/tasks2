/**
 * Реализовать функцию поиска Person по значению name.
 * Даны класс:
 * class Person {
 *     String name;
 *     Integer age;
 * }
 * и сигнатура метода:
 * Optional<Person> findPersonByName(List<Person> persons, String name) {
 *     return null;
 * }
 * Требуется реализовать метод, который возвращает первого человека с заданным именем, обёрнутого в Optional,
 * или Optional.empty(), если такого человека нет.
 */
package org.example.main3;

import java.util.List;
import java.util.Optional;

public class Main3 {
    public static void main(String[] args) {
        // 1. Создаем список людей
        List<Person3> person3s = List.of(
                new Person3("Alice", 25),
                new Person3("Bob", 30),
                new Person3("Bob", 35),
                new Person3("Bob", 40),
                new Person3("Bob", 45),
                new Person3("Charlie", 35)
        );
        // 2. Ищем человека по имени
        String searchName = "Bob";
        Optional<Person3> result = findPersonByName(person3s, searchName);

        // 3. Выводим результат разными способами:
        // Способ 2: С использованием ifPresent (более идиоматично)
        System.out.print("Результат поиска: ");
        result.ifPresentOrElse(
                person3 -> System.out.println(person3),
                () -> System.out.println("Не найден")
        );

    }

    static Optional<Person3> findPersonByName(List<Person3> person3s, String name) {
        return person3s.stream()
                .filter(p -> p.name != null && p.name.equals(name))
                .findFirst();
    }
}
