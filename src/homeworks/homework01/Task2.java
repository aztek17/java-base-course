package homeworks.homework01;

import java.util.Random;

public class Task2 {
    public static void main(String[] args) {
        Random random = new Random();
        int petr = random.nextInt(3);
        int vasiliy = random.nextInt(3);
        System.out.println(petr);
        System.out.println(vasiliy);
        if (petr == vasiliy) {
            System.out.println("Ничья!");
        } else if ((petr == 0 & vasiliy == 1) || (petr == 1 && vasiliy == 2) || (petr == 2 && vasiliy == 0)) {
            System.out.println("Выиграл Петр");
        } else {
            System.out.println("Выиграл Василий");
        }
    }
}
