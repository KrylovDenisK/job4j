package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
           List<String> result = read.lines()
                   .map(String::trim)
                   .filter(x -> !x.isEmpty() && !x.startsWith("#") && x.contains("="))
                   .flatMap(x -> Arrays.stream(x.split("=")))
                   .collect(Collectors.toList()
                   );
           IntStream.range(0, result.size()).filter(x -> x % 2 == 0).forEach(x -> values.put(result.get(x), result.get(x + 1)));
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
