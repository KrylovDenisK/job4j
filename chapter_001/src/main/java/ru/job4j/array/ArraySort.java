package ru.job4j.array;
/**
 * Сортировка массива по возрастанию
 */
public class ArraySort {
    /**
     * Сортировка массива по возрастанию
     * @param array массив для сортировка
     * @return отсортиированный массив
     */
        public int[] sortUp(int[] array) {
            int temp;
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length; j++) {
                    if (array[i] < array[j]) {
                        temp = array[j];
                        array[j] = array[i];
                        array[i] = temp;
                    }
                }
            }
            return array;
        }
}