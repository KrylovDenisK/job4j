package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest  {
    @Test
    public void whenAddOnePlusOneThenTwo()  {
        Calculator calc = new Calculator();
        calc.add(1, 1);
        double result = calc.getResult();
        double expected = 2;
        assertThat(result, is(expected));
    }
    @Test
    public void whenSubtractTwoMinusOneThenOne() {
        Calculator calc = new Calculator();
        calc.subtract(2, 1);
        double result = calc.getResult();
        double expected = 1;
        assertThat(result, is(expected));
    }
    @Test
    public void whenDiv2On2Then1() {
        Calculator calc = new Calculator();
        calc.div(2, 2);
        double result   = calc.getResult();
        double expected = 1;
        assertThat(result, is(expected));
    }
    @Test
    public void whenMultiple2On2Then4() {
        Calculator calc = new Calculator();
        calc.multiplay(2, 2);
        double result = calc.getResult();
        double expected = 4;
        assertThat(result, is(expected));
    }
}