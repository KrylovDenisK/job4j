package ru.job4j.map;

import java.util.*;
import java.util.HashMap;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children
                && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public String toString() {
        return "User{"
                +  "name='" + name + '\''
                +  '}';
    }

    public static void main(String[] arqs) {
       // Map<User, Object> map = new HashMap<>();
//        map.put(new User("name1"), 1);
        //map.forEach((k, v) -> System.out.println(k.toString() + v));
        Map<User, Object> map = new HashMap<>();
        map.put(null, 5);
        int index = 0;

    }
}
