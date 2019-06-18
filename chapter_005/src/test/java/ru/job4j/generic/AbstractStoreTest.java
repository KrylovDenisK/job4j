package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class AbstractStoreTest {
    private UserStore userStore = new UserStore(10);
    private RoleStore roleStore = new RoleStore(10);
    @Before
    public void setUp() {
        for (int i = 0; i < 10; i++) {
            userStore.add(new User(Integer.toString(i)));
            roleStore.add(new Role(Integer.toString(i)));
        }
    }

    @Test
    public void whenReplaceItem() {
        User user = new User("100");
        Role role = new Role("120");
        userStore.replace("0", user);
        roleStore.replace("8", role);
        assertThat(userStore.findById("100"), is(user));
        assertThat(roleStore.findById("120"), is(role));


    }

    @Test
    public void whenDeleteItem() {
        userStore.delete("100");
        roleStore.delete("120");
        assertThat(userStore.findById("100"), is(nullValue()));
        assertThat(roleStore.findById("120"), is(nullValue()));

    }


}