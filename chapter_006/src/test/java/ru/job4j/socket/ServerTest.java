package ru.job4j.socket;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringJoiner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {
    @Test
    public void whenSpeckExitThenResultTrue() throws IOException {
        StringJoiner in = new StringJoiner(System.lineSeparator()).add("exit");
        StringJoiner out = new StringJoiner(System.lineSeparator()).add("exit").add(System.lineSeparator());
        this.socketServer(in.toString(), out.toString());
    }
    @Test
    public void whenSpeckHelloThenResultTrue() throws IOException {
        StringJoiner in = new StringJoiner(System.lineSeparator())
                .add("hello").add("exit").add(System.lineSeparator());
        StringJoiner out = new StringJoiner(System.lineSeparator())
                .add("Hello, dear friend, I'm a oracle." + System.lineSeparator())
                .add("exit").add(System.lineSeparator());
        this.socketServer(in.toString(), out.toString());
    }

    private void socketServer(String inStr, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(inStr.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);
        server.connectionToClient();
        assertThat(out.toString(), is(expected));
    }

}