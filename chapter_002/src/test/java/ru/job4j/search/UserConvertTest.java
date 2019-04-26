package ru.job4j.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {
    @Test
    public void whenHigherPriority() {
        UserConvert userConvert = new UserConvert();
        User userExpect = new User(1, "Cody", "Orlando");
                List<User> users = new ArrayList<>(
                Arrays.asList(
                    new User(0, "Alex", "Moscow"),
                    userExpect
                )
        );
        HashMap<Integer, User> result = userConvert.process(users);
        assertThat(result.get(1), is(userExpect));
    }
}
