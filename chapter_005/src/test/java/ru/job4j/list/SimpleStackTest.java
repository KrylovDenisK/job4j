package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class SimpleStackTest {

    private SimpleStack<Integer> stack = new SimpleStack<>();
    @Before
    public void setUp() {
        IntStream.range(0, 5).forEach(stack::push);
    }
    @Test
    public void whenPollStack() {
        assertThat(stack.poll(), is(4));
        assertThat(stack.poll(), is(3));
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(1));
        assertThat(stack.poll(), is(0));
    }
}