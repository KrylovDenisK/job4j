package ru.job4j.array;
import java.util.Arrays;
/**
  Удаление дубликатов элементов в массиве String
 */
public class ArrayDuplicate {
    public String[] fillNullDublicate(String[] array) {
        int n = array.length;
        String temp;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i != j && array[i].equals(array[j])) {
                    array[j] = "";
                }
            }
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[j - 1].equals("")) {
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }

        int result = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals("")) {
                result = i;
                break;
            }
        }
        return Arrays.copyOf(array, result);
    }
}