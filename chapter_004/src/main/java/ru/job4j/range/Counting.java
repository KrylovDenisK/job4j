package ru.job4j.range;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class Counting {
    public static List<Double> diapason(int start, int end, UnaryOperator<Double> func) {
        List<Double> result =  new ArrayList<>();
        for (int i = start; i < end; i++) {
            result.add(func.apply((double) i));
        }
        return result;
    }
}
