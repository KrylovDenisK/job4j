package ru.job4j.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TwoDimanshional implements Iterator<Integer> {

    private final int[][] array;
    private int row = 0;
    private int collomn = 0;

    public TwoDimanshional(final int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return array.length > row && array[row].length > collomn;
    }

    @Override
    public Integer next() {
        try {
            boolean exeption =  array[row].length <= collomn;
        } catch (ArrayIndexOutOfBoundsException obe) {
            throw new NoSuchElementException();
        }
        int result;
        if (collomn < array[row].length - 1) {
            result = array[row][collomn++];
        } else {
            result = array[row++][collomn];
            collomn = 0;
        }
        return result;
    }
}

