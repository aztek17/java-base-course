package race;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
@NoArgsConstructor
public class DriftRace extends Race {

    @Override
    public String toString() {
        return "\n* Дрифт гонка * - " +
                "длина гонки: " + length +
                ", маршрут: '" + route + '\'' +
                ", призовой фонд: " + prizeFund +
                ", участники гонки: " + Arrays.toString(racers) +
                '}';
    }
}
