package car;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class ShowCar extends Car {
    int stars;

    public ShowCar(String brand, String model, int yearOfManufactured, int power, int turbo, int suspension, int durability) {
        super(brand, model, yearOfManufactured, power, turbo, suspension, durability);
        setStars(0);
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
}
