import car.Car;
import garage.Garage;
import race.CasualRace;
import race.DriftRace;
import race.Race;
import repository.CarRepository;
import repository.impl.CarRepositoryImpl;

import java.util.Arrays;

public class App {

    public static void main(String[] args) {
        CarRepository repository = new CarRepositoryImpl();

        Car juke = repository.findByModel("Juke");
        Car niva = repository.findByModel("Niva");
        Car ferrari = repository.findByModel("Roma");
        Car mcLaren = repository.findByModel("720s");
        Car mercedes = repository.findByModel("E55 AMG");
        Car toyota = repository.findByModel("Supra");

        Garage garage = new Garage();

        Car[] monacoRacers = {juke, ferrari};
        Race monaco = new CasualRace(10000, "Square St. Pavel - Red wood", 5000, monacoRacers);
        Car[] tokioRacers = {mercedes, toyota};
        Race tokio = new DriftRace(7800, "Tokio Central Square - Abandoned Village", 3000, tokioRacers);

        Car[] parkedCars = {niva, mcLaren, ferrari};
        garage.setParkedCars(parkedCars);

        garage.upgradeCar(niva);
        garage.upgradeCar(mcLaren);
        garage.upgradeCar(ferrari);

        Race[] finishRaces = {monaco, tokio};

        System.out.println("\nИнформация об автомобилях в гараже после тюнинга: " + Arrays.toString(garage.getParkedCars()));
        System.out.println("\nИнформация об проводимых гонках: " + Arrays.toString(finishRaces));
    }

}
