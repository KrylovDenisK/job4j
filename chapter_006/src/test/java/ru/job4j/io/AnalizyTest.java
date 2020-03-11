package ru.job4j.io;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.StringJoiner;
import java.util.TreeMap;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {
    private Analizy analizy;

    @Before
    public void beforeTest() {
        analizy = new Analizy();
    }

    @Test
    public void whenConvertDataFileToMapResultTrue() {
        Map<String, String> expect = new TreeMap<>();
        expect.put("10:56:01", "200");
        expect.put("10:57:01", "500");
        expect.put("10:58:01", "400");
        expect.put("10:59:01", "200");
        expect.put("11:01:02", "500");
        expect.put("11:02:02", "200");
        analizy.convertDataFileToMap(Analizy.class.getClassLoader().getResource("server.log").getPath());
        assertThat(analizy.getValues(), is(expect));
    }

    @Test
    public void whenSearchErrorsLogFileThenResultTrue() {
        StringJoiner expected = new StringJoiner(System.lineSeparator());
        expected.add("10:57:01;10:59:01");
        expected.add("11:01:02;11:02:02");
        analizy.setValues("10:56:01", "200");
        analizy.setValues("10:57:01", "500");
        analizy.setValues("10:58:01", "400");
        analizy.setValues("10:59:01", "200");
        analizy.setValues("11:01:02", "500");
        analizy.setValues("11:02:02", "200");
        analizy.searchErrorsLogFile();
        assertThat(analizy.getOutJoiner().toString(), is(expected.toString()));
    }


}