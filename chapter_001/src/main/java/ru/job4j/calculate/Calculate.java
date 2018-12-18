package ru.job4j.calculate;
/**
 * Calculate Класс для вывода Hellow World  
 * @author krylov
 * @since 17.12.2018
 * @version 1
*/
public class Calculate{
	/**
	Вывод строки в консоль
	@param args - args
	
	*/
	public static void main(String[] args){
		System.out.println("Hellow world");
	}
	
	
	/**
	* Method echo.
	* @param name Your name.
	* @return Echo plus your name.
	*/
	public String echo(String name) {
		return "Echo, echo, echo : " + name;
	}
		
}