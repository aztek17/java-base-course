package car;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
public class Car {

    String type;
    String brand;
    String model;
    int yearOfManufactured;
    int power;
    int turbo;
    int suspension;
    int durability;

    public Car(String data) {
        List<String> params = Arrays.asList(data.split(","));
        if (params.size() == 8) {
            setType(params.getFirst());
            setBrand(params.get(1));
            setModel(params.get(2));
            setYearOfManufactured(Integer.parseInt(params.get(3)));
            setPower(Integer.parseInt(params.get(4)));
            setTurbo(Integer.parseInt(params.get(5)));
            setSuspension(Integer.parseInt(params.get(6)));
            setDurability(Integer.parseInt(params.get(7)));
        } else {
            throw new IllegalArgumentException("Не удалось считать данные из файла из-за некоректных полей");
        }
    }

    public List<String> getListUpgrade() {
        return new ArrayList<>() {{
            add("turbo");
            add("power");
            add("suspension");
            add("durability");
        }};
    }

    @Override
    public String toString() {
        return "\n-Base Car: " +
                "brand='" + brand + '\'' +
                ", power=" + power +
                ", turbo=" + turbo +
                ", suspension=" + suspension +
                ", durability=" + durability;
    }
}


