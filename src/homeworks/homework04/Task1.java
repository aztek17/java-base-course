package homeworks.homework04;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        String keyboard = "qwertyuiopasdfghjklzxcvbnm";

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите маленькую букву английского алфавита");
        String letter = scanner.next();

        if (keyboard.indexOf(letter) == 0) {
            System.out.println(keyboard.charAt(keyboard.length() - 1));
        } else {
            System.out.println(keyboard.charAt(keyboard.indexOf(letter) - 1));
        }
    }

}
