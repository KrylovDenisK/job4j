package ru.job4j.tracker.actions;

import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.inputs.Input;

public class DeleteItem extends BaseAction {

    public DeleteItem(int key, String name) {
        super(key, name);
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
}

