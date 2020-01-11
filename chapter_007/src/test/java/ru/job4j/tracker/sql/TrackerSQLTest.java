package ru.job4j.tracker.sql;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.Item;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {
    private TrackerSQL sql = new TrackerSQL();

    @Before
    public void sqlInit() {
        sql.init();
    }

    @Test
    public void checkConnection() {
        assertThat(sql.init(), is(true));
    }

    @Test
    public void whenItemAddResultTrue() {
        Item expected = sql.add(new Item("Поломка клавиатуры", "Не работает половина кнопок на клавиатуре"));
        Item result = sql.findById(expected.getId());
        assertThat(result.getName(), is(expected.getName()));
    }
    @Test
    public void whenDeleteResultTrue() {
        Item addItem = sql.add(new Item("1", "1"));
        assertThat(sql.delete(addItem.getId()), is(true));
    }
    @Test
    public void whenFindByNameResultTrue() {
        Item expected = sql.add(new Item("newItem", "newItem"));
        List<Item> result = sql.findByName(expected.getName());
        sql.delete(expected.getId());
        assertThat(result, is(List.of(expected)));
    }

}