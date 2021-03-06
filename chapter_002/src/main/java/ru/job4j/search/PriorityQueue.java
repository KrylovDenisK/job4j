package ru.job4j.search;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();
    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        int pozition = tasks.size();
        for (int i = 0; i < tasks.size(); i++) {
            if (task.getPriority() < tasks.get(i).getPriority()) {
                pozition = i;
                break;
            }
        }
        tasks.add(pozition, task);
    }

    public Task take() {
        return this.tasks.poll();
    }

    public Task takeLast() {
        return tasks.getLast();
    }
}

