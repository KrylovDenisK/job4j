package ru.job4j.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
        int cells;
        int size = list.size();
        if (size % rows == 0) {
            cells = size / rows;
        } else {
            cells = size / rows < 1 ? rows : size / rows + 1;
        }
        int[][] arrayResult = new int[rows][cells];
        int i = 0;
        int j = 0;
        for (Integer item : list) {
            arrayResult[i][j] = item;
            if ((j + 1) % cells == 0) {
                j = 0;
                i++;
            } else {
                j++;
            }
        }
        return arrayResult;
    }
}
