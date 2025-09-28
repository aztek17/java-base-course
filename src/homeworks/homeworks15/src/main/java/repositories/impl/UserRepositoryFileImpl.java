package repositories.impl;

import exception.UserNotFoundException;
import model.User;
import repositories.UserRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryFileImpl implements UserRepository {

    public UserRepositoryFileImpl() {
        USERS.addAll(readFile());
    }

    private final List<User> USERS = new ArrayList<>();
    private static final String FILE_NAME = "users.txt";

    @Override
    public void create(User user) {
        USERS.add(user);
        writeUsersToFile(USERS);
    }

    @Override
    public User findById(String id) {
        return USERS
                .stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("Пользователь с id = " + id + " не найден"));
    }

    @Override
    public List<User> findAll() {
        return USERS;
    }

    @Override
    public void update(User user) {
        User foundedUser;
        try {
            foundedUser = findById(user.getId());
            int indexFounded = USERS.indexOf(foundedUser);
            USERS.set(indexFounded, user);
            writeUsersToFile(USERS);
        } catch (UserNotFoundException e) {
            create(user);
        }
    }

    @Override
    public void deleteById(String id) {
        boolean removedUser = USERS.removeIf(user -> user.getId().equals(id));
        if (removedUser) {
            System.out.println("Пользователь с id = " + id + " успешно удален");
            writeUsersToFile(USERS);
        } else {
            throw new UserNotFoundException("Пользователя с заданным идентификатором не существует");
        }
    }

    @Override
    public void deleteAll() {
        USERS.clear();
        writeUsersToFile(USERS);
    }

    @Override
    public List<User> findByAge(int age) {
        return USERS
                .stream()
                .filter(e -> e.getAge() == age)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findByIsWorker(boolean isWorker) {
        return USERS
                .stream()
                .filter(e -> e.isWorker() == (isWorker))
                .collect(Collectors.toList());
    }

    private List<User> readFile() {
        final List<User> USERS;
        List<User> result;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/homeworks/homeworks15/src/main/resources/" + FILE_NAME))) {
            result = bufferedReader.lines()
                    .map(User::new)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Файл не найден, список пользователей пуст!");
            result = new ArrayList<>();
        }
        USERS = result;
        return USERS;
    }

    private void writeUsersToFile(List<User> users) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/homeworks/homeworks15/src/main/resources/" + FILE_NAME))) {
            users.stream()
                    .map(User::toString)
                    .forEach(line -> {
                        try {
                            bufferedWriter.write(line + "\n");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            System.out.println("Файл не найден, файл не обновлен!");
        }
    }
}
