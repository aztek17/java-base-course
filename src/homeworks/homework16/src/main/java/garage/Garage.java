package garage;

import car.Car;
import car.PerformanceCar;
import car.ShowCar;

import java.util.Arrays;
import java.util.Scanner;

public class Garage {
    Car[] parkedCars;

    public Garage(Car[] parkedCars) {
        this.parkedCars = parkedCars;
    }

    public Garage() {
    }

    public Car[] getParkedCars() {
        return parkedCars;
    }

    public void setParkedCars(Car[] parkedCars) {
        this.parkedCars = parkedCars;
        for (Car car : parkedCars) {
            System.out.println("Автомобиль " + car.getBrand() + " припаркован в гараж");
        }
        System.out.println("Информация об автомобилях в гараже: " + Arrays.toString(parkedCars));
    }

    public void upgradeCar(Car car) {
        if (Arrays.asList(getParkedCars()).contains(car)) {
            System.out.println("Введите что вы хотите улучшить в автомобиле: " + car.getBrand());
            System.out.println(car.getListUpgrade());
            Scanner scanner = new Scanner(System.in);
            String chosen = scanner.nextLine();

            switch (chosen) {
                case "turbo" -> car.setTurbo((int) (car.getTurbo() * 1.1));
                case "power" -> car.setPower((int) (car.getPower() * 1.1));
                case "suspension" -> car.setSuspension((int) (car.getSuspension() * 0.9));
                case "durability" -> car.setDurability((int) (car.getDurability() * 1.1));
                case "addons" -> ((PerformanceCar) car).setAddOns(askAddons());
                case "stars" -> ((ShowCar) car).setStars(1);
                default -> System.out.println("Введен неизвестный параметр");
            }
        } else {
            System.out.println("Автомобиля: " + car.getBrand() + " нет в гараже, тюнинг невозможен");
        }

    }

    private String askAddons() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите какие дополнение хотите установить для автомобиля");
        return scanner.nextLine();
    }

    @Override
    public String toString() {
        return "Garage " +
                "parked cars=" + Arrays.toString(parkedCars);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Garage garage = (Garage) o;
        return Arrays.equals(parkedCars, garage.parkedCars);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(parkedCars);
    }
}
