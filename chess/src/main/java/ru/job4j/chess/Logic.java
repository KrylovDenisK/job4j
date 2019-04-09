package ru.job4j.chess;

import javafx.scene.control.Alert;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.Optional;

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
    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        int index = this.findBy(source); // поиск фигуры в массиве фигур
        if (index != -1) { // Если нашли
            Cell[] steps = this.figures[index].way(source, dest);
                if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                    if (freeCell(steps)) {
                        rst = true;
                        this.figures[index] = this.figures[index].copy(dest);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("ОШИБКА");
                        alert.setHeaderText("Одна из клеток занята");
                        alert.setContentText("Повторите свой ход");
                        alert.showAndWait();
                    }
                }
        }
        return rst;
    }
    private boolean freeCell (Cell[] steps) {
        boolean result = true;
        for (int i = 1; i < steps.length; i++) {
            for (int j = 0; j < figures.length; j++) {
                if (steps[i].x == figures[j].position().x && steps[i].y == figures[j].position().y) {
                    result = false;
                }
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
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}
