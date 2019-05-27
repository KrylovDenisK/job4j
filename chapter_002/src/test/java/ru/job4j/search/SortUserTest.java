package ru.job4j.search;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {
    @Test
    public void whenSortUp() {
       SortUser sortUser = new SortUser();
        List<User> users = new ArrayList<>(
                Arrays.asList(
                        new User(0, "Alex", "Moscow", 37),
                        new User(2, "Niko", "New York", 18),
                        new User(1, "Cody", "Orlando", 25)
                )
        );
        Set<User> expect = new TreeSet<>();
        expect.add(new User(2, "Niko", "New York", 18));
        expect.add(new User(1, "Cody", "Orlando", 25));
        expect.add(new User(0, "Alex", "Moscow", 37));
        assertThat(sortUser.sort(users), is(expect));
    }
   @Test
    public void whenSortByNameLenght() {
        SortUser sortUser = new SortUser();
        List<User> users = new ArrayList<>(
                Arrays.asList(
                        new User(0, "Сергей", "Moscow", 25),
                        new User(2, "Иван", "New York", 30),
                        new User(1, "Сергей", "Orlando", 20),
                        new User(3, "Иван", "Maiami", 25)
                )
        );

        List<User> expect = new ArrayList<>(
                Arrays.asList(
                        new User(2, "Иван", "New York", 30),
                        new User(3, "Иван", "Maiami", 25),
                        new User(0, "Сергей", "Moscow", 25),
                        new User(1, "Сергей", "Orlando", 20)
                )
        );
        assertThat(sortUser.sortNameLength(users), is(expect));
    }

    @Test
    public void whenSortByAllFields() {
        SortUser sortUser = new SortUser();
        List<User> users = new ArrayList<>(
                Arrays.asList(
                        new User(0, "Сергей", "Moscow", 25),
                        new User(2, "Иван", "New York", 30),
                        new User(1, "Сергей", "Orlando", 20),
                        new User(3, "Иван", "Maiami", 25)
                )
        );
        List<User> expect = new ArrayList<>(
                Arrays.asList(
                        new User(3, "Иван", "Maiami", 25),
                        new User(2, "Иван", "New York", 30),
                        new User(1, "Сергей", "Orlando", 20),
                        new User(0, "Сергей", "Moscow", 25)
                )
        );
        assertThat(sortUser.sortByAllFields(users), is(expect));
    }

}


