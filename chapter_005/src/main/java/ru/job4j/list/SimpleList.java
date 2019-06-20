package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class SimpleList<T> implements Iterable<T> {

    private T[] conteiner;
    private int size;
    private int index = 0;
    private int modCount = 0;

    public SimpleList(int size) {
        this.size = size;
        conteiner = (T[]) new Object[size];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            private int indexItr = 0;
            @Override
            public boolean hasNext() {
                return indexItr < index;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return conteiner[indexItr++];
            }
        };
    }

    public int getSize() {
        return size;
    }

    public void add(T value) {
        conteiner[index++] = value;
        if (index == size) {
            expansionContainer();
            size = conteiner.length;
            modCount++;
        }
    }

    public T get(int index) {
        return conteiner[index];
    }

    private void expansionContainer() {
        T[] newConteiner = (T[]) new Object[(size * 3) / 2 + 1];
        System.arraycopy(conteiner, 0, newConteiner, 0, size);
        conteiner = newConteiner;
    }

}