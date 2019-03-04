package ru.job4j.tracker;

public class FindItemById implements UserAction {
    @Override
    public int key() {
        return 4;
    }
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Поиск заявки по ID --------------");
        String id = input.ask("Введите ID заявки :");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println("Имя заявки: " + item.getName() + " Описание заявки: " + item.getDesc());
        } else {
            System.out.println("Заявка не найдена!!!");
        }
    }
    @Override
    public String info() {
        return "4. Find Item By Id.";
    }
}

