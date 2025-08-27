package homeworks.homework12.task2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите логин");
        String login = scanner.nextLine();
        System.out.println("Введите пароль");
        String password = scanner.nextLine();
        System.out.println("Введите подтверждение пароля");
        String confirmPassword = scanner.nextLine();
        boolean valid;

        valid = User.validateAuthData(login, password, confirmPassword);
        System.out.println("Валидность данных пользователя: " + valid);
    }
}
