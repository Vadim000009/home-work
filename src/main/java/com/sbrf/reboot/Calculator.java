package com.sbrf.reboot;

/**
 * Класс @Calculator
 * В классе определены методы:
 * add - сложение, sub - вычитание, mul - умножение,
 * div - деление, pow - квадрат, sqrt - корень
 */
public class Calculator {

    /**
     * Метод сложения
     * @param a - первый операнд
     * @param b - первый операнд
     * @return возвращает сумму чисел
     */
    public double getAddition(double a, double b) {
        return a + b;
    }

    /**
     * Метод вычитания
     * @param a - первый операнд
     * @param b - первый операнд
     * @return возвращает разность чисел
     */
    public double getSubtraction(double a, double b) {
        return a - b;
    }

    /**
     * Метод умножения
     * @param a - первый операнд
     * @param b - первый операнд
     * @return возвращает произведение чисел
     */
    public double getMultiplication(double a, double b) {
        return a * b;
    }

    /**
     * Метод деления
     * @param a - первый операнд
     * @param b - первый операнд
     * @return возвращает частное чисел
     */
    public double getDivision(double a, double b) {
        return a / b;
    }

    /**
     * Метод возведения в квадрат
     * @param a - операнд, на основании которого будет производиться возведение
     * @return возвращает число в квадрате
     */
    public double getPowerSquare(double a) {
        return a * a;
    }

    /**
     * Метод квадратного корня
     * @param a - число, из которого будет будет получен корень
     * @return возвращает целое рациональное число или дробное
     */
    public double getSqrt(double a) {
        return Math.sqrt(a);
    }

    /**
     * Метод возведения в куб
     * @param a - операнд, на основании которого будет производиться возведение
     * @return возвращает число в кубе
     */
    public double getPowerCube(double a) {
        return a * a * a;
    }
}