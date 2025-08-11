package homeworks.homework09.car;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Car {

    String brand;
    String model;
    int yearOfManufactured;
    int power;
    int turbo;
    int suspension;
    int durability;

    public Car(String brand, String model, int yearOfManufactured, int power, int turbo, int suspension, int durability) {
        this.brand = brand;
        this.model = model;
        this.yearOfManufactured = yearOfManufactured;
        this.power = power;
        this.turbo = turbo;
        this.suspension = suspension;
        this.durability = durability;
    }

    public Car() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearOfManufactured() {
        return yearOfManufactured;
    }

    public void setYearOfManufactured(int yearOfManufactured) {
        this.yearOfManufactured = yearOfManufactured;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
        System.out.println("Показатель мощности изменен на " + power);
    }

    public int getTurbo() {
        return turbo;
    }

    public void setTurbo(int turbo) {
        this.turbo = turbo;
        System.out.println("Показатель ускорения изменен на " + turbo);
    }

    public int getSuspension() {
        return suspension;
    }

    public void setSuspension(int suspension) {
        this.suspension = suspension;
        System.out.println("Показатель подвески изменен на " + suspension);
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
        System.out.println("Показатель долговечности изменен на " + durability);
    }

    public List<String> getListUpgrade() {
        return new ArrayList<>() {{
            add("turbo");
            add("power");
            add("suspension");
            add("durability");
        }};
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;
        return yearOfManufactured == car.yearOfManufactured && power == car.power && turbo == car.turbo && suspension == car.suspension && durability == car.durability && Objects.equals(brand, car.brand) && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(brand);
        result = 31 * result + Objects.hashCode(model);
        result = 31 * result + yearOfManufactured;
        result = 31 * result + power;
        result = 31 * result + turbo;
        result = 31 * result + suspension;
        result = 31 * result + durability;
        return result;
    }

    @Override
    public String toString() {
        return "\nBase Car " +
                "brand='" + brand + '\'' +
                ", power=" + power +
                ", turbo=" + turbo +
                ", suspension=" + suspension +
                ", durability=" + durability;
    }
}


