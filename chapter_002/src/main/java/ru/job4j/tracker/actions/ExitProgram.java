package ru.job4j.tracker.actions;

import ru.job4j.tracker.StartUI;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.inputs.Input;

import java.util.function.Consumer;

public class ExitProgram extends BaseAction {
    private StartUI startUI;
    public ExitProgram(int key, String name, Consumer<String> output, StartUI startUI) {
        super(key, name, output);
        this.startUI = startUI;
    }
    @Override
    public void execute(Input input, Tracker tracker) {
       getOutput().accept("Завершение работы");
       startUI.setWork(false);
    }
}
