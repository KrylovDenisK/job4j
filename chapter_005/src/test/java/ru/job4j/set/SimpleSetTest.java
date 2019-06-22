package ru.job4j.set;

import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    private SimpleSet<Integer> set = new SimpleSet<>();
    @Test
    public void add() {
        IntStream.range(0, 6).forEach(set::add);
        set.add(1);
        set.add(2);
        List<Integer> result = new ArrayList<>();
        set.forEach(result::add);
        var expected = List.of(0, 1, 2, 3, 4, 5);
        assertThat(result, is(expected));
    }


    @Test
    public void add2() {
        SimpleSet<String> set1 = new SimpleSet<>();
        set1.add(new String("123"));
        set1.add(new String("123"));
        set1.add(new String("123"));
        List<String> strings = new ArrayList<>();
        set1.forEach(strings::add);
        assertThat(strings.get(0), is("123"));
    }

}