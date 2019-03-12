package ru.job4j.tracker.inputs;
import ru.job4j.tracker.inputs.Input;

import java.util.*;
public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }
}
