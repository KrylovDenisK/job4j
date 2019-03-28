package ru.job4j.tracker.inputs;

import ru.job4j.tracker.exception.MenuOutException;
import ru.job4j.tracker.inputs.Input;

public class StubInput implements Input {

    private final String[] value;

    private int position;

    public StubInput(final String[] value) {
        this.value = value;
    }
    @Override
    public String ask(String question) {
        return this.value[this.position++];
    }

    public int ask(String question, int[] range) {
        boolean flag = false;
        int key = Integer.valueOf(this.ask(question));
        for (int value : range) {
            if (value == key) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            throw new MenuOutException("Введенное значение вызходит из значения диапазона");
        }
        return key;
    }
}