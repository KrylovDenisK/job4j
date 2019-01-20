package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MergeArraysTest {
    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
        int[] firstArray = {1, 3, 5};
        int[] secondArray = {0, 2, 4};
        MergeArrays array = new MergeArrays();
        int[] result = array.merge(firstArray, secondArray);
        int[] expect = new int[] {0, 1, 2, 3, 4, 5};
        assertThat(result, is(expect));
    }
}
