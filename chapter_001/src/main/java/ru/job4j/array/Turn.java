package ru.job4j.array;

public class Turn {
    public int[] back(int[] array) {
        int temp;
        int size = array.length;
        for (int i = 0; i < size / 2; i++) {
            temp = array[i];
            array[i] =  array[size - i - 1];
            array[size - i - 1] = temp;
        }
        return array;
    }
}