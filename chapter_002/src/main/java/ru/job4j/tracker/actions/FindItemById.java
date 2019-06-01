package ru.job4j.tracker.actions;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.inputs.Input;

import java.util.function.Consumer;

public class FindItemById extends BaseAction {
    public FindItemById(int key, String name, Consumer<String> output) {
        super(key, name, output);
    }
    @Override
    public void execute(Input input, Tracker tracker) {
        getOutput().accept("------------ Поиск заявки по ID --------------");
        String id = input.ask("Введите ID заявки :");
        Item item = tracker.findById(id);
        if (item != null) {
            getOutput().accept("Имя заявки: " + item.getName() + " Описание заявки: " + item.getDesc());
        } else {
            getOutput().accept("Заявка не найдена!!!");
        }
    }
}

