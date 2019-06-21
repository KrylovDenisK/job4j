package ru.job4j.list;

public class SimpleQueue<T> {
    private SimpleStack<T> in = new SimpleStack<>();
    private SimpleStack<T> out = new SimpleStack<>();

    public void push(T value) {
        in.push(value);
    }

    public T poll() {
        if (!in.isEmpty()) {
            if (out.isEmpty()) {
                while (!in.isEmpty()) {
                    out.push(in.poll());
                }
            }
        }
        return out.poll();
    }
}
