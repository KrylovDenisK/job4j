package ru.job4j.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TwoDimanshional implements Iterator<Integer> {

    private final int[][] array;
    private int row = 0;
    private int collomn = 0;
    private int index = 0;

    public TwoDimanshional(final int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return Arrays.stream(array).flatMapToInt(Arrays::stream).count() > index;
    }

    @Override
    public Integer next() {
        if (Arrays.stream(array).flatMapToInt(Arrays::stream).count() <= index) {
            throw new NoSuchElementException();
        }
        int result;
        if (collomn < array[row].length - 1) {
            result = array[row][collomn++];
        } else {
            result = array[row++][collomn];
            collomn = 0;
        }
        index++;
        return result;
    }
}

