package repository.impl;

import car.Car;
import car.PerformanceCar;
import car.ShowCar;
import repository.CarRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarRepositoryImpl implements CarRepository {

    private final List<Car> LIST_CARS = new ArrayList<>();
        private static final String FILE_PATH = "src/main/resources/cars.txt";
    // Для запуска из IDE заменить FILE_PATH на полный путь src/homeworks/homeworks16/src/main/resources/cars.txt

    public CarRepositoryImpl() {
        LIST_CARS.addAll(readFile());
    }

    private List<Car> readFile() {
        final List<Car> carsFromFile = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH))) {
            for (String carLine : bufferedReader.lines().toList()) {
                String[] parts = carLine.split(",");
                String carType = parts[0].trim();

                switch (carType) {
                    case "BASE":
                        carsFromFile.add(new Car(carLine));
                        break;
                    case "SPORT":
                        carsFromFile.add(new PerformanceCar(carLine));
                        break;
                    case "SHOW":
                        carsFromFile.add(new ShowCar(carLine));
                        break;
                    default:
                        System.err.println("Unknown car type: " + carType);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + e.getMessage(), e);
        }
        return carsFromFile;
    }

    @Override
    public Car findByModel(String model) {
        return LIST_CARS.stream().filter(modelCar -> modelCar.getModel().equals(model)).toList().getFirst();
    }

    @Override
    public List<Car> findAll() {
        return LIST_CARS;
    }

}
