package ru.job4j.search;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class UserConvert {
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        list.forEach(x -> result.put(x.getId(), x));
        return result;
    }
}
