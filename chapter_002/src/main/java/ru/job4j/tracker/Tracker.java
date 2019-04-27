package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

public class Tracker {
    private final List<Item> items = new ArrayList<>();
    private final Random random = new Random();
    /**
     * Метод реализаует добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        items.add(item);
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
        for (Item element : items) {
            if (element != null && element.getId().equals(id)) {
                item.setId(id);
                items.add(item);
                items.remove(element);
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
        for (Item element : items) {
            if (element != null && element.getId().equals(id)) {
                items.remove(element);
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
        return items.toArray(new Item[items.size()]);
    }
    /**
     * Метод отбирает заявки по key(this.name) и сохраняет в массив
     * @param key строка для поиска
     * @return array массив совпадений name
     */
    public Item[] findByName(String key) {
        int index = 0;
        List<Item> array = new ArrayList<>();
        for (Item i : items) {
            if (i != null && i.getName().equals(key)) {
                array.add(i);
            }
        }
        return array.toArray(new Item[array.size()]);
    }
}


