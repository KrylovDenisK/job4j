package ru.job4j.tracker.inputs;

import ru.job4j.tracker.exception.MenuOutException;

public class ValidateInput implements Input {
    private final Input input;

    public ValidateInput(final Input input) {
        this.input = input;
    }

    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }
    @Override
    public int ask(String question, int[] range) {
        boolean flag = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                flag = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Необходимо ввести корректное значение");
            } catch (MenuOutException moe) {
                 System.out.println("Необходимо выбрать значение из диапазона меню");
            }
        } while (flag);
        return value;
    }
}
