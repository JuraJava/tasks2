package org.example.main4;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Subsets {

    static List<List<Integer>> generate(int[] nums) {
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
}
