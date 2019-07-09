package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.IntStream;

public class HashMap<K, V> implements Iterable {

    private Node<K, V>[] nodes = new Node[16];
    private int modCount = 0;
    private int count = 0;

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

        public void setValue(V value) {
            this.value = value;
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
        double numberFilling = count / nodes.length;
        if (numberFilling >= 0.7) {
            nodesExpansion();
        }
        int hashCode = hashCode(key);
        int hash = hash(hashCode);
        Node<K, V> node = new Node<>(key, value, hash);
        int index = calculateIndex(hash);
        if (Objects.nonNull(nodes[index])) {
            if (Objects.equals(nodes[index].getKey(), key)) {
                nodes[index].setValue(value);
                modCount++;
                result = true;
            }
        } else {
            nodes[index] = node;
            modCount++;
            count++;
            result = true;
        }
        return result;
    }

    /**
     * Получить значение по ключу
     * @param key
     * @return значение
     */
    public V get(K key) {
        V result = null;
        int hashCode = hashCode(key);
        int index = calculateIndex(hash(hashCode));
        if (Objects.nonNull(nodes[index]) && hashCode(nodes[index].getKey()) == hashCode(key)
                    && Objects.equals(nodes[index].getKey(), key)) {
            result = nodes[index].getValue();
        }
        return result;
    }

    /**
     * Удалить значение по ключу
     */
    public boolean delete(K key) {
        boolean result = false;
        int hashCode = hashCode(key);
        int index = calculateIndex(hash(hashCode));
        if (Objects.nonNull(nodes[index]) && hashCode(nodes[index].getKey()) == hashCode(key)
                && Objects.equals(nodes[index].getKey(), key)) {
            nodes[index] = null;
            modCount++;
            count--;
            result = true;
        }
        return result;
    }

    private int hashCode(K key) {
        return Objects.nonNull(key) ? key.hashCode() : 0;
    }
    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }
    private int calculateIndex(int hash) {
          return hash % nodes.length;
    }
    /**
     * Расширение массива Nodes
     */
    private void nodesExpansion() {
        Node<K, V>[] newNodes = new Node[nodes.length * 3];
        for (int i = 0; i < nodes.length; i++) {
            if (Objects.nonNull(nodes[i])) {
                Node<K, V> node = nodes[i];
                newNodes[node.getHash() % newNodes.length] = node;
            }
        }
        nodes = newNodes;
    }

}
