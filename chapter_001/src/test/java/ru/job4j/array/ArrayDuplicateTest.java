package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] array = {"6", "7", "7", "6", "6", "5", "1", "1", "5"};
        String[] table = arrayDuplicate.fillNullDublicate(array);
        String[] expect = {"6", "7", "5", "1"};
        assertThat(table, is(expect));
    }
}