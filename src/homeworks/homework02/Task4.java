package homeworks.homework02;

import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scannerCount = new Scanner(System.in);
        System.out.println("Введите число строк и столбцов сетки: ");
        int count = scannerCount.nextInt();

        Scanner scannerSymbol = new Scanner(System.in);
        System.out.println("Введите повторяемый элемент сетки: ");
        String repeatSymbol = scannerSymbol.next();

        System.out.println("Сетка по запросу " + count + " на " + count);
        int countPrintedRow = 0;
        while (countPrintedRow < count) {
            System.out.println(repeatSymbol.repeat(count));
            countPrintedRow++;
        }
    }
}
