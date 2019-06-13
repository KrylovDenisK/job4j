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
        return IntStream.range(index, array.length).anyMatch(i -> array[i] % 2 == 0);
    }

    @Override
    public Integer next() {
        if (array.length <= index) {
            throw new NoSuchElementException();
        }

        if (index == 0) {
            index = searchIndexEventItem(index);
        }
        int result = array[index++];
        index = searchIndexEventItem(index);
        return result;
    }
    private int searchIndexEventItem(int poz) {
        return IntStream.range(poz, array.length).filter(i -> array[i] % 2 == 0).findFirst().orElse(array.length);
    }
}
