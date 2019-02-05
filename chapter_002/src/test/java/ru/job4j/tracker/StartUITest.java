package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = new Item("test name", "desc");
        tracker.add(item);
        Input input = new StubInput(new String[]{"2", "test replace", "заменили заявку", item.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test replace"));
    }

    @Test
    public void whenDeleteItemThenTrackerUpdateValue() {
        Tracker tracker = new Tracker();
        Item firstItem = tracker.add(new Item("name1", "desc1"));
        Item secondItem = tracker.add(new Item("name2", "desc2"));
        Item threeItem = tracker.add(new Item("name3", "desc3"));
        Input input = new StubInput(new String[]{"3", secondItem.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll(), is(new Item[]{firstItem, threeItem}));
    }
}
