package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArraySortTest {
    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
        int[] mas = {1, 5, 4, 2, 3, 1, 7, 8, 0, 5};
        ArraySort array = new ArraySort();
        int[] result = array.sortUp(mas);
        int[] expect = new int[] {0, 1, 1, 2, 3, 4, 5, 5, 7, 8};
        assertThat(result, is(expect));
    }
    @Test
    public void whenSortArrayWithFiveElementsThenSortedArray() {
        int[] mas = {10, 8, 9, 5, 7};
        ArraySort array = new ArraySort();
        int[] result = array.sortUp(mas);
        int[] expect = new int[] {5, 7, 8, 9, 10};
        assertThat(result, is(expect));
    }
    @Test
    public void whenSortArraySixFElementsThenSortedArray() {
        int[] mas = {8, 5, 15, 1, 0};
        ArraySort array = new ArraySort();
        int[] result = array.sortUp(mas);
        int[] expect = new int[] {0, 1, 5, 8, 15};
        assertThat(result, is(expect));
    }
}
