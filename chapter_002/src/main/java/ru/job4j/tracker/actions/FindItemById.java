package ru.job4j.tracker.actions;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.inputs.Input;

public class FindItemById extends BaseAction {
    public FindItemById(int key, String name) {
        super(key, name);
    }
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Поиск заявки по ID --------------");
        String id = input.ask("Введите ID заявки :");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println("Имя заявки: " + item.getName() + " Описание заявки: " + item.getDesc());
        } else {
            System.out.println("Заявка не найдена!!!");
        }
    }
}

