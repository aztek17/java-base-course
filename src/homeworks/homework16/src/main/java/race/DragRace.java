package race;

import car.Car;

import java.util.Arrays;

public class DragRace extends Race {
    public DragRace(int length, String route, int prizeFund, Car[] racers) {
        super(length, route, prizeFund, racers);
    }

    public DragRace() {
    }

    @Override
    public String toString() {
        return "\n* Драг гонка * - " +
                "длина гонки: " + length +
                ", маршрут: '" + route + '\'' +
                ", призовой фонд: " + prizeFund +
                ", участники гонки: " + Arrays.toString(racers) +
                '}';
    }
}
