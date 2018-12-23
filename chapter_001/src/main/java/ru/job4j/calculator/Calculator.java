package ru.job4j.calculator;
/**
 * Calculator Класс описывает работу простых функция калькулятора
 * @author krylov
 * @since 20.12.2018
 * @version 1

 */
public class Calculator {
	/** Поле хранит результат выполнения метода класса*/
	private double result;
    /**
     Сложение двух чисел
     @param first - первое число
     @param second - второе число
     */
	public void add(double first, double second) {
		this.result = first + second;
	}
    /**
     Разность чисел
     @param first - первое число
     @param second - второе число
     */
	public void subtract(double first, double second) {
		this.result = first - second;
	}
    /**
    Деление двух чисел
     @param first - первое число
     @param second - второе число
     */
	public void div(double first, double second) {
		this.result = first / second;
	}
    /**
     Умножение двух чисел
     @param first - первое число
     @param second - второе число
     */
	public void multiplay(double first, double second) {
		this.result = first * second;
	}
    /**
     Получение значения поля result
     */
	public double getResult() {
		return result;
	}
}