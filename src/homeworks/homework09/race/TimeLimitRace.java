package homeworks.homework09.race;

import homeworks.homework09.car.Car;

import java.util.Arrays;

public class TimeLimitRace extends Race {
    private int goldTime;

    public TimeLimitRace(int length, String route, int prizeFund, Car[] racers, int goldTime) {
        super(length, route, prizeFund, racers);
        this.goldTime = goldTime;
    }

    public TimeLimitRace() {
    }

    public int getGoldTime() {
        return goldTime;
    }

    public void setGoldTime(int goldTime) {
        this.goldTime = goldTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TimeLimitRace that = (TimeLimitRace) o;
        return goldTime == that.goldTime;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + goldTime;
        return result;
    }

    @Override
    public String toString() {
        return "**Гонка на лучшее время** " +
                "\nлучшее время: " + goldTime +
                ", длина гонки: " + length +
                ", маршрут: '" + route + '\'' +
                ", призовой фонд: " + prizeFund +
                ", участники гонки: " + Arrays.toString(racers);
    }
}
