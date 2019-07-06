package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class HashMapTest {

    HashMap<User, Integer> map = new HashMap<>();

    @Before
    public void setUp() {
        int i = 0;
        while (i < 5) {
            map.insert(new User("name " + i), i);
            i++;
        }
    }
    @Test
    public void whenGetThen() {
        assertThat(map.get(new User("name 0")), is(0));
        assertThat(map.get(new User("name 1")), is(1));
        assertThat(map.get(new User("name 2")), is(2));
        assertThat(map.get(new User("name 3")), is(3));
        assertThat(map.get(new User("name 4")), is(4));
        assertThat(map.get(new User("name 20")), is(nullValue()));
    }

    @Test
    public void whenDeleteThen() {
        assertThat(map.delete(new User("name 0")), is(true));
        assertThat(map.delete(new User("name 3")), is(true));
        assertThat(map.delete(new User("name 10")), is(false));
    }
    @Test(expected = ConcurrentModificationException.class)
    public void shouldReturnExeption() {
        Iterator<HashMap.Node<User, Integer>> it = map.iterator();
        map.insert(new User("name10"), 12);
        it.next();
    }

    @Test
    public void hasNextNextSequentialInvocation() {
        Iterator<HashMap.Node<User, Integer>> it = map.iterator();
        assertThat(it.hasNext(), is(true));
        it.next();
        assertThat(it.hasNext(), is(true));
        it.next();
        assertThat(it.hasNext(), is(true));
        it.next();
        assertThat(it.hasNext(), is(true));
        it.next();
        assertThat(it.hasNext(), is(true));
        it.next();
        assertThat(it.hasNext(), is(false));
    }

}