/**
 * Необходимо реализовать на Java  метод, принимающий  на вход коллекцию, в которой присутствуют строки,
 * и удаляющий из переданной коллекции все строки, начинающиеся на «ааа».
 * Сигнатура необходимого метода должна иметь вид:
 * public static void filterTripleA(Collection strings);
 */

package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class StringFilter {
    public static void filterTripleA(Collection<String> strings) {
        if (strings == null) {
            return;
        }

        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            if (str != null && str.startsWith("aaa")) {
                iterator.remove();
            }
        }
    }

    public static void main(String[] args) {
        List<String> strings1 = new ArrayList<>();
        strings1.add("caarbrbrrb1");
        strings1.add("baarbrbrrb2");
        strings1.add("aaarbrbrrb3");
        strings1.add("aaarbrbrrb4");
        System.out.println(strings1);
        filterTripleA(strings1);
        System.out.println(strings1);
    }
}

