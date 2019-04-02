package ru.job4j.singleton;

public class TrackerSingle2 {
    private static TrackerSingle2 instance;
    private static int number = 0;
    public TrackerSingle2() {
        number++;
    }
    public static TrackerSingle2 getInstance() {
        if (instance == null) {
            instance = new TrackerSingle2();
        }
        System.out.println(number);
        return instance;
    }
}
