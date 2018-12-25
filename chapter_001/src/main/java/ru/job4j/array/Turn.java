package ru.job4j.array;

public class Turn {
    public int[] back(int[] array) {
        int temp;
        int size = array.length;
        for (int i = 0; i < size; i++) {
           if (size % 2 != 0 && i == size - i - 1) {
               break;
           }
            temp = array[i];
            array[i] =  array[size - i - 1];
            array[size - i - 1] = temp;
            if (size % 2 == 0 && i + 1 == size - i - 1) {
                break;
            }
        }
        return array;
    }
}