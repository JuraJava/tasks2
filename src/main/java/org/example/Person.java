/**
 * Дан следующий Java-класс:
 * class Person
 * Необходимо реализовать стандартный метод, осуществляющий сравнение 2-х объектов Person.
 */

package org.example;

import java.util.Objects;

class Person {
    String firstName;
    String lastName;
    Integer age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(age, person.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age);
    }

    public Person(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public static void main(String[] args) {
        Person person1 = new Person("Ivan", "Ivanov", 25);
        Person person2 = new Person("Ivan", "Ivanov", 26);
        System.out.println(person1.equals(person2));
    }
}

