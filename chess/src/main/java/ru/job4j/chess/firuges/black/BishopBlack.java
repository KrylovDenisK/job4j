package ru.job4j.chess.firuges.black;

import javafx.scene.control.Alert;
import ru.job4j.chess.exeptions.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {

        if (!isDiagonal(source, dest)) {
            throw new ImpossibleMoveException("Не диагональ");
        }
        int lenght = Math.abs(source.x - dest.x) + 1;
        Cell[] steps = new Cell[lenght];
        Cell cell = Cell.A1;
        int deltaX = source.x - dest.x;
        int deltaY = source.y - dest.y;

        if (deltaX < 0 && deltaY > 0) {
            for (int i = 0; i < lenght; i++) {
                steps[i] = cell.getValue(source.x + i, source.y - i);
            }
        } else if (deltaX > 0 && deltaY > 0) {
            for (int i = 0; i < lenght; i++) {
                steps[i] = cell.getValue(source.x - i, source.y - i);
            }
        } else if (deltaX < 0 && deltaY < 0) {
            for (int i = 0; i < lenght; i++) {
                steps[i] = cell.getValue(source.x + i, source.y + i);
            }
        } else if (deltaX > 0 && deltaY < 0) {
            for (int i = 0; i < lenght; i++)
                steps[i] = cell.getValue(source.x - i, source.y + i);
        }
        return steps;
        }


    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }

    private boolean isDiagonal(Cell sourse, Cell dest) {
        boolean result = false;
        if (Math.abs(sourse.x - sourse.y) == Math.abs(dest.x - dest.y) || sourse.x + sourse.y == dest.x + dest.y) {
            result = true;
        }
        return result;
    }
}
