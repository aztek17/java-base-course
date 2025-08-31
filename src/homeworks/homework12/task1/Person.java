package homeworks.homework12.task1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Person {
    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDate birthday;
    private long phoneNumber;
    private char sex;
    private int age;

    public Person(String line) throws IllegalArgumentException {
        String[] parsedLine = line.split(" ");
        if (parsedLine.length != 7) {
            throw new IllegalArgumentException("Неверное количество введенных данных");
        }

        setLastName(parsedLine[0]);
        setFirstName(parsedLine[1]);
        setMiddleName(parsedLine[2]);
        setBirthday(parsedLine[3]);
        setPhoneNumber(parsedLine[4]);
        setSex(parsedLine[5]);
        setAge(parsedLine[6]);
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) throws IllegalArgumentException {
        try {
            this.birthday = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException("Некорректный формат даты рождения");
        }
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws NumberFormatException {
        this.phoneNumber = Long.parseLong(phoneNumber);
    }

    public char getSex() {
        return sex;
    }

    public void setSex(String sex) throws IllegalArgumentException {
        if (sex.length() != 1) {
            throw new IllegalArgumentException("Размерность поля \"Пол\" должно быть равно одному символу");
        } else if (sex.charAt(0) != 'f' && sex.charAt(0) != 'm') {
            throw new IllegalArgumentException("Для пола допустимые символы f или m");
        }
        this.sex = sex.charAt(0);
    }

    public int getAge() {
        return age;
    }

    public void setAge(String age) throws NumberFormatException {
        this.age = Integer.parseInt(age);
    }

    @Override
    public String toString() {
        return lastName + ' ' +
                firstName + ' ' +
                middleName + ' ' +
                birthday + ' ' +
                phoneNumber + ' ' +
                sex + ' ';
    }
}
