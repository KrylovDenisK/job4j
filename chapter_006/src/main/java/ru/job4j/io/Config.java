package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;


public class Config {
    private final String path;
    private Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
           values = read.lines()
                   .map(String::trim)
                   .filter(x -> !x.isEmpty() && !x.startsWith("#") && x.contains("="))
                   .map(x -> x.split("="))
                   .collect(Collectors.toMap(x -> x[0], x -> x[1]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
