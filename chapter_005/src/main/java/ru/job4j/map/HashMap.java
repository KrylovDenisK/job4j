package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.IntStream;

public class HashMap<K, V> implements Iterable {

    private Node<K, V>[] nodes = new Node[16];
    private int modCount = 0;

    @Override
    public Iterator<Node<K, V>> iterator() {
        return new Iterator<>() {
            int indexItr = 0;
            private int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                int value = IntStream.range(indexItr, nodes.length).filter(x -> Objects.nonNull(nodes[x])).findFirst().orElse(-1);
                return value != -1;
            }

            @Override
            public Node<K, V> next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (indexItr < nodes.length) {
                    if (Objects.nonNull(nodes[indexItr])) {
                        break;
                    }
                    indexItr++;
                }
                return nodes[indexItr++];
            }
        };
    }

    public static class Node<K, V> {
        K key;
        V value;
        int hash;

        private Node(K key, V value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }

        public void setHash(int hash) {
            this.hash = hash;
        }

        public int getHash() {
            return hash;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    /**
     * Добавить значение value по  ключу key
     * @param key ключ
     * @param value значение
     * @return true если успешно
     */
    public boolean insert(K key, V value) {
        boolean result = false;
        double numberFilling = filling();
        if (numberFilling >= 0.7) {
            nodesExpansion();
        }
        if (Objects.nonNull(key)) {
            int hash = hash(key.hashCode());
            Node<K, V> node = new Node<>(key, value, hash);
            int index = calculateIndex(hash, nodes.length);
            if (Objects.isNull(nodes[index])) {
                nodes[index] = node;
                modCount++;
                result = true;
            }
        } else {
            Node<K, V> node = new Node<>(null, value, 0);
            if (Objects.nonNull(nodes[0])) {
                nodes[0] = node;
                modCount++;
                result = true;
            }
        }
        return result;
    }

    /**
     * Получить значение по ключу
     * @param key
     * @return значение
     */
    public V get(K key) {
        V result;
        if (Objects.nonNull(key)) {
            int index = calculateIndex(hash(key.hashCode()), nodes.length);
            result = Objects.nonNull(nodes[index]) && nodes[index].getKey().hashCode() == key.hashCode()
                    && key.equals(nodes[index].getKey()) ? nodes[index].getValue() : null;
        } else {
            result = nodes[0].getValue();
        }
        return result;
    }

    /**
     * Удалить значение по ключу
     */
    public boolean delete(K key) {
        boolean result = false;
        int index = calculateIndex(hash(key.hashCode()), nodes.length);
        if (Objects.nonNull(nodes[index]) && nodes[index].getKey().hashCode() == key.hashCode()
                && key.equals(nodes[index].getKey())) {
            nodes[index] = null;
            modCount++;
            result = true;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }
    private int calculateIndex(int hash, int lenght) {
          return hash % nodes.length;
    }
    /**
     *  Рассчитать коэффициент заполнение
     */
    private double filling() {
        return IntStream.range(0, nodes.length).filter(x -> Objects.nonNull(nodes[x])).count() / nodes.length;
    }

    /**
     * Расширение массива Nodes
     */
    private void nodesExpansion() {
        int newHash;
        Node<K, V>[] newNodes = new Node[nodes.length * 3];
        for (int i = 0; i < nodes.length; i++) {
            if (Objects.nonNull(nodes[i])) {
                Node<K, V> node = nodes[i];
                newHash = hash(node.key.hashCode());
                node.setHash(newHash);
                newNodes[calculateIndex(newHash, newNodes.length)] = node;
            }
        }
        nodes = newNodes;
    }

}
