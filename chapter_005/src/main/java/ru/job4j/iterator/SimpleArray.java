package ru.job4j.iterator;


import java.util.Iterator;
import java.util.Objects;
import java.util.stream.IntStream;

public class SimpleArray<T> implements Iterable<T> {
    private T[] array;
    private int index;

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int poz = 0;
            @Override
            public boolean hasNext() {
                return poz > array.length;
            }

            @Override
            public T next() {
                return array[poz++];
            }
        };
    }

    public SimpleArray(T[] array) {
        this.array = array;
    }

    /**
     * Add T element
     * @param model
     */
    public void add(T model) {
        array[index++] = model;
    }

    /**
     * Set item by index
     * @param index new element
     * @param model item
     */
    public void set(int index, T model) {
        if (index <= this.index) {
            array[index] = model;
        }
    }

    /**
     *item deletion and rest
     * @param index
     */
    public void remove(int index) {
        if (!Objects.isNull(get(index))) {
            if (index != array.length - 1) {
                IntStream.range(index + 1, array.length).forEach(x -> array[x - 1] = array[x]);
                array[array.length - 1] = null;
            } else {
                array[index] = null;
            }
            this.index--;
        }
    }

    /**
     * Get item by index
     * @param index
     * @return
     */
    public T get(int index) {
        return array[index];
    }
}
