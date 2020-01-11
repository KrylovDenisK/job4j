package ru.job4j.tracker.actions;

import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.inputs.Input;
import java.util.function.Consumer;

public class FindItemsByName extends BaseAction {
    public FindItemsByName(int key, String name, Consumer<String> output) {
        super(key, name, output);
    }
        @Override
        public void execute(Input input, ITracker tracker) {
            getOutput().accept("------------ Поиск заявок по имени --------------");
            String name = input.ask("Введите имя заявки :");
            for (Item item : tracker.findByName(name)) {
                getOutput().accept(item.toString());
            }
        }
    }

