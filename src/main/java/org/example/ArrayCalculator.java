/**
 * Дан массив double[N], необходимо реализовать на Java метод, возвращающий частное от деления разности максимального
 * с минимальным элементов и среднего арифметического значения элементов массива.
 * В случае ошибки требуемый метод должен возвращать только указанный Exception.
 * Сигнатура требуемого метода должна иметь вид:
 * public static double foo(double[] sourceArray) throws MyException;
 */

package org.example;

public class ArrayCalculator {

    // Пользовательское исключение
    public static class MyException extends Exception {
        public MyException(String message) {
            super(message);
        }
    }

    public static double foo(double[] sourceArray) throws MyException {
        if (sourceArray == null || sourceArray.length == 0) {
            throw new MyException("Массив не может быть null или пустым");
        }

        if (sourceArray.length < 2) {
            throw new MyException("Массив должен содержать как минимум 2 элемента");
        }

        double min = sourceArray[0];
        double max = sourceArray[0];
        double sum = 0;

        // Находим минимальное, максимальное и сумму
        for (double value : sourceArray) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
            sum += value;
        }

        double average = sum / sourceArray.length;

        // Проверка деления на ноль
        if (Math.abs(average) < 1e-10) {
            throw new MyException("Среднее арифметическое близко к нулю, деление невозможно");
        }

        return (max - min) / average;
    }

    public static void main(String[] args) throws MyException {
//        double[] sourceArray2 = null;
//        double[] sourceArray2 = new double[0];
//        double[] sourceArray2 = new double[1];
//        double[] sourceArray2 = new double[2];
        double[] sourceArray2 = new double[4];
        sourceArray2[0] = 5;
        sourceArray2[1] = 6;
        sourceArray2[2] = 7;
        sourceArray2[3] = 8;
        System.out.println(foo(sourceArray2));
    }
}

