package homeworks.homework08;

import java.io.IOException;

public class Pensioner extends Person {

    public Pensioner(String name, int amountMoney, int age) throws IOException {
        super(name, amountMoney, age);
    }

    @Override
    public void addProduct(Product product) throws IOException {
        if (product instanceof DiscountProduct) {
            if (getAmountMoney() >= priceAfterAdditionalDiscount(product)) {
                getProducts().add(product);
                setAmountMoney(getAmountMoney() - priceAfterAdditionalDiscount(product));
                App.writeToFile(getName() + " купил " + product.getProductName());
            } else {
                App.writeToFile(getName() + " не может позволить себе " + product.getProductName());
            }
        } else {
            App.writeToFile("Пенсионер покупает только акционные товары");
        }
    }

    private int priceAfterAdditionalDiscount(Product product) {
        return (int) Math.round(product.getProductPrice() * (1 - 5 / 100.0));
    }
}
