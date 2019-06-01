package ru.job4j.tracker.actions;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.inputs.Input;

import java.util.function.Consumer;

public class AddItem extends BaseAction {

    public AddItem(int key, String name, Consumer<String> output) {
        super(key, name, output);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        getOutput().accept("------------ Добавление новой заявки --------------");
        String name = input.ask("Введите имя заявки :");
        String desc = input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        tracker.add(item);
        getOutput().accept("------------ Новая заявка с getId : " + item.getId() + "-----------");
    }
}

