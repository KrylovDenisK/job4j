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
        do {
            nodeI = nodeI.next;
            for (int j = 0; j < 2; j++) {
                if (Objects.isNull(nodeJ.next)) {
                    return false;
                }
                nodeJ = nodeJ.next;
            }
        } while (nodeI != nodeJ);
        return true;
    }
}
