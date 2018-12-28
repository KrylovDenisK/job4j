package ru.job4j.array;

public class MatrixCheck {
    public boolean mono(boolean[][] table) {
        int size = table.length;
        boolean result = true;
        for (int i = 0; i < size; i++) {
                if (table[0][0] != table[i][i] || table[0][size - 1] != table[i][size - i - 1]) {
                    result = false;
                    break;
            }
        }
        return result;
    }
}