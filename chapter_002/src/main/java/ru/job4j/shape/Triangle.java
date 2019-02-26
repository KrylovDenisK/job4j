package ru.job4j.shape;

public class Triangle implements Shape {
    public String draw() {
        StringBuilder result = new StringBuilder();
        result.append("  *  ");
        result.append(" *** ");
        result.append("*****");
        return result.toString();
    }
}
