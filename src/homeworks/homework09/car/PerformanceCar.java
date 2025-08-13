package homeworks.homework09.car;

import java.util.Arrays;
import java.util.List;

public class PerformanceCar extends Car {
    String[] addOns;

    public PerformanceCar(String brand, String model, int yearOfManufactured, int power, int turbo, int suspension, int durability) {
        super(brand, model, yearOfManufactured, (int) (power * 1.5), turbo, (int) (suspension * 0.75), durability);
    }

    public PerformanceCar() {
    }

    public String[] getAddOns() {
        return addOns;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PerformanceCar that = (PerformanceCar) o;
        return Arrays.equals(addOns, that.addOns);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(addOns);
        return result;
    }

    @Override
    public String toString() {
        return "\n-Sport Car " +
                "addOns=" + Arrays.toString(addOns) +
                ", brand='" + brand + '\'' +
                ", power=" + power +
                ", turbo=" + turbo +
                ", suspension=" + suspension +
                ", durability=" + durability;
    }
}
