package race;

import car.Car;

import java.util.Arrays;

public class CasualRace extends Race {
    public CasualRace(int length, String route, int prizeFund, Car[] racers) {
        super(length, route, prizeFund, racers);
    }

    public CasualRace() {
    }

    @Override
    public String toString() {
        return "\n*Городская гонка* - " +
                "длина гонки: " + length +
                ", маршрут: '" + route + '\'' +
                ", призовой фонд: " + prizeFund +
                ", участники гонки: " + Arrays.toString(racers) +
                '}';
    }
}
