package ru.job4j.set;

import ru.job4j.list.SimpleList;

import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T> {
    private SimpleList<T> simpleList = new SimpleList<>(16);

    public void add(T value) {
        if (!simpleList.hasValue(value)) {
            simpleList.add(value);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return simpleList.iterator();
    }
}
