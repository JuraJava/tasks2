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
        List<Person> persons = List.of(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Bob", 35),
                new Person("Bob", 40),
                new Person("Bob", 45),
                new Person("Charlie", 35)
        );
        // 2. Ищем человека по имени
        String searchName = "Bob";
        Optional<Person> result = findPersonByName(persons, searchName);

        // 3. Выводим результат разными способами:
        // Способ 2: С использованием ifPresent (более идиоматично)
        System.out.print("Результат поиска: ");
        result.ifPresentOrElse(
                person -> System.out.println(person),
                () -> System.out.println("Не найден")
        );

    }

    static Optional<Person> findPersonByName(List<Person> persons, String name) {
        return persons.stream()
                .filter(p -> p.name != null && p.name.equals(name))
                .findFirst();
    }
}
