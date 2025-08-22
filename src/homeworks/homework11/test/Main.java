package homeworks.homework11.test;

import homeworks.homework11.model.Car;
import homeworks.homework11.repository.CarRepository;
import homeworks.homework11.repository.CarRepositoryImpl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static final String colorToFind = "Black";
    private static final long mileageToFind = 0;
    private static final long minPrice = 700000;
    private static final long maxPrice = 800000;
    private static final List<String> modelsToFind = Arrays.asList("Toyota", "Volvo");


    public static void main(String[] args) {
        CarRepository repository = new CarRepositoryImpl();
        List<Car> carList = repository.findAll();
        repository.save("Автомобили в базе:\n" + "   Number    " + "Model    " + "Color    " + "Mileage    " + "Cost");
        repository.save(carList.toString());

        String colorOrWithoutMileage = carList
                .stream()
                .filter(colorOrMileageFilter -> (colorOrMileageFilter.getColor().equals(colorToFind))
                        || colorOrMileageFilter.getMileage() == mileageToFind)
                .map(Car::getNumber)
                .collect(Collectors.joining(" "));
        repository.save("Номера автомобилей по цвету или пробегу: " + colorOrWithoutMileage);
        ;

        long uniqueModelInRange = carList
                .stream()
                .filter(uniqueModel -> (uniqueModel.getCost() >= minPrice && uniqueModel.getCost() <= maxPrice))
                .map(Car::getModel)
                .distinct()
                .count();
        repository.save("Уникальные автомобили: " + uniqueModelInRange + " шт.");

        String colorMinCost = carList
                .stream()
                .min(Comparator.comparingLong(Car::getCost))
                .map(Car::getColor)
                .orElse("empty");
        repository.save("Цвет автомобиля с минимальной стоимостью: " + colorMinCost);

        for (String carModel : modelsToFind) {
            Double averageCost = carList
                    .stream()
                    .filter(model -> model.getModel().equals(carModel))
                    .collect(Collectors.averagingLong(Car::getCost));
            repository.save("Средняя стоимость модели " + carModel + ": " + averageCost);
        }
    }
}
