package ru.job4j.tracker.actions;

import ru.job4j.tracker.StartUI;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.inputs.Input;

public class ExitProgram extends BaseAction {
    private StartUI startUI;
    public ExitProgram(int key, String name, StartUI startUI) {
        super(key, name);
        this.startUI = startUI;
    }
    @Override
    public void execute(Input input, Tracker tracker) {
       System.out.println("завершение работы");
       this.startUI.setWork(false);
    }
}
