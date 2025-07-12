package homeworks.homework04;

import java.util.Arrays;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите искомую строку, в которой 2 слова, разделенные пробелом: ");
        String input = scanner.nextLine();

        String[] words = input.split(" ");
        for (String word : words) {
            char[] tempArray = word.toCharArray();
            Arrays.sort(tempArray);
            System.out.println(new String(tempArray).toLowerCase());
        }
    }
}
