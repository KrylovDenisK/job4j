package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class SimpleLinkedList<T> implements Iterable<T> {
    private Node<T> first;
    private int size;
    private int modCount;

    public static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }
    /**
     * Insert value in start list
     */
    public void add(T data) {
        Node<T> node = new Node<>(data);
        node.next = first;
        first = node;
        size++;
        modCount++;
    }

    public T get(int index) {
        Node<T> result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> node = first;
            private int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                T result = node.data;
                node = node.next;
                return result;
            }
        };
    }
}
