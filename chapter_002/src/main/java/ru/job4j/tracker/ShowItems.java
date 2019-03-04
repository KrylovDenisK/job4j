package ru.job4j.tracker;

public class ShowItems implements UserAction{
    @Override
    public int key() {
        return 1;
    }
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("-------------- Список заявок---------------");
        for (Item item  : tracker.findAll()) {
            System.out.println(item.toString());
        }
    }
    @Override
    public String info() {
        return "1. Show All Items.";
    }
}
