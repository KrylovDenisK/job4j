package ru.job4j.chess.exeptions;

public class ImpossibleMoveException extends RuntimeException {
    public ImpossibleMoveException (String message) {
        super(message);
    }
}
