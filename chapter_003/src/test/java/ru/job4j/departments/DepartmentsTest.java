package ru.job4j.departments;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DepartmentsTest {
    @Test
    public void whenSortAsc() {
        Departments departments = new Departments();
        List<String> input = Arrays.asList("K1/SP2/SPP1", "K2/SP1/SPP2", "K1/SP2/SPP2", "K1/SP1/SPP2", "K2/SP2/SPP1");
        List<String> expect = Arrays.asList("K1", "K1/SP1", "K1/SP1/SPP2", "K1/SP2", "K1/SP2/SPP1",
                "K1/SP2/SPP2", "K2", "K2/SP1", "K2/SP1/SPP2", "K2/SP2", "K2/SP2/SPP1");

        List<String> result = departments.convert(input);
        assertThat(result, is(expect));
    }

    @Test
    public void whenSortDesc() {
        Departments departments = new Departments();
        List<String> input = Arrays.asList("K1/SP2/SPP1", "K2/SP1/SPP2", "K1/SP2/SPP2", "K1/SP1/SPP2", "K2/SP2/SPP1");
        List<String> expect = Arrays.asList("K2", "K2/SP2", "K2/SP1", "K2/SP2/SPP1", "K2/SP1/SPP2",
                "K1", "K1/SP2", "K1/SP1", "K1/SP2/SPP2", "K1/SP2/SPP1", "K1/SP1/SPP2");
        List<String> result = departments.sortDesc(departments.convert(input));
        assertThat(result, is(expect));
    }
}