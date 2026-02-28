package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Subsets {

    private static List<List<Integer>> generate(int[] nums) {
        int n = nums.length;
        // Создаем поток по количеству подмножеств (2^n)
        return IntStream.range(0, 1 << n)
                .mapToObj(mask ->
                        // Для каждой маски собираем элементы, где соответствующий бит установлен
                        IntStream.range(0, n)
                                .filter(i -> (mask & (1 << i)) != 0)
                                .mapToObj(i -> nums[i])
                                .collect(Collectors.toList())
                )
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3};
        List<List<Integer>> result = generate(nums);

        // Проверка наличия подмножеств
        System.out.println(result.contains(new ArrayList<Integer>()));
        System.out.println(result.contains(new ArrayList<>(List.of(1))));
        System.out.println(result.contains(new ArrayList<>(List.of(1, 2))));
        System.out.println(result.contains(new ArrayList<>(List.of(1, 3))));
        System.out.println(result.contains(new ArrayList<>(List.of(1, 2, 3))));
        System.out.println(result.contains(new ArrayList<>(List.of(2))));
        System.out.println(result.contains(new ArrayList<>(List.of(2, 3))));
        System.out.println(result.contains(new ArrayList<>(List.of(3))));
    }
}
