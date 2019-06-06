package ru.job4j.search;

import java.util.*;
import java.util.stream.Collectors;

public class SortUser {
    public Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }

    public List<User> sortNameLength(List<User> list) {
        return list.stream().sorted(Comparator.comparing(user -> user.getName().length())).collect(Collectors.toList());
    }
    public List<User> sortByAllFields(List<User> list) {
        return list.stream().sorted(Comparator.comparing(User::getName).thenComparing(User::getAge)).collect(Collectors.toList());
    }
}
