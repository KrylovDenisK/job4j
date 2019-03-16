package ru.job4j.tracker.inputs;

import ru.job4j.tracker.exception.MenuOutException;

public class ValidateInput extends ConsoleInput {
    public int ask(String question, int[] range) {
        boolean flag = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
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
