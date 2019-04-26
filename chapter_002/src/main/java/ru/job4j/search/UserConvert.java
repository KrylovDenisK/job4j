package ru.job4j.search;

import java.util.HashMap;
import java.util.List;

public class UserConvert {
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        for (User item: list) {
            result.put(item.getId(), item);
        }
        return result;
    }
}
