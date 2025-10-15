package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Main1 {
    public static void main(String[] args) {
        List<Map<String, Object>> getDecisions1 = Arrays.asList(
                Map.of("id", 20, "result", "approved"),
                Map.of("id", 30, "result", "approved"),
                Map.of("id", 23, "result", "approved")
        );

        List<Map<String, Object>> getDecisions2 = Arrays.asList(
                Map.of("id", 2, "result", "approved"),
                Map.of("id", 16, "result", "approved"),
                Map.of("id", 32, "result", "approved")
        );

        // Объединяем оба списка
        List<Map<String, Object>> all = new ArrayList<>();
        all.addAll(getDecisions1);
        all.addAll(getDecisions2);

        // Сортируем по id по возрастанию
        all.sort(Comparator.comparingInt(m -> (Integer) m.get("id")));

        // Получаем 3 последних заказа
        List<Map<String, Object>> last3 = all.subList(all.size() - 3, all.size());

        // Печатаем результат
        for (Map<String, Object> order : last3) {
            System.out.println(order);
        }
    }
}
