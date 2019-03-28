package ru.job4j.tracker.actions;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.inputs.Input;

public class EditItem extends BaseAction {
    public EditItem(int key, String name) {
        super(key, name);
    }
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("----------------Замена заявки-------------");
        String name = input.ask("Введите имя новой заявки :");
        String desc = input.ask("Введите описание новой заявки :");
        String id = input.ask("Введите id изменяемой заявки");
        Item item = new Item(name, desc);
        if (tracker.replace(id, item)) {
            System.out.println("Операция выполнена!");
        } else {
            System.out.println("Операция не выполнена!");
        }
    }
}

