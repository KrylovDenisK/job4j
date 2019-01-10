package ru.job4j.array;
import java.util.function.BiPredicate;

/**
 * Обертка над строкой.
 */
public class BubbleSort {
    public int[] sort(int[] array) {
        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[j -  1] > array[j]) {
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }
}
