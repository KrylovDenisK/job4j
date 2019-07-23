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
        HashMap<Integer, User> previousMap = transformToMap(previous);
        for (User currentUser : current) {
           User user = previousMap.remove(currentUser.id);
           if (Objects.nonNull(user)) {
                if (!currentUser.name.equals(user.name)) {
                    changed++;
                }
           } else {
               added++;
           }
        }
        deleted = previousMap.values().size();
        return new Info(added, changed, deleted);
    }

    /**
     *  Копирование List в Map
     */
    public HashMap<Integer, User> transformToMap(List<User> list) {
        HashMap<Integer, User> map = new HashMap<>();
        for (User user : list) {
           map.put(user.id, user);
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
