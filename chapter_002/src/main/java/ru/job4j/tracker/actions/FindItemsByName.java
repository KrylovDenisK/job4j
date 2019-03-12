package ru.job4j.tracker.actions;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.inputs.Input;

public class FindItemsByName implements UserAction {
    @Override
    public int key() {
        return 5;
    }
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Поиск заявок по имени --------------");
        String name = input.ask("Введите имя заявки :");
        for (Item item : tracker.findByName(name)) {
            System.out.println(item.toString());
        }
    }
    @Override
    public String info() {
        return "5. Find items by name";
    }
}

