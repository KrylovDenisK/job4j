package ru.job4j.departments;

import java.util.*;

public class Departments {
    private final List<String> deps = new ArrayList<>();

    /**
     * add missing departments
     * @param deps
     * @return sorted list departments
     */
    public List<String> convert(List<String> deps) {
        Set<String> result = new TreeSet<>();
        for (String dep : deps) {
            char[] strChar = dep.toCharArray();
            for (int i = 0; i < strChar.length; i++) {
                if (strChar[i] == '/') {
                    result.add(dep.substring(0, i));
                }
                result.add(dep);
            }
        }
        return new ArrayList<>(result);
    }

    /**
     * Sort descending
     * @param orgs
     * @return sorted list
     */
    public List<String> sortDesc(List<String> orgs) {
        orgs.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int sepO1 = searchSeparate(o1);
                int sepO2 = searchSeparate(o2);
                String str1First = o1.substring(0, sepO1);
                String str2First = o2.substring(0, sepO2);
                int result = str2First.compareTo(str1First);
                String str1Second = o1.substring(sepO1);
                String str2Second = o2.substring(sepO2);
                return result!= 0 ? result : compareString(str2Second, str1Second);
            }
        });
        return orgs;
    }

    /**
     * search separatein string
     * @param str
     * @return index where separator is
     */
    private int searchSeparate(String str) {
        int result = str.length();
        char[] strChar = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (strChar[i] == '/') {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     *
     * Algorithm sorting descending
     * @param str1
     * @param str2
     * @return result sort
     */
    private int compareString(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length() ;
        if (len1!= len2) {
            return -(len1 - len2);
        }
        int lim = Math.min(len1, len2);
        char v1[] = str1.toCharArray();
        char v2[] = str2.toCharArray();
        int k = 0;
        while (k < lim) {
            char c1 = v1[k];
            char c2 = v2[k];
            if (c1 != c2) {
                return c1 - c2;
            }
            k++;
        }
        return -(len1 - len2);
    }
}
