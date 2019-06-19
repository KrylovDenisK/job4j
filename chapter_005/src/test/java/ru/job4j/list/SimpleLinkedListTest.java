package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleLinkedListTest {
    private SimpleLinkedList<Integer> simpleList = new SimpleLinkedList<>();
    @Before
    public void setUp() {
        IntStream.range(0, 4).forEach(simpleList::add);
    }

    @Test
    public void whenAddDataThenFirstNewNode() {
        simpleList.add(10);
        simpleList.add(100);
        assertThat(simpleList.get(0), is(100));
        assertThat(simpleList.get(1), is(10));
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
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(0));
        assertThat(it.hasNext(), is(false));
    }
}