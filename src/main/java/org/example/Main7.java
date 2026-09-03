package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main7 {
    public static void main(String[] args) {

        List<Long> numbers = new ArrayList<>();
        for (int i = 1; i <= 1000000; i++) {
            numbers.add((long) i);
        }

        long sum = sum(numbers);
        System.out.println("Сумма" + sum);
    }

    public static long sum(List<Long> numbers) {
        final int threads = 10;
        List<List<Long>> subLists = split(numbers, threads);

        ExecutorService executor = Executors.newFixedThreadPool(threads);

        List<Future<Long>> futures = new ArrayList<>();

        for (List<Long> subList : subLists) {
            Callable<Long> task = () -> {
                long subSum = 0;
                for (Long num : subList) {
                    subSum += num;
                }
                return subSum;
            };

            futures.add(executor.submit(task));
        }

        executor.shutdown();

        long totalSum = 0;
        for (Future<Long> future : futures) {
            try {
                totalSum += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return totalSum;
    }

    private static List<List<Long>> split(List<Long> numbers, int parts) {
        int size = numbers.size();
        int subListSize = (int) Math.ceil((double) size / parts);

        List<List<Long>> result = new ArrayList<>(parts);
        for (int i = 0; i < parts; i++) {
            int fromIndex = i * subListSize;
            if (fromIndex >= size) {
                result.add(Collections.emptyList());
            } else {
                int toIndex = Math.min((i + 1) * subListSize, size);
                List<Long> sublist = numbers.subList(fromIndex, toIndex);
                result.add(sublist);
            }
        }
        return result;
    }
}
