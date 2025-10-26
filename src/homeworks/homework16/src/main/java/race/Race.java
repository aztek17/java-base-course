package race;

import car.Car;
import lombok.*;

import java.util.Arrays;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class Race {
    int length;
    String route;
    int prizeFund;
    Car[] racers;

    @Override
    public String toString() {
        return "Обычная гонка " +
                "длина гонки: " + length +
                ", маршрут: '" + route + '\'' +
                ", призовой фонд: " + prizeFund +
                ", участники гонки: " + Arrays.toString(racers) +
                '}';
    }
}
