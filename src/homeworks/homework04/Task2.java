package homeworks.homework04;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите искому строку: ");
        String input = scanner.next();
        int countArrow = 0;

        Pattern arrowRight = Pattern.compile(">>->");
        Pattern arrowLeft = Pattern.compile("<-<<");

        Matcher matcherArrowLeft = arrowLeft.matcher(input);
        Matcher matcherArrowRight = arrowRight.matcher(input);
        while(matcherArrowLeft.find() || matcherArrowRight.find()) {
            countArrow++;
        }
        System.out.println("Количество стрелок в строке: " + countArrow);
    }

}
