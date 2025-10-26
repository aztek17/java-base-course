package car;

import java.util.List;

public class ShowCar extends Car {
    int stars;

    public ShowCar(String brand, String model, int yearOfManufactured, int power, int turbo, int suspension, int durability) {
        super(brand, model, yearOfManufactured, power, turbo, suspension, durability);
        setStars(0);
    }

    public ShowCar() {
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = getStars() + stars;
    }

    @Override
    public List<String> getListUpgrade() {
        List<String> listUpgrade;
        listUpgrade = super.getListUpgrade();
        listUpgrade.add("stars");
        return listUpgrade;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ShowCar showCar = (ShowCar) o;
        return stars == showCar.stars;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + stars;
        return result;
    }

    @Override
    public String toString() {
        return "\n-Show Car " +
                "stars=" + stars +
                ", brand='" + brand + '\'' +
                ", power=" + power +
                ", turbo=" + turbo +
                ", suspension=" + suspension +
                ", durability=" + durability;
    }
}
