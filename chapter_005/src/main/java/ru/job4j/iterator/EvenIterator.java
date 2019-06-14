package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class EvenIterator implements Iterator<Integer> {

    private final int[] array;
    private int index = 0;

    public EvenIterator(final int[] array) {
        this.array = array;

    }

    @Override
    public boolean hasNext() {
        if (index > -1) {
            index = IntStream.range(index, array.length).filter(i -> array[i] % 2 == 0).findFirst().orElse(-1);
        }
        return index > -1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[index++];
    }
}
