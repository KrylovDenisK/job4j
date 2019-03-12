package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.inputs.Input;
import ru.job4j.tracker.inputs.StubInput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {
    private final PrintStream stdout = new PrintStream(System.out);
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(out));
    }
    @After
    public void backOutput() {
        System.setOut(stdout);
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "y"});
        new StartUI(input, tracker).init();
       assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = new Item("test name", "desc");
        tracker.add(item);
        Input input = new StubInput(new String[]{"2", "test replace", "заменили заявку", item.getId(), "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test replace"));
    }

    @Test
    public void whenDeleteItemThenTrackerUpdateValue() {
        Tracker tracker = new Tracker();
        Item firstItem = tracker.add(new Item("name1", "desc1"));
        Item secondItem = tracker.add(new Item("name2", "desc2"));
        Item threeItem = tracker.add(new Item("name3", "desc3"));
        Input input = new StubInput(new String[]{"3", secondItem.getId(), "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll(), is(new Item[]{firstItem, threeItem}));
    }

   @Test
    public void whenShowAllItemThenSeeAllItem() {
        Tracker tracker = new Tracker();
        Item firstItem = tracker.add(new Item("name1", "desc1"));
        Item secondItem = tracker.add(new Item("name2", "desc2"));
        Item threeItem = tracker.add(new Item("name3", "desc3"));
        Input input = new StubInput(new String[]{"1", "y"});
        System.setOut(new PrintStream(out));
        StringBuilder expect = new StringBuilder()
                .append("0. Add new Item\r\n")
                .append("1. Show All items\r\n")
                .append("2. Edit item\r\n")
                .append("3. Delete item\r\n")
                .append("4. Find item by Id\r\n")
                .append("5. Find items by name\r\n")
                .append("6. Exit Program\r\n")
                .append("-------------- Список заявок---------------\r\n")
                .append("Наменование заявки: name1 ID заявки: " + firstItem.getId() + "\r\n")
                .append("Наменование заявки: name2 ID заявки: " + secondItem.getId() + "\r\n")
                .append("Наменование заявки: name3 ID заявки: " + threeItem.getId() + "\r\n");
        new StartUI(input, tracker).init();
        assertThat(out.toString(), is(expect.toString()));
        System.setOut(stdout);
    }
    @Test
    public void whenFindItemByIdThenShowThisItem() {
        Tracker tracker = new Tracker();
        Item firstItem = tracker.add(new Item("name1", "desc1"));
        Item secondItem = tracker.add(new Item("name2", "desc2"));
        Item threeItem = tracker.add(new Item("name3", "desc3"));
        Input input = new StubInput(new String[]{"4", secondItem.getId(), "y"});
        System.setOut(new PrintStream(out));
        StringBuilder expect = new StringBuilder()
                .append("0. Add new Item\r\n")
                .append("1. Show All items\r\n")
                .append("2. Edit item\r\n")
                .append("3. Delete item\r\n")
                .append("4. Find item by Id\r\n")
                .append("5. Find items by name\r\n")
                .append("6. Exit Program\r\n")
                .append("------------ Поиск заявки по ID --------------\r\n")
                .append("Имя заявки: " + secondItem.getName() + " Описание заявки: " + secondItem.getDesc() + "\r\n");
        new StartUI(input, tracker).init();
        assertThat(out.toString(), is(expect.toString()));
        System.setOut(stdout);

    }
}
