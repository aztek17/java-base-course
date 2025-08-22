package homeworks.homework11.repository;

import homeworks.homework11.model.Car;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarRepositoryImpl implements CarRepository {

    @Override
    public List<Car> findAll() {
        List<Car> carList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/homeworks/homework11/data/input.txt"))) {
            while (true) {
                String nextLine = reader.readLine();
                if (nextLine == null) {
                    break;
                }
                String[] parts = nextLine.split("\\|");
                carList.add(new Car(
                        parts[0],
                        parts[1],
                        parts[2],
                        Long.parseLong(parts[3]),
                        Long.parseLong(parts[4])));
            }
            return carList;
        } catch (IOException exception) {
            System.out.println("Не удалось считать данные автомобилей из файла");
            return Collections.emptyList();
        }
    }

    @Override
    public void save(String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/homeworks/homework11/data/output.txt", true))) {
            writer.write(text);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Не удалось записать результат в файл");
        }
    }
}
