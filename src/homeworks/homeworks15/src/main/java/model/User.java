package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class User {
    private String id;
    private LocalDateTime dateTime;
    private String login;
    private String password;
    private String confirmPassword;
    private String lastName;
    private String firstName;
    private String middleName;
    private int age;
    private boolean isWorker = false;
    private static List<String> LIST_IDS = new ArrayList<>();


    public static List<String> getListIds() {
        return LIST_IDS;
    }

    public static void setListIds(List<String> listIds) {
        LIST_IDS = listIds;
    }

    public User(String s) {
        List<String> params = Arrays.asList(s.split("\\|"));
        if (params.size() == 10) {
            setId(params.getFirst());
            setDateTime(LocalDateTime.parse(params.get(1)));
            setLogin(params.get(2));
            setPassword(params.get(3));
            setConfirmPassword(params.get(4));
            setLastName(params.get(5));
            setFirstName(params.get(6));
            setMiddleName(params.get(7));
            setAge(Integer.parseInt(params.get(8)));
            setWorker(Boolean.parseBoolean(params.get(9)));
        } else {
            throw new IllegalArgumentException("Не удалось считать данные из файла из-за некоректных полей");
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (!LIST_IDS.contains(id)) {
            this.id = id;
            LIST_IDS.add(id);
        } else {
            throw new IllegalArgumentException("ID уже используется");
        }
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login == null
                || login.length() >= 20
                || !login.matches("\\w+")
                || login.chars().noneMatch(Character::isLetter)) {
            throw new IllegalArgumentException("Логин не отвечает условиям");
        }
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null
                || password.length() >= 20
                || !password.matches("\\w+")
                || password.chars().noneMatch(Character::isDigit)) {
            throw new IllegalArgumentException("Пароль не отвечает условиям");
        }
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        if (!confirmPassword.equals(getPassword())) {
            throw new IllegalArgumentException("Пароль и подтверждение пароля не совпадают");
        }
        this.confirmPassword = confirmPassword;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (!lastName.matches("[a-zA-Zа-яА-Я]+")) {
            throw new IllegalArgumentException("Фамилия должна содержать только буквы");
        }
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (!firstName.matches("[a-zA-Zа-яА-Я]+")) {
            throw new IllegalArgumentException("Имя должно содержать только буквы");
        }
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        if (middleName != null && !middleName.isEmpty()) {
            if (!middleName.matches("[a-zA-Zа-яА-Я]+")) {
                throw new IllegalArgumentException("Отчество должно содержать только буквы");
            }
        }
        this.middleName = middleName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isWorker() {
        return isWorker;
    }

    public void setWorker(boolean worker) {
        isWorker = worker;
    }

    @Override
    public String toString() {
        return id + '|' +
                dateTime + '|' +
                login + '|' +
                password + '|' +
                confirmPassword + '|' +
                lastName + '|' +
                firstName + '|' +
                middleName + '|' +
                age + '|' +
                isWorker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (age != user.age) return false;
        if (isWorker != user.isWorker) return false;
        if (!id.equals(user.id)) return false;
        if (!dateTime.equals(user.dateTime)) return false;
        if (!login.equals(user.login)) return false;
        if (!password.equals(user.password)) return false;
        if (!confirmPassword.equals(user.confirmPassword)) return false;
        if (!lastName.equals(user.lastName)) return false;
        if (!firstName.equals(user.firstName)) return false;
        return Objects.equals(middleName, user.middleName);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + dateTime.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + confirmPassword.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (isWorker ? 1 : 0);
        return result;
    }

}
