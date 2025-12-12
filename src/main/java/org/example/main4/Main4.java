package org.example.main4;

import java.util.ArrayList;
import java.util.List;

import static org.example.main4.Subsets.generate;


public class Main4 {
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
