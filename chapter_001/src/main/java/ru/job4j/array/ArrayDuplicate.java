package ru.job4j.array;
import java.util.Arrays;
/**
  Удаление дубликатов элементов в массиве String
 */
public class ArrayDuplicate {
    /**
     * Поиск дубликатов, создание массива без дубликатов
     * @param array Массив с дубликатами
     * @return Массив без дубликатов
     */
    public String[] fillNullDublicate(String[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (array[i].equals(array[j])) {
                    array[j] = array[n - 1];
                    n--;
                    j--;
                }
            }
        }
        return Arrays.copyOf(array, n);
    }
}