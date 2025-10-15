package org.example;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main2 {
    public static void main(String[] args) {
        int[] arr = {0, 0, -7, -7, -7, 1, 2, 5, 3, 1, 2};
        int n = getFirstUnique(arr);
        System.out.println("Первое уникальное значение: " + n);

    }

    private static int getFirstUnique(int arr[]) {
        Map<Integer, Integer> countMap = new LinkedHashMap<>();

        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return -1; // если уникальных нет
    }

}