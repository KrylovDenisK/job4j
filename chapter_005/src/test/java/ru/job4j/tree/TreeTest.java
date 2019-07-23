package ru.job4j.tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
public class TreeTest {
    private Tree<Integer> tree = new Tree<>(1);
    @Before
    public void setUp() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
    }

    @Test
    public void when6ElFindLastThen6() {
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void iterator() {
        Iterator<Integer> it = tree.iterator();
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
        assertThat(it.hasNext(), is(false));
    }
    @Test
    public void whenIsNotBinaryResultFalse() {
        assertThat(tree.isBinary(), is(false));
    }
    @Test
    public void whenIsBinaryResultTrue() {
        Tree<Integer> binaryTree = new Tree<>(5);
        binaryTree.add(5, 4);
        binaryTree.add(5, 6);
        binaryTree.add(4, 3);
        binaryTree.add(4, 2);
        binaryTree.add(2, 1);
        binaryTree.add(6, 7);
        assertThat(binaryTree.isBinary(), is(true));
    }
}
