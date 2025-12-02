import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;
import ru.test.tireservice.AppTireService;
import ru.test.tireservice.controller.CarController;
import ru.test.tireservice.dto.CarDtoRequest;
import ru.test.tireservice.dto.CarDtoResponse;
import ru.test.tireservice.model.Car;
import ru.test.tireservice.service.CarService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = AppTireService.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CarService carService;

    @Test
    public void createCarPositive() throws Exception {
        CarDtoRequest request = CarDtoRequest.builder()
                .brand("Toyota")
                .model("Camry")
                .type(Car.CarType.PASSENGER)
                .tireSize(17)
                .build();

        CarDtoResponse response = CarDtoResponse.builder()
                .id(1L)
                .brand("Toyota")
                .model("Camry")
                .type(Car.CarType.PASSENGER)
                .tireSize(17)
                .build();

        when(carService.createCar(any(CarDtoRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.brand").value("Toyota"))
                .andExpect(jsonPath("$.model").value("Camry"));
    }

    @Test
    public void getAllCarsPositive() throws Exception {
        CarDtoResponse car1 = CarDtoResponse.builder()
                .id(1L).brand("Renault").model("Logan").build();
        CarDtoResponse car2 = CarDtoResponse.builder()
                .id(2L).brand("BMW").model("X5").build();

        when(carService.getAllCars())
                .thenReturn(List.of(car1, car2));

        mockMvc.perform(get("/api/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].brand").value("Renault"))
                .andExpect(jsonPath("$[0].model").value("Logan"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].brand").value("BMW"))
                .andExpect(jsonPath("$[1].model").value("X5"));
    }

    @Test
    public void getCarByIdPositive() throws Exception {
        Long carId = 1L;
        CarDtoResponse response = CarDtoResponse.builder()
                .id(carId)
                .brand("Volga")
                .model("Cyber")
                .build();

        when(carService.getCarById(carId))
                .thenReturn(response);

        mockMvc.perform(get("/api/car/{id}", carId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(carId))
                .andExpect(jsonPath("$.brand").value("Volga"))
                .andExpect(jsonPath("$.model").value("Cyber"));
    }

    @Test
    public void getCarByIdNotExist() throws Exception {
        Long carId = 9999L;
        when(carService.getCarById(carId))
                .thenThrow(new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Автомобиля с ID " + carId + " не существует"
                ));

        mockMvc.perform(get("/api/car/{id}", carId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateCarOnlyProvidedDataPositive() throws Exception {
        Long carId = 1L;
        CarDtoRequest request = CarDtoRequest.builder()
                .model("Spectra")
                .tireSize(18)
                .build();

        CarDtoResponse response = CarDtoResponse.builder()
                .id(carId)
                .brand("Kia")
                .model("Spectra")
                .type(Car.CarType.BUS)
                .tireSize(18)
                .build();

        when(carService.updateCar(eq(carId), any(CarDtoRequest.class)))
                .thenReturn(response);

        mockMvc.perform(put("/api/car/{id}", carId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(carId))
                .andExpect(jsonPath("$.model").value("Spectra"))
                .andExpect(jsonPath("$.type").value(Car.CarType.BUS.name()))
                .andExpect(jsonPath("$.tireSize").value(18));
    }

    @Test
    public void createCarEmptyBodyNegative() throws Exception {
        CarDtoRequest request = CarDtoRequest.builder().build();

        mockMvc.perform(post("/api/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
