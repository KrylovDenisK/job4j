package ru.job4j;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Students {
    private String name;
    private int scope;

    public int getScope() {
        return scope;
    }

    public Students(String name, int scope) {
        this.name = name;
        this.scope = scope;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Students students = (Students) o;
        return scope == students.scope && Objects.equals(name, students.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, scope);
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' + ", scope=" + scope + '}';
    }

    public static List<Students> levelOf(List<Students> list, int bound) {
        return list.stream().flatMap(Stream::ofNullable)
                .sorted((o1, o2) -> o2.getScope() - o1.getScope())
                .takeWhile(x -> x.getScope() > bound).collect(Collectors.toList());
    }

}
