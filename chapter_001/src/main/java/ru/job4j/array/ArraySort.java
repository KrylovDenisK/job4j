package ru.job4j.array;

public class ArraySort {
        public int[] sortUp(int[] array) {
            int length = array.length;
            int temp;
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
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