package homeworks.homework07;

import java.util.ArrayList;

public class Pensioner extends Person {
    private final ArrayList<Product> products = new ArrayList<>();

    public Pensioner(String name, int amountMoney, int age) {
        super(name, amountMoney, age);
    }

    @Override
    public void addProduct(Product product) {
        if (product instanceof DiscountProduct) {
            if (getAmountMoney() >= priceAfterAdditionalDiscount(product)) {
                this.products.add(product);
                setAmountMoney(getAmountMoney() - priceAfterAdditionalDiscount(product));
                System.out.println(getName() + " купил " + product.getProductName());
            } else {
                System.out.println(getName() + " не может позволить себе " + product.getProductName());
            }
        }
    }

    private int priceAfterAdditionalDiscount(Product product) {
        return (int) Math.round(product.getProductPrice() * (1 - 5 / 100.0));
    }
}
