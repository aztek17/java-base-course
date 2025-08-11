package homeworks.homework09.race;

import homeworks.homework09.car.Car;

import java.util.Arrays;

public class CircuitRace extends Race {
    private int laps;

    public CircuitRace(int length, String route, int prizeFund, Car[] racers, int laps) {
        super(length, route, prizeFund, racers);
        this.laps = laps;
    }

    public CircuitRace() {
    }

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CircuitRace that = (CircuitRace) o;
        return laps == that.laps;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + laps;
        return result;
    }

    @Override
    public String toString() {
        return "**Кольцевая гонка** " +
                "\nколичество кругов: " + laps +
                ", длина гонки: " + length +
                ", маршрут: '" + route + '\'' +
                ", призовой фонд: " + prizeFund +
                ", участники гонки: " + Arrays.toString(racers);
    }
}
