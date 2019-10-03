package ru.job4j.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.net.Socket;

public class Server {
    private final Socket socket;
    private final static String HELLO = "hello";
    private final static String EXIT = "exit";

    public Server(Socket socket) {
        this.socket = socket;
    }

    public void connectionToClient() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String ask;
        do {
            System.out.println("wait command ...");
            ask = in.readLine();
            System.out.println(ask);
            if (HELLO.equals(ask)) {
                out.println("Hello, dear friend, I'm a oracle.");
                out.println();
            } else {
                out.println(ask);
                out.println();
            }
        } while (!EXIT.equals(ask));
    }
    public static void main(String...args) throws Exception {
         try (final Socket socket = new ServerSocket(5000).accept()) {
             new Server(socket).connectionToClient();
        }
    }
}
