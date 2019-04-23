package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
        int i;
        int cells;
        int size = list.size();
        if (size % rows == 0) {
            cells = size / rows;
        } else {
            if (size / rows < 1) {
                cells = rows;
                for (i = 0; i < rows - size; i++) {
                    list.add(0);
                }
            } else {
                cells = size / rows + 1;
                int result = cells * rows - size;
                for (i = 0; i < result; i++) {
                    list.add(0);
                }
            }
        }
        int[] array = new int[list.size()];
        i = 0;
        for (Integer cell : list) {
            array[i++] = cell;
        }
        int[][] arrayResult = new int[rows][cells];
        int pozition = 0;
        for (i = 0; i < rows; i++) {
            System.arraycopy(array, pozition, arrayResult[i], 0, cells);
            pozition = pozition + cells;
        }
        return arrayResult;
    }
}
