package ru.job4j.socket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.socket.input.Input;
import ru.job4j.socket.input.StubInput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.StringJoiner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenSpeckExitThenResultTrue() throws IOException {
        StringJoiner expected = new StringJoiner(System.lineSeparator()).add("exit" + System.lineSeparator());
        StringJoiner outServer = new StringJoiner(System.lineSeparator()).add("exit").add(System.lineSeparator());
        this.socketServer(outServer.toString(), expected.toString(), new StubInput(new String[] {"exit"}));
    }

    @Test
    public void whenSpeckHelloThenResultTrue() throws IOException {
        StringJoiner expected = new StringJoiner(System.lineSeparator())
                .add("Hello, dear friend, I'm a oracle.")
                .add("exit" + System.lineSeparator());
        StringJoiner outServer = new StringJoiner(System.lineSeparator())
                .add("Hello, dear friend, I'm a oracle." + System.lineSeparator())
                .add("exit").add(System.lineSeparator());
        this.socketServer(outServer.toString(), expected.toString(), new StubInput(new String[] {"hello,", "exit"}));
    }

    private void socketServer(String outServer, String expected, Input input) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(outServer.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Client client = new Client(socket, input);
        client.connectionToServer();
        assertThat(this.out.toString(), is(expected));
    }
}