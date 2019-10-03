package ru.job4j.socket;

import ru.job4j.socket.input.ConsoleInput;
import ru.job4j.socket.input.Input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


public class Client {
    private Input input;
    private final Socket socket;
    private final static String EXIT = "exit";

    public Client(Socket socket, Input input) {
        this.socket = socket;
        this.input = input;
    }
    public void connectionToServer() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String question;
        do {
            question = input.ask("Введите вопрос: ");
            out.println(question);
            String str = in.readLine();
            while (!str.isEmpty()) {
                System.out.println(str);
                str = in.readLine();
            }
        } while (!question.equals(EXIT));
    }
    public static void main(String...arg) throws Exception {
        try (final Socket socket = new Socket(InetAddress.getByName("192.168.55.210"), 5000)) {
            new Client(socket, new ConsoleInput()).connectionToServer();
        }


    }
}
