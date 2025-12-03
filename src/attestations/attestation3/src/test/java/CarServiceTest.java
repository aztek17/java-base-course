import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import ru.test.tireservice.dto.CarDtoRequest;
import ru.test.tireservice.dto.CarDtoResponse;
import ru.test.tireservice.model.Car;
import ru.test.tireservice.repository.CarRepository;
import ru.test.tireservice.service.CarService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @Test
    public void getCarByIdPositive() {
        Long id = 1L;
        Integer tireSize = 30;

        Car car = Car.builder()
                .brand("Toyota")
                .model("Camry")
                .type(Car.CarType.BUS)
                .tireSize(tireSize)
                .build();
        car.setId(id);

        when(carRepository.findById(id))
                .thenReturn(Optional.of(car));

        CarDtoResponse result = carService.getCarById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Toyota", result.getBrand());
        assertEquals("Camry", result.getModel());
        assertEquals(Car.CarType.BUS, result.getType());
        assertEquals(tireSize, result.getTireSize());
    }

    @Test
    public void getCarByIdNotExist() {
        Long carId = 999L;

        when(carRepository.findById(carId))
                .thenReturn(Optional.empty());

        ResponseStatusException exception = Assertions.assertThrows(
                ResponseStatusException.class,
                () -> carService.getCarById(carId)
        );

        assertTrue(exception.getMessage().contains("не существует или он удален"));
    }

    @Test
    public void getAllCars() {
        Car car1 = Car.builder().brand("Lada").build();
        Car car2 = Car.builder().brand("Chevrolet").build();
        car1.setId(1L);
        car2.setId(2L);

        when(carRepository.findAll())
                .thenReturn(List.of(car1, car2));

        List<CarDtoResponse> result = carService.getAllCars();

        assertEquals(2, result.size());
        assertEquals("Lada", result.get(0).getBrand());
        assertEquals("Chevrolet", result.get(1).getBrand());
    }

    @Test
    public void createCarPositive() {
        CarDtoRequest request = CarDtoRequest.builder()
                .brand("Mercedes")
                .model("W212")
                .type(Car.CarType.PASSENGER)
                .tireSize(17)
                .build();

        Car savedCar = Car.builder()
                .brand("Mercedes")
                .model("W212")
                .type(Car.CarType.PASSENGER)
                .tireSize(17)
                .build();
        savedCar.setId(1L);

        when(carRepository.save(any(Car.class)))
                .thenReturn(savedCar);

        CarDtoResponse result = carService.createCar(request);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Mercedes", result.getBrand());
        assertEquals("W212", result.getModel());
        assertEquals(Car.CarType.PASSENGER, result.getType());
        assertEquals(17, result.getTireSize());
    }

    @Test
    public void updateCarPositive() {
        Long carId = 1L;
        Car existingCar = Car.builder()
                .brand("VW")
                .model("GOLF5")
                .type(Car.CarType.PASSENGER)
                .tireSize(16)
                .build();

        CarDtoRequest updateRequest = CarDtoRequest.builder()
                .brand("VW1")
                .model("GOLF7")
                .type(Car.CarType.TRUCK)
                .tireSize(18)
                .build();

        when(carRepository.findById(carId))
                .thenReturn(Optional.of(existingCar));
        when(carRepository.save(any(Car.class)))
                .thenReturn(existingCar);

        CarDtoResponse result = carService.updateCar(carId, updateRequest);

        assertEquals("VW1", result.getBrand());
        assertEquals("GOLF7", result.getModel());
        assertEquals(18, result.getTireSize());
        assertEquals(Car.CarType.TRUCK, result.getType());
    }

    @Test
    public void updateCarOnlyProvidedFields() {
        Long carId = 1L;
        Car existingCar = Car.builder()
                .brand("Toyota")
                .model("Camry")
                .type(Car.CarType.PASSENGER)
                .tireSize(16)
                .build();
        existingCar.setId(carId);

        CarDtoRequest updateRequest = CarDtoRequest.builder()
                .model("E-Klasse")
                .build();

        when(carRepository.findById(carId))
                .thenReturn(Optional.of(existingCar));
        when(carRepository.save(any(Car.class)))
                .thenReturn(existingCar);

        CarDtoResponse result = carService.updateCar(carId, updateRequest);

        assertEquals("E-Klasse", result.getModel());
        assertEquals(16, result.getTireSize());
        assertEquals("Toyota", result.getBrand());
    }
}
