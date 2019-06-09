package ru.job4j;

import org.junit.Test;
import ru.job4j.range.Counting;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StudentsTest {
    @Test
    public void whenQuadraticFunctionThenQuadraticResult() {
        List<Students> list = Arrays.asList(new Students("Александр", 50),
                                            new Students("Борис", 25),
                                            null,
                                            new Students("Владимир", 75),
                                            null,
                                            new Students("Дмитрий", 16));
        List<Students> expect = Arrays.asList(new Students("Владимир", 75),
                                              new Students("Александр", 50));
        assertThat(Students.levelOf(list, 49), is(expect));
    }
}