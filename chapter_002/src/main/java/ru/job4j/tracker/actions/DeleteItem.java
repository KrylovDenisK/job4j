package ru.job4j.tracker.actions;

import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.inputs.Input;

import java.util.function.Consumer;

public class DeleteItem extends BaseAction {

    public DeleteItem(int key, String name, Consumer<String> output) {
        super(key, name, output);
    }
    @Override
    public void execute(Input input, Tracker tracker) {
        super.getOutput().accept("--------------Удаление заявки-------------");
        String id = input.ask("Введите id заявки:");
        if (tracker.delete(id)) {
           getOutput().accept("Операция выполнена!");
        } else {
            getOutput().accept("Операция не выполнена!");
        }
    }
}

