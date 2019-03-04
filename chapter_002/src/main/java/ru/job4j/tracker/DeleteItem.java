package ru.job4j.tracker;

public class DeleteItem implements UserAction {
    @Override
    public int key() {
        return 3;
    }
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("--------------Удаление заявки-------------");
        String id = input.ask("Введите id заявки:");
        tracker.delete(id);
    }
    @Override
    public String info() {
        return "3. Delete Item";
    }
}

