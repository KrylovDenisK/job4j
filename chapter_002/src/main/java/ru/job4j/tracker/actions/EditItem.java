package ru.job4j.tracker.actions;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.inputs.Input;

import java.util.function.Consumer;

public class EditItem extends BaseAction {
    public EditItem(int key, String name, Consumer<String> output) {
        super(key, name, output);
    }
    @Override
    public void execute(Input input, Tracker tracker) {
        getOutput().accept("----------------Замена заявки-------------");
        String name = input.ask("Введите имя новой заявки :");
        String desc = input.ask("Введите описание новой заявки :");
        String id = input.ask("Введите id изменяемой заявки");
        Item item = new Item(name, desc);
        if (tracker.replace(id, item)) {
            getOutput().accept("Операция выполнена!");
        } else {
            getOutput().accept("Операция не выполнена!");
        }
    }
}

