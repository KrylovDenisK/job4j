package ru.job4j.list;

import java.util.Objects;

public class Node<T> {
     Node<T> next;
     T value;

    public Node(T value) {
        this.value = value;
    }

    public boolean hasCycle(Node<T> first) {
        Node<T> nodeI = first;
        Node<T> nodeJ = first;

        while (!Objects.isNull(nodeJ)) {
            if (nodeJ.next == nodeI || nodeJ.next.next == nodeI) {
                return true;
            }
            nodeI = nodeI.next;
            nodeJ = nodeJ.next.next;
        }
        return false;
    }
}
