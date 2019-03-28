package ru.job4j.tracker.actions;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.inputs.Input;

public class FindItemsByName extends BaseAction {
    public FindItemsByName(int key, String name) {
        super(key, name);
    }
        @Override
        public void execute (Input input, Tracker tracker){
            System.out.println("------------ Поиск заявок по имени --------------");
            String name = input.ask("Введите имя заявки :");
            for (Item item : tracker.findByName(name)) {
                System.out.println(item.toString());
            }
        }
    }

