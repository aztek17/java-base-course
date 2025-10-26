package car;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
public class Car {

    String brand;
    String model;
    int yearOfManufactured;
    int power;
    int turbo;
    int suspension;
    int durability;

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


