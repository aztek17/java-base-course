import car.Car;
import car.ShowCar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.CarRepository;
import repository.impl.CarRepositoryImpl;

import java.util.List;

public class CarRepositoryImplTests {
    // Разделитель точка
    // Тип машины не в switch/case к примеру DRIFT
    // Пропущен параметр для создания объекта
    // findAll проверить количество в List
    // findByModel проверить количество и частично или полностью поля
    private static final String FILE_PATH = "src/main/resources/cars.txt";
    private static final String PATH_WRONG_SEPARATOR = "src/main/resources/wrongSeparator.txt";
    private static final String PATH_SKIPPED_PARAM = "src/main/resources/skippedParam.txt";

//    @BeforeEach
//    void setup() {
//        new CarRepositoryImpl(FILE_PATH);
//    }

    @Test
    void invalidParamsSeparator() {
        Assertions.assertThrows(IllegalAccessException.class, () -> new CarRepositoryImpl(PATH_WRONG_SEPARATOR));
    }

    @Test
    void skippedParam() {
        Assertions.assertThrows(IllegalAccessException.class, () -> new CarRepositoryImpl(PATH_SKIPPED_PARAM));
    }

    @Test
    void unknownCarType() {
        CarRepository repository = new CarRepositoryImpl(FILE_PATH);
        List<Car> cars = repository.findAll();
        for (Car car: cars) {
            Assertions.assertNotEquals("SAMOSVAL", car.getType());
        }
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
