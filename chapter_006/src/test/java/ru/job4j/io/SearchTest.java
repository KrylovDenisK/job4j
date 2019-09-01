package ru.job4j.io;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SearchTest {
    private Search search;

    @Before
    public void setUp() {
        search = new Search();
    }

    @Test
    public void whenFileSearchByGivenFormatsThenResultTrue() {
        List<File> listFiles = search.files("C:\\Users\\user\\Desktop\\job4j\\test", List.of(".txt"));
        List<String> expected = List.of("1.txt", "2.txt", "test2.txt", "test21.txt");
        List<String> result = listFiles.stream().map(File::getName).collect(Collectors.toList());
        assertThat(result, is(expected));
    }
}