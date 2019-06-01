package ru.job4j.tracker.actions;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.inputs.Input;

import java.util.function.Consumer;

public class ShowItems extends BaseAction {
    public ShowItems(int key, String name, Consumer<String> output) {
        super(key, name, output);
    }
    @Override
    public void execute(Input input, Tracker tracker) {
        super.getOutput().accept("-------------- Список заявок---------------");
        for (Item item  : tracker.findAll()) {
            getOutput().accept(item.toString());
        }
    }
}
