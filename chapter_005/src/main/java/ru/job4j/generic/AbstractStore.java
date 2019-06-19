package ru.job4j.generic;

import java.util.stream.IntStream;

public abstract class AbstractStore<T extends Base> implements Store<T> {
    private SimpleArray<T> list;
    private int size;

    public AbstractStore(int size) {
        this.size = size;
        list = new SimpleArray<>(size);
    }

    @Override
    public void add(T model) {
        list.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        int index = searchIndex(id);
        if (index != -1) {
            list.set(index, model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int index = searchIndex(id);
        if (index != -1) {
            list.remove(index);
            result = true;
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        int index = searchIndex(id);
        if (index != -1) {
            result = list.get(index);
        }
        return result;
    }
    private int searchIndex(String id) {
        return IntStream.range(0, size).filter(x -> list.get(x).getId().equals(id)).findFirst().orElse(-1);
    }


}
