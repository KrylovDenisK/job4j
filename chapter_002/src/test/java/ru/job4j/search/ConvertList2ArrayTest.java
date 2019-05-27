package ru.job4j.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertList2ArrayTest {
    @Test
    public void when7ElementsThen9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7)),
                2
        );
        int[][] expect = {
                {1, 2, 3, 4},
                {5, 6, 7, 0}
        };
        assertThat(result, is(expect));
    }
    @Test
    public void whenList3RowThen1Row() {
        ConvertList2Array list = new ConvertList2Array();
        List<Integer> result = list.convert(
                new ArrayList<>(Arrays.asList(
                        new int[][] {{1, 2, 3}, {4}, {5, 6, 7, 8}}
                        )
                )
        );
        List<Integer> expect = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        assertThat(result, is(expect));
    }
}