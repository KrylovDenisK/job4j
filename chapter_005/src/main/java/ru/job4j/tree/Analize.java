package ru.job4j.tree;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Analize {

    /**
    *  Статистика об изменении коллекции
    */
    public Info diff(List<User> previous, List<User> current) {
        int added = 0, changed = 0, deleted;
        boolean isAdded;
        HashMap<User, Integer> previousMap = transformToMap(previous);
        for (User currentUser : current) {
            isAdded = true;
            for (User user : previousMap.keySet()) {
                if (user.id == currentUser.id) {
                    if (!user.name.equals(currentUser.name)) {
                        changed++;
                    }
                    int value = previousMap.get(user);
                    Integer oldValue = value == 1 ? previousMap.remove(user) : previousMap.put(user, value - 1);
                    isAdded = false;
                    break;
                }
            }
            if (isAdded) {
                added++;
            }
        }
        deleted = previousMap.values().stream().mapToInt(Integer::intValue).sum();
        return new Info(added, changed, deleted);
    }

    /**
     *  Копирование List в Map
     */
    public HashMap<User, Integer> transformToMap(List<User> list) {
        HashMap<User, Integer> map = new HashMap<>();
        for (User user : list) {
            if (!map.containsKey(user)) {
                map.put(user, 1);
            } else {
                int value = map.get(user) + 1;
                map.put(user, value);
            }
        }
        return map;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;
            return added == info.added
                    && changed == info.changed
                    && deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            return Objects.hash(added, changed, deleted);
        }
    }
}
