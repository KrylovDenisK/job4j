package ru.job4j.tracker.actions;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.inputs.Input;

public class ShowItems extends BaseAction {
    public ShowItems(int key, String name) {
        super(key, name);
    }
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("-------------- Список заявок---------------");
        for (Item item  : tracker.findAll()) {
            System.out.println(item.toString());
        }
    }
}
