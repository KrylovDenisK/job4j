package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class SimpleQueueTest {
    private SimpleQueue<Integer> simpleQueue = new SimpleQueue<>();
    @Before
    public void setUp() {
        IntStream.range(0, 5).forEach(simpleQueue::push);
    }

    @Test
    public void poll() {
        assertThat(simpleQueue.poll(), is(0));
        simpleQueue.push(5);
        assertThat(simpleQueue.poll(), is(1));
        simpleQueue.push(6);
        assertThat(simpleQueue.poll(), is(2));
        assertThat(simpleQueue.poll(), is(3));
        assertThat(simpleQueue.poll(), is(4));
        assertThat(simpleQueue.poll(), is(5));
        assertThat(simpleQueue.poll(), is(6));

    }
}