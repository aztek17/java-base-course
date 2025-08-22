package homeworks.homework11.model;

import java.util.Objects;

public class Car {
    private String number;
    private String model;
    private String color;
    private long mileage;
    private long cost;
    private final String separator = "    ";

    public Car(String number, String model, String color, long mileage, long cost) {
        this.number = number;
        this.model = model;
        this.color = color;
        this.mileage = mileage;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (mileage != car.mileage) return false;
        if (cost != car.cost) return false;
        if (!Objects.equals(number, car.number)) return false;
        if (!Objects.equals(model, car.model)) return false;
        return Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + Long.hashCode(mileage);
        result = 31 * result + Long.hashCode(cost);
        result = 31 * result + separator.hashCode();
        return result;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "\n" + separator + number +
                separator + model +
                separator + color +
                separator + mileage +
                separator + cost;
    }
}
