package ru.job4j.iterator;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.generic.SimpleArray;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleArrayTest {
    private SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
    private Iterator it = simpleArray.iterator();

    @Before
    public void setUp() {
        for (int i = 0; i < 10; i++) {
            simpleArray.add(i);
        }
    }
    @Test
    public void whenAddModel() {
        assertThat(simpleArray.get(5), is(5));
        assertThat(simpleArray.get(9), is(9));
    }

    @Test
    public void whenSetModel() {
        simpleArray.set(5, 100);
        assertThat(simpleArray.get(5), is(100));
    }

    @Test
    public void whenRemoveModel() {
        simpleArray.remove(5);
        assertThat(simpleArray.get(5), is(6));
        simpleArray.remove(6);
        assertThat(simpleArray.get(6), is(8));
    }
    @Test
    public void whenIteratorThenSequence() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(0));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(8));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(9));
        assertThat(it.hasNext(), is(false));
    }
}