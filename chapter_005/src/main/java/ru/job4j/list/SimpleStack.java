package ru.job4j.list;

public class SimpleStack<T> {
    private SimpleLinkedList<T> simpleLinkedList = new SimpleLinkedList<>();
    /**
     * Delete and return Stack value
     */
    public T poll() {
        return simpleLinkedList.delete();
    }
    /**
     * Add value in stack
     */
    public void push(T value) {
        simpleLinkedList.add(value);
    }

    public boolean isEmpty() {
        return simpleLinkedList.getSize() == 0;
    }
}
