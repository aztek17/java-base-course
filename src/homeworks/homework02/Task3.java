package homeworks.homework02;

import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scannerPhrase = new Scanner(System.in);
        System.out.println("Исходная строка: ");
        String inputString = scannerPhrase.next();

        Scanner scannerRepeatCounter = new Scanner(System.in);
        System.out.println("Сколько раз вывести строку? ");
        int count = scannerRepeatCounter.nextInt();

        System.out.println("После повторения " + count + " раз: " + inputString.repeat(count));
    }

}
