package ru.job4j.singleton;

import ru.job4j.tracker.Item;

import java.util.Arrays;
import java.util.Random;

public class TrackerSingle4 {
    private final Item[] items = new Item[100];
    private int position = 0;
    public final Random random = new Random();

    private TrackerSingle4() {
    }

    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    private String generateId() {
        return String.valueOf(random.nextInt());
    }

    public Item[] findAll() {
        return Arrays.copyOf(this.items, this.position);
    }
        private static final class Holder {
            private static final TrackerSingle4 INSTANCE = new TrackerSingle4();
        }

    public static TrackerSingle4 getInstance() {
        return Holder.INSTANCE;
    }
}