package ru.job4j.singleton;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

public enum TrackerSingle {
    INSTANCE; // здесь мы указываем перечисления.
    private TrackerSingle() {

    }
}

