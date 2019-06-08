package ru.job4j.chess;

import javafx.scene.control.Alert;
import ru.job4j.chess.exeptions.FigureNotFoundException;
import ru.job4j.chess.exeptions.ImpossibleMoveException;
import ru.job4j.chess.exeptions.OccupiedWayException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    /**
     * Добавление шахматной фигуры в хранилище
     * @param figure
     */
    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    /**
     * Попытка передвижения фигуры
     * @param source текущее положениие фигуры
     * @param dest требуемое положение
     * @return Результат передвижения (да \ нет)
     */
    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException {
        boolean rst = false;
        int index = this.findBy(source);
        if (index != -1) {
            Cell[] steps = this.figures[index].way(source, dest);
                if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                     if (notFreeCell(steps)) {
                         throw new OccupiedWayException("Занят путь");
                     }
                     if (freeWay(steps)) {
                         rst = true;
                         this.figures[index] = this.figures[index].copy(dest);
                     } else {
                         throw new OccupiedWayException("Занят путь");
                        }
                }
        } else {
            throw new FigureNotFoundException("фигура не найдена");
        }
        return rst;
    }
    private boolean freeWay (Cell[] steps) {
        boolean result = true;
        for (int i = 1; i < steps.length - 1; i++) {
            for (int j = 0; j < this.figures.length; j++) {
                if (steps[i].x == this.figures[j].position().x && steps[i].y == this.figures[j].position().y) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    private boolean notFreeCell (Cell[] steps) {
        boolean result = false;
        for (int i = 0; i < this.figures.length; i++) {
            if (this.figures[i].position().x == steps[steps.length - 1].x && this.figures[i].position().y == steps[steps.length - 1].y) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Очистить массив с ФИГУРАМИ
     */
    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }
   /**
    *   Поиск фигуры на доске по адресу ячейки
    */
    private int findBy(Cell cell) {
        return IntStream.range(0, figures.length).filter(i -> figures[i].position().equals(cell)).findFirst().orElse(-1);
    }
}
