package ru.job4j.tracker;

import java.util.Random;
import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int position = 0;
    public final Random random = new Random();
    /**
     * Метод реализаует добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }
    /**
     * Метод генерирует уникальный ключ для заявки.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return String.valueOf(random.nextInt());
    }
    /**
     * Поиск заявки по ID
     * @param id id заявки
     * @return найденная заявка
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }
    /**
     * Метод ищет заявку по id и производит ее замену.
     * @param id   id заменяемой заявки
     * @param item новая заявка
     * @return true - опперация успешна, false - нет.
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i] != null && this.items[i].getId().equals(id)) {
                item.setId(id);
                this.items[i] = item;
                result = true;
                break;
            }
        }
        return result;
    }
    /**
     * Метод ищет заявку по id и производит ее удаление.
     * @param id id удаляемой заявки
     * @return true - опперация успешна, false - нет.
     */
    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i] != null && this.items[i].getId().equals(id)) {
                System.arraycopy(this.items, i + 1, items, i, items.length - i - 1);
                this.position--;
                result = true;
                break;
            }
        }
        return result;
    }
    /**
     * @return array возвращает копию массива this.items без null элементов
     */
    public Item[] findAll() {
        return Arrays.copyOf(this.items, this.position);
    }
    /**
     * Метод отбирает заявки по key(this.name) и сохраняет в массив
     * @param key строка для поиска
     * @return array массив совпадений name
     */
    public Item[] findByName(String key) {
        int i = 0;
        Item[] array = new Item[100];
        for (int j = 0; j < this.items.length; j++) {
            if (this.items[j] != null && this.items[j].getName().equals(key)) {
                array[i] = this.items[j];
                i++;
            }
        }
        return Arrays.copyOf(array, i);
    }
}

