package car;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class PerformanceCar extends Car {
    String[] addOns;

    public PerformanceCar(String brand, String model, int yearOfManufactured, int power, int turbo, int suspension, int durability) {
        super(brand,
                model,
                yearOfManufactured,
                (int) (power * 1.5),
                turbo,
                (int) (suspension * 0.75),
                durability);
    }

    public void setAddOns(String newAddOn) {
        if (addOns != null) {
            this.addOns = Arrays.copyOf(addOns, getAddOns().length + 1);
            this.addOns[addOns.length - 1] = newAddOn;
        } else {
            this.addOns = new String[]{newAddOn};
        }
        System.out.println("Добавлен аддон: " + newAddOn);
    }

    @Override
    public void setPower(int power) {
        super.setPower((int) (power * 1.5));
    }

    @Override
    public void setSuspension(int suspension) {
        super.setSuspension((int) (suspension * 0.75));
    }

    @Override
    public List<String> getListUpgrade() {
        List<String> listUpgrade;
        listUpgrade = super.getListUpgrade();
        listUpgrade.add("addons");
        return listUpgrade;
    }

}
