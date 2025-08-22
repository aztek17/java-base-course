package homeworks.homework09.race;

import homeworks.homework09.car.Car;

import java.util.Arrays;
import java.util.Objects;

public class Race {
    int length;
    String route;
    int prizeFund;
    Car[] racers;

    public Race(int length, String route, int prizeFund, Car[] racers) {
        this.length = length;
        this.route = route;
        this.prizeFund = prizeFund;
        this.racers = racers;
    }

    public Race() {
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public int getPrizeFund() {
        return prizeFund;
    }

    public void setPrizeFund(int prizeFund) {
        this.prizeFund = prizeFund;
    }

    public Car[] getRacers() {
        return racers;
    }

    public void setRacers(Car[] racers) {
        this.racers = racers;
    }

    @Override
    public String toString() {
        return "Обычная гонка " +
                "длина гонки: " + length +
                ", маршрут: '" + route + '\'' +
                ", призовой фонд: " + prizeFund +
                ", участники гонки: " + Arrays.toString(racers) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Race race = (Race) o;
        return length == race.length && prizeFund == race.prizeFund && Objects.equals(route, race.route) && Arrays.equals(racers, race.racers);
    }

    @Override
    public int hashCode() {
        int result = length;
        result = 31 * result + Objects.hashCode(route);
        result = 31 * result + prizeFund;
        result = 31 * result + Arrays.hashCode(racers);
        return result;
    }
}
