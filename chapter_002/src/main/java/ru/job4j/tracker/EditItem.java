package ru.job4j.tracker;

public class EditItem implements UserAction {
    @Override
    public int key() {
        return 2;
    }
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("----------------Замена заявки-------------");
        String name = input.ask("Введите имя новой заявки :");
        String desc = input.ask("Введите описание новой заявки :");
        String id = input.ask("Введите id изменяемой заявки");
        Item item = new Item(name, desc);
        tracker.replace(id, item);
    }
    @Override
    public String info() {
        return "2. Edit Item on tracker.";
    }
}
