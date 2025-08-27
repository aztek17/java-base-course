package homeworks.homework12.task1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите данные человека: ");
        Scanner input = new Scanner(System.in);
        try {
            Person person = new Person(input.nextLine());

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/homeworks/homework12/task1/" +
                    person.getLastName() + ".txt", true))) {
                writer.write(person.toString());
                writer.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
