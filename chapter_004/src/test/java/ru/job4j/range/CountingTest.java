package ru.job4j.range;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class CountingTest {
    @Test
    public void whenLinearFunctionThenLinearResults() {
        List<Double> result = Counting.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }
    @Test
    public void whenQuadraticFunctionThenQuadraticResult() {
        List<Double> result = Counting.diapason(1, 4, x -> Math.pow(x, 2));
        List<Double> expect = Arrays.asList(1D, 4D, 9D);
        assertThat(result, is(expect));
    }
    @Test
    public void whenLogarifmFunctionThenLogarifmResult() {
        List<Double> result = Counting.diapason(2, 5, x -> Math.log(x) / Math.log(2));
        List<Double> expect = Arrays.asList(1D, Math.log(3) / Math.log(2), 2D);
        assertThat(result, is(expect));
    }
}
