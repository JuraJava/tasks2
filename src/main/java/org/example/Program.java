/**
 * ЗАДАЧА
 * Выбрать самого старшего человека каждого пола из списка, используя Stream API
 * Разрешено  писать  код только в методе takeOnlyOldestInEachGender
 */
package org.example;

import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class Program {
    private static class Person {

        public enum Gender {
            MALE, FEMALE, APACHE_ATTACK_HELICOPTER, OTHER
        }

        private final String name;
        private final int age;
        private final Gender gender;

        private Person(String name, Integer age, Gender gender) {
            this.name = name;
            this.age = age;
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }

        public Gender getGender() {
            return gender;
        }

        @Override
        public String toString() {
            return "P(%s %d %s)".formatted(name, age, gender);
        }
    }

    private static List<Person> takeOnlyOldestInEachGender(List<Person> persons) {
        return persons.stream()
                .collect(Collectors.toMap(
                        Person::getGender,
                        Function.identity(),
                        (p1, p2) -> p1.getAge() > p2.getAge() ? p1 : p2
                ))
                .values()
                .stream()
                .toList();

    }

    public static void main(String[] args) {
        /* Initial data */
        List<Person> people = List.of(
                new Person("Debra", 25, Person.Gender.FEMALE),
                new Person("XC-1923", 20, Person.Gender.APACHE_ATTACK_HELICOPTER),
                new Person("Hose", 21, Person.Gender.MALE),
                new Person("Lion", 24, Person.Gender.MALE),
                new Person("Matilda", 22, Person.Gender.FEMALE),
                new Person("RP-1321", 23, Person.Gender.APACHE_ATTACK_HELICOPTER),
                new Person("PK-5231", 26, Person.Gender.APACHE_ATTACK_HELICOPTER),
                new Person("Ignat", 18, Person.Gender.MALE),
                new Person("Masha", 19, Person.Gender.FEMALE)
        );
        /* Verification */
        test(people);
    }

    private static void test(List<Person> people) {
        List<Person> finalSelection = takeOnlyOldestInEachGender(people);
        List<String> oldestOfEachGender = List.of("Lion", "Debra", "PK-5231");
        boolean allSelected = new HashSet<>(finalSelection.stream().map(Person::getName).toList()).containsAll(oldestOfEachGender);
        boolean sizeIsEqual = (oldestOfEachGender.size() == finalSelection.size());
        System.out.println(allSelected && sizeIsEqual ? "Passed :)" : "Failed :(");
    }
}

