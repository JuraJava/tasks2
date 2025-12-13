/**
 * Условие задачи:
 * Напишите метод, который генерирует все возможные подмножества (включая пустое множество) для заданного массива целых чисел.
 * Подмножества должны быть представлены в виде списка списков.
 * Пример для массива [1, 2, 3] метод должен вернуть все подмножества:
 *  [], [1], [2], [3], [1,2], [1,3], [2,3], [1,2,3]
 *
 * Решение:
 * Наиболее оптимальный подход — использование битовых масок.
 * Для массива длины n существует 2^n подмножеств. Каждое подмножество соответствует числу от 0 до 2^n - 1,
 * где бит числа указывает, входит ли элемент массива в подмножество.
 * Используем Stream API для генерации диапазона чисел-масок и преобразования каждой маски в список элементов.
 */

/**
 * Объяснение решения:
 * 1.	Идея битовых масок:
 * Если массив имеет n элементов, то всего подмножеств — 2^n.
 * Каждое подмножество можно представить двоичным числом длины n, где 1 в позиции i означает, что элемент nums[i] входит
 * в подмножество.
 * 2.	Генерация масок:
 * IntStream.range(0, 1 << n) создает поток чисел от 0 до 2^n - 1.
 * Выражение 1 << n эквивалентно 2^n.
 * 3.	Преобразование маски в подмножество:
 * Для каждой маски с помощью IntStream.range(0, n) проверяем каждый бит.
 * (mask & (1 << i)) != 0 означает, что i-й бит установлен, значит nums[i] включаем в подмножество.
 * 4.	Сборка результата:
 * Каждая маска преобразуется в List<Integer> с помощью Collectors.toList(), а затем все такие списки собираются в
 * итоговый список подмножеств.
 * 5.	Сложность:
 * Время выполнения — O(n * 2^n), что оптимально для задачи генерации всех подмножеств, поскольку вывод уже содержит
 * столько элементов.
 * Пространственная сложность — O(2^n) для хранения результата.
 * Проверка в main:
 * Все проверки result.contains(...) должны вывести true, так как метод generate вернёт все указанные подмножества.
 */

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
