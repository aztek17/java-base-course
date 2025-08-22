package homeworks.homework11;

import java.util.ArrayList;
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
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("a123me", "Mercedes", "White", 0, 8300000));
        carList.add(new Car("b873of", "Volga", "Black", 0, 673000));
        carList.add(new Car("w487mn", "Lexus", "Grey", 76000, 900000));
        carList.add(new Car("p987hj", "Volga", "Red", 610, 704340));
        carList.add(new Car("c987ss", "Toyota", "White", 254000, 761000));
        carList.add(new Car("o983op", "Toyota", "Black", 698000, 740000));
        carList.add(new Car("p146op", "BMW", "White", 271000, 850000));
        carList.add(new Car("u893ii", "Toyota", "Purple", 210900, 440000));
        carList.add(new Car("l097df", "Toyota", "Black", 108000, 780000));
        carList.add(new Car("y876wd", "Toyota", "Black", 160000, 1000000));

        System.out.println("Автомобили в базе:\n" + "   Number    " + "Model    " + "Color    " + "Mileage    " + "Cost");
        System.out.println(carList);

        List<String> colorOrWithoutMileage = carList
                .stream()
                .filter(colorOrMileageFilter -> (colorOrMileageFilter.getColor().equals(colorToFind)) || colorOrMileageFilter.getMileage() == mileageToFind)
                .map(Car::getNumber)
                .toList();
        System.out.println("Номера автомобилей по цвету или пробегу: " + colorOrWithoutMileage);

        long uniqueModelInRange = carList
                .stream()
                .filter(uniqueModel -> (uniqueModel.getCost() >= minPrice && uniqueModel.getCost() <= maxPrice))
                .map(Car::getModel)
                .distinct()
                .count();
        System.out.println("Уникальные автомобили: " + uniqueModelInRange + " шт.");

        String colorMinCost = carList
                .stream()
                .min(Comparator.comparingLong(Car::getCost))
                .map(Car::getColor)
                .orElse("empty");
        System.out.println("Цвет автомобиля с минимальной стоимостью: " + colorMinCost);

        for (String carModel : modelsToFind) {
            Double averageCost = carList
                    .stream()
                    .filter(model -> model.getModel().equals(carModel))
                    .collect(Collectors.averagingLong(Car::getCost));
            System.out.println("Средняя стоимость модели " + carModel + ": " + averageCost);
        }

    }
}