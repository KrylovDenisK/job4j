package ru.job4j.max;

/**
 * @author  Denis Krylov
 * @version $Id$
 * @since 0.1
 */
public class Max {
    /**
     * Возвращаетт максимум из двух чисел
     * @param first первое число.
     * @param second второе число.
     * @return результат сравнения.
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }
}
