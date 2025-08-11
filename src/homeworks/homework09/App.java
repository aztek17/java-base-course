package homeworks.homework09;

import homeworks.homework09.car.Car;
import homeworks.homework09.car.PerformanceCar;
import homeworks.homework09.car.ShowCar;
import homeworks.homework09.garage.Garage;
import homeworks.homework09.race.CasualRace;
import homeworks.homework09.race.DriftRace;
import homeworks.homework09.race.Race;

import java.util.Arrays;

public class App {

    public static void main(String[] args) {
        Car juke = new Car("Nissan", "Juke", 2015, 100, 40, 100, 100);
        Car niva = new Car("Lada", "Niva", 2010, 80, 20, 150,60);
        Car ferrari = new PerformanceCar("Ferrari" , "Roma", 2023, 620, 270, 60, 70);
        Car mcLaren = new PerformanceCar("McLaren" , "720s", 2023, 720, 250, 50, 75);
        Car mercedes = new ShowCar("Mercedes", "E55 AMG", 2007,500, 80,80,50);
        Car toyota = new ShowCar("Toyota", "Supra", 2002,470, 200,50,70);

        Garage garage = new Garage();

        Car[] monacoRacers = {juke, ferrari};
        Race monaco = new CasualRace(10000, "Square St. Pavel - Red wood", 5000, monacoRacers);
        Car[] tokioRacers = {mercedes, toyota};
        Race tokio = new DriftRace(7800, "Tokio Central Square - Abandoned Village", 3000, tokioRacers);

        Car[] parkedCars = {niva, mcLaren};
        garage.setParkedCars(parkedCars);

        garage.upgradeCar(niva);
        garage.upgradeCar(mcLaren);
        garage.upgradeCar(ferrari);

        System.out.println("Информация об автомобилях в гараже после тюнинга: " + Arrays.toString(garage.getParkedCars()));
    }

}
