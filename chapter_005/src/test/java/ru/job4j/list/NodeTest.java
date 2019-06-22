package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class NodeTest {
    private Node<Integer> first;
    private Node<Integer> two;
    private Node<Integer> third;
    private Node<Integer> four;

    @Before
    public void beforeTest() {
        first = new Node(1);
        two = new Node(2);
        third = new Node(3);
        four = new Node(4);
    }

    @Test
    public void whenHasCycle() {
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
        assertThat(first.hasCycle(first), is(true));
    }
    public void whenHasNotCycle() {
        first.next = two;
        two.next = third;
        third.next = four;
        assertThat(first.hasCycle(first), is(false));
    }

}