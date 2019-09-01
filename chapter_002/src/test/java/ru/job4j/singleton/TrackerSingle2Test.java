package ru.job4j.singleton;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.Item;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerSingle2Test {
    private final PrintStream stdout = new PrintStream(System.out);
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private Item one, two;

    {
        one = new Item("one", "one");
        two = new Item("two", "two");
    }

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(out));
    }
    @After
    public void backOutput() {
        System.setOut(stdout);
    }
    @Test
    public void whenTryCreateArrayObjectThenUseOneObjectFor2() {
         ArrayList<TrackerSingle2> single = new ArrayList<>();
         for (int i = 0; i < 3; i++) {
             single.add(TrackerSingle2.getInstance());
         }

         String expected = "1" + System.lineSeparator() + "1" + System.lineSeparator() + "1" + System.lineSeparator();
         System.setOut(new PrintStream(out));
         assertThat(out.toString(),
                is(expected));
         System.setOut(stdout);
    }
    @Test
    public void whenTryCreateArrayObjectUseOneObjectFor4() {
        TrackerSingle4 trackerOne = TrackerSingle4.getInstance();
        TrackerSingle4 trackerTwo = TrackerSingle4.getInstance();
        trackerOne.add(this.one);
        trackerTwo.add(this.two);
        assertThat(trackerOne.findAll()[1], is(this.two));
    }
}

