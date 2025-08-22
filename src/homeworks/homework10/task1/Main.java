package homeworks.homework10.task1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] inputNumbers = new int[]{1, 22, 10, 5, 3, 15, 8, 11, 14};
        int[] evenNumbers = Sequence.filter(inputNumbers, number -> (number % 2) == 0);

        int[] sumEvenNumbers = Sequence.filter(inputNumbers, number -> {
            int sum = 0;
            String value = String.valueOf(number);
            for (int i = 0; i < value.length(); i++) {
                sum = sum + value.toCharArray()[i];
            }
            return (sum % 2) == 0;
        });

        System.out.println("Четные числа из переданного списка: " + Arrays.toString(evenNumbers));
        System.out.println("Числа, сумма цифр которых является четным числом: " + Arrays.toString(sumEvenNumbers));
    }
}
