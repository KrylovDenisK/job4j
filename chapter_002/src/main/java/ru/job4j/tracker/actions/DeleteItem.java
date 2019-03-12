package ru.job4j.tracker.actions;

import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.inputs.Input;

public class DeleteItem implements UserAction {
    @Override
    public int key() {
        return 3;
    }
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("--------------Удаление заявки-------------");
        String id = input.ask("Введите id заявки:");
        if (tracker.delete(id)) {
            System.out.println("Операция выполнена!");
        } else {
            System.out.println("Операция не выполнена!");
        }
    }
    @Override
    public String info() {
        return "3. Delete item";
    }
}

