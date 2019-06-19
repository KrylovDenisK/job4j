package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class SimpleListTest {
    SimpleList<Integer> simpleList = new SimpleList<>(5);
    @Before
    public void setUp() {
        IntStream.range(0, 4).forEach(simpleList::add);
    }

    @Test
    public void whenAddValueThenNewSize() {
        assertThat(simpleList.getSize(), is(5));
        simpleList.add(4);
        assertThat(simpleList.getSize(), is(8));
        assertThat(simpleList.get(4), is(4));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void shouldReturnEvenNumbersSequentially() {
        Iterator<Integer> it = simpleList.iterator();
        IntStream.range(0, 20)
                .forEach(x -> {
                    simpleList.add(x);
                    it.next();
                });
    }
    @Test
    public void hasNextNextSequentialInvocation() {
        Iterator<Integer> it = simpleList.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(0));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
    }
}