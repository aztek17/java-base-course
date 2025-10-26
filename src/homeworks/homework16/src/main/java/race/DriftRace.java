package race;

import car.Car;

import java.util.Arrays;

public class DriftRace extends Race {
    public DriftRace(int length, String route, int prizeFund, Car[] racers) {
        super(length, route, prizeFund, racers);
    }

    public DriftRace() {
    }

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
