package homeworks.homework02;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner firstScanner = new Scanner(System.in);
        System.out.println("Введите первое целое число: ");
        int firstNumber = firstScanner.nextInt();

        Scanner secondScanner = new Scanner(System.in);
        System.out.println("Введите второе целое число: ");
        int secondNumber = secondScanner.nextInt();

        System.out.println("Сумма двух целых чисел: " + (firstNumber + secondNumber));
        System.out.println("Разница двух целых чисел: " + (firstNumber - secondNumber));
        System.out.println("Произведение из двух целых чисел: " + (firstNumber * secondNumber));
        System.out.println("Среднее из двух целых чисел: " + ((firstNumber + secondNumber) / 2.0));
        System.out.println("Расстояние двух целых чисел: " + (Math.abs(firstNumber - secondNumber)));
        System.out.println("Максимальное целое число: " + (Math.max(firstNumber, secondNumber)));
        System.out.println("Минимальное целое число: " + (Math.min(firstNumber, secondNumber)));
    }
}
