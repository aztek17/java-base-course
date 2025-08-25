package homeworks.homework12.task2;

public class Main {
    public static void main(String[] args) {
        String login = "berserk_911";
        String password = "kresreb771_177";
        String confirmPassword = "kresreb771_177";
        boolean valid;

        valid = User.validateAuthData(login, password, confirmPassword);
        System.out.println("Валидность данных пользователя: " + valid);
    }
}
