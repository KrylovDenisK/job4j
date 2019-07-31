package ru.job4j.tree;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizeTest {
    private Analize analize = new Analize();
    private List<Analize.User> previous = new ArrayList<>();
    private List<Analize.User> current = new ArrayList<>();

    @Before
    public void beforeTest() {
        for (int i = 0; i < 10; i++) {
            previous.add(new Analize.User(i, "Name_" + i));
            if (i % 2 == 0) {
                current.add(new Analize.User(i, "Name_" + i));
            }
        }
       // previous.add(new Analize.User(0, "Name_" + 0));
        for (int i = 10; i < 15; i++) {
            current.add(new Analize.User(i, "Name_" + i));
        }
        current.set(4, new Analize.User(8, "Name_84"));

    }

    @Test
    public void diff() {
        Analize.Info expected = new Analize.Info(5, 1, 5);
        assertThat(analize.diff(previous, current), is(expected));
    }
}