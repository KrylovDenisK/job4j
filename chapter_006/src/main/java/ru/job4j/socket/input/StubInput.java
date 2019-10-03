package ru.job4j.socket.input;

public class StubInput implements Input {
    private String[] questions;
    private int pos;

    public StubInput(String[] questions) {
        this.questions = questions;
    }

    @Override
    public String ask(String question) {
        return this.questions[this.pos++];
    }
}
