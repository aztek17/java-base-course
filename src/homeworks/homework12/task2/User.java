package homeworks.homework12.task2;

import homeworks.homework12.task2.exceptions.WrongLoginException;
import homeworks.homework12.task2.exceptions.WrongPasswordException;

public class User {

    public static boolean validateAuthData(String login, String password, String confirmPassword) {
        try {
            if (login.length() >= 20 || login.isEmpty() || !login.matches("\\w+")) {
                throw new WrongLoginException("Некорректный формат логина");
            }

            if (password.length() >= 20 || password.isEmpty() || !password.matches("\\w+")) {
                throw new WrongPasswordException("Некорректный формат пароля");
            } else if (!password.equals(confirmPassword)) {
                throw new WrongPasswordException("Подтверждение пароля отличается от пароля");
            }

        } catch (WrongLoginException | WrongPasswordException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
        return true;
    }
}
