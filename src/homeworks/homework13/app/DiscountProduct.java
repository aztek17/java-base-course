package homeworks.homework13.app;

public class DiscountProduct extends Product {
    private int discountValue;
    private boolean discountApplies = false;

    public DiscountProduct(String productName, int productPrice, int discountValue, boolean discountApplies) {
        super(productName, (int) Math.round(productPrice * (1 - discountValue / 100.0)));
        this.discountValue = discountValue;
        this.discountApplies = discountApplies;
    }

    public int getDiscountValue() {
        return discountValue;
    }

    public boolean isDiscountApplies() {
        return discountApplies;
    }

}
