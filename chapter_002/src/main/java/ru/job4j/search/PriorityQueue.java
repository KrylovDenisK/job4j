package ru.job4j.search;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();
    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        if (tasks.isEmpty()) {
            tasks.add(task);
        } else {
            for (Task element : tasks) {
                if (task.getPriority() < element.getPriority()) {
                    tasks.add(tasks.indexOf(element), task);
                    break;
                }
            }
        }

    }

    public Task take() {
        return this.tasks.poll();
    }
}

