package ru.job4j.list;

public class SimpleArrayList<T> {
    private int size;
    private Node<T> first;

    /**
     * Метод вставляет в начало списка данные.
     */
    public void add(T data) {
        Node<T> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Реализовать метод удаления первого элемент в списке.
     */
    public T delete() {
        first = first.next;
        size--;
        return first.data;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public T get(int index) {
        Node<T> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {

        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }
}
