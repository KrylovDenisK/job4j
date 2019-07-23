package ru.job4j.tree;

import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;
    private int modCount = 0;

    public Tree(E value) {
        this.root = new Node<>(value);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> itemParent = findBy(parent);
        Optional<Node<E>> itemChild = findBy(child);
        if (itemParent.isPresent() && itemChild.isEmpty()) {
            itemParent.get().leaves().add(new Node<>(child));
            modCount++;
            result = true;
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Queue<Node<E>> data = new LinkedList<>(List.of(root));
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return !data.isEmpty();
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> value = data.poll();
                for (Node<E> child : value.leaves()) {
                    data.offer(child);
                }
                return value.getValue();
            }
        };
    }

    public boolean isBinary() {
        boolean result = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> item = data.poll();
            if (item.leaves().size() > 2) {
                result = false;
                break;
            }
            for (Node<E> child : item.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }
}
