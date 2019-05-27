package ru.job4j.search;

import java.util.Comparator;

public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int lenLeft = left.length();
        int lenRight = right.length();
        int len = Math.min(lenLeft, lenRight);
        for (int i = 0; i < len; i++) {
            if (left.charAt(i) != right.charAt(i)) {
                return left.charAt(i) - right.charAt(i);
            }
        }
        return lenLeft - lenRight;
    }
}
