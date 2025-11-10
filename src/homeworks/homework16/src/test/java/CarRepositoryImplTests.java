import car.Car;
import car.ShowCar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repository.CarRepository;
import repository.impl.CarRepositoryImpl;

import java.util.Arrays;
import java.util.List;

public class CarRepositoryImplTests {
    private static final String FILE_PATH = "src/test/resources/cars.txt";
    private static final String PATH_WRONG_SEPARATOR = "src/test/resources/wrongSeparator.txt";
    private static final String PATH_SKIPPED_PARAM = "src/test/resources/skippedParam.txt";

    @Test
    void invalidParamsSeparator() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CarRepositoryImpl(PATH_WRONG_SEPARATOR));
    }

    @Test
    void skippedParam() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CarRepositoryImpl(PATH_SKIPPED_PARAM));
    }

    @Test
    void unknownCarType() {
        CarRepository repository = new CarRepositoryImpl(FILE_PATH);
        Assertions.assertEquals(6, repository.findAll().size()); // first step

        List<String> expectedTypes = Arrays.asList("SHOW", "SPORT", "BASE"); // second step
        repository.findAll()
                .forEach(validate -> Assertions.assertTrue(expectedTypes.contains(validate.getType())));
    }

    @Test
    void getAll() {
        CarRepository repository = new CarRepositoryImpl(FILE_PATH);
        List<Car> cars = repository.findAll();
        Assertions.assertEquals(6, cars.size());
    }

    @Test
    void findCarsByModel() {
        ShowCar expCar = new ShowCar();
        expCar.setType("SHOW");
        expCar.setBrand("Mercedes");
        expCar.setModel("E55 AMG");
        expCar.setYearOfManufactured(2007);
        expCar.setPower(500);
        expCar.setTurbo(80);
        expCar.setSuspension(80);
        expCar.setDurability(50);
        expCar.setStars(0);

        CarRepository repository = new CarRepositoryImpl(FILE_PATH);
        Car mercedes = repository.findByModel("E55 AMG");
        Assertions.assertEquals(expCar, mercedes);
    }

}
