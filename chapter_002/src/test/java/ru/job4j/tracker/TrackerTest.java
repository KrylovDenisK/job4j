package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }
    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription1", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }
    @Test
    public void whenFindItemByIdThenReturnThisItem() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription1", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        tracker.add(next);
        assertThat(tracker.findById(next.getId()), is(next));
    }
    @Test
    public void whenDeleteItemByIdThenFinilArrayWithoutIt() {
        Tracker tracker = new Tracker();
        Item one = new Item("test1", "testDescription1", 123L);
        tracker.add(one);
        Item two = new Item("test2", "testDescription2", 1234L);
        tracker.add(two);
        Item three = new Item("test3", "testDescription3", 12345L);
        tracker.add(three);
        Item[] expect = new Item[] {one, three};
        tracker.delete(two.getId());
        assertThat(tracker.findAll(), is(expect));
    }
    @Test
    public void whenFindItemByNameThenReturnArrayThisItem() {
        Tracker tracker = new Tracker();
        Item one = new Item("test1", "testDescription1", 123L);
        tracker.add(one);
        Item two = new Item("test2", "testDescription2", 1234L);
        tracker.add(two);
        Item three = new Item("test3", "testDescription3", 12345L);
        tracker.add(three);
        Item four = new Item("test2", "testDescription3", 12345L);
        tracker.add(four);
        Item[] result = new Item[2];
        result = tracker.findByName("test2");
        Item[] expect = new Item[] {two, four};
        assertThat(result, is(expect));
    }
}