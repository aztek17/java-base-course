package homeworks.homework09.race;

import homeworks.homework09.car.Car;

import java.util.Arrays;

public class DriftRace extends Race {
    public DriftRace(int length, String route, int prizeFund, Car[] racers) {
        super(length, route, prizeFund, racers);
    }

    public DriftRace() {
    }

    @Override
    public String toString() {
        return "Дрифт гонка " +
                "длина гонки: " + length +
                ", маршрут: '" + route + '\'' +
                ", призовой фонд: " + prizeFund +
                ", участники гонки: " + Arrays.toString(racers) +
                '}';
    }
}
