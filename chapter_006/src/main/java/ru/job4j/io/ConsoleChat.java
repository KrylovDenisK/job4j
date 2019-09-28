package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleChat {
    private final Scanner scanner = new Scanner(System.in);
    private List<String> wordsFile = new ArrayList<>();
    private final Random random = new Random();
    private static final Logger LOGGER = LogManager.getLogger(ConsoleChat.class.getName());
    private static final String STOP = "stop";
    private static final String END = "end";
    private static final String CONTINUATION = "continuation";
    private boolean enable = true;

    /**
     * Write date from file to list 'wordFile'
     */
    private void readWordsTheFile() {
        try (BufferedReader read = new BufferedReader(new FileReader(ConsoleChat.class.getClassLoader().getResource("input.txt").getPath()))) {
           wordsFile = read.lines().map(String::trim).flatMap(p -> Arrays.stream(p.split(" "))).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void question() {
        readWordsTheFile();
        String question;
        System.out.println("Console chat!!!");
        do {
            String answer = "";
            System.out.print("Enter your line: ");
            question = scanner.nextLine();
            if (question.equals(STOP)) {
                enable = false;
            } else if (question.equals(CONTINUATION)) {
                enable = true;
                answer = getAnswer();
                System.out.println(answer);
            } else {
                if (enable && !question.equals(END)) {
                    answer = getAnswer();
                    System.out.println(answer);
                }
            }
            LOGGER.info("Question: " + question + " " + "Answer: " + answer);
        } while (!question.equals(END));
    }

    /**
     * Getting a random list item whose contents are read from a text file
     * @return random item from list
     */
    private String getAnswer() {
        int index = random.nextInt(wordsFile.size());
        return wordsFile.get(index);
    }

    public static void main(String...args) {
        new ConsoleChat().question();
    }
}
