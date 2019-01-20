package ru.job4j.array;

public class MergeArrays {
   public int[] merge(int[] firstArray, int[] secondArray) {
        int i = 0, j = 0;
        int[] array = new int[firstArray.length + secondArray.length];
        for (int m = 0; m < array.length; m++) {
           if (i > firstArray.length - 1) {
               array[m] = secondArray[j];
               j++;
           } else if (j > secondArray.length - 1) {
               array[m] = firstArray[i];
               i++;
           } else if (firstArray[i] < secondArray[j]) {
               array[m] = firstArray[i];
               i++;
           } else {
               array[m] = secondArray[j];
               j++;
           }
       }
        return array;
    }
}

