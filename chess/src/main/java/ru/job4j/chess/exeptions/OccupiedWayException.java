package ru.job4j.chess.exeptions;

public class OccupiedWayException extends RuntimeException {
    public  OccupiedWayException(String message) {
        super(message);
    }
}
