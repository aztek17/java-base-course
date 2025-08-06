package homeworks.homework08;

import java.io.IOException;
import java.util.Random;

public class Adult extends Person {

    private boolean buyCredit;

    public Adult(String name, int amountMoney, int age) throws IOException {
        super(name, amountMoney, age);
        this.buyCredit = new Random().nextBoolean();
    }

    @Override
    public void addProduct(Product product) throws IOException {
        if (getAmountMoney() >= product.getProductPrice()) {
            getProducts().add(product);
            setAmountMoney(getAmountMoney() - product.getProductPrice());
            writeDataToFile(getName() + " купил " + product.getProductName());
        } else if (getAmountMoney() < product.getProductPrice() && isBuyCredit()) {
            getProducts().add(product);
            setAmountMoney(getAmountMoney() - product.getProductPrice());
            writeDataToFile(getName() + " купил " + product.getProductName());
        } else {
            writeDataToFile(getName() + " не может позволить себе " + product.getProductName());
            setAmountMoney(getAmountMoney() - product.getProductPrice());
        }
    }

    @Override
    public void setAmountMoney(int amountMoney) {
        if (amountMoney >= 0) {
            super.setAmountMoney(amountMoney);
        } else if (isBuyCredit()) {
            this.amountMoney = amountMoney;
            System.out.println("Использована кредитная карта. Задолженность по карте: " + Math.abs(getAmountMoney()));
        } else {
            System.out.println("Деньги не могут быть отрицательными(кредитная карта так же отсутствует)");
        }
    }

    public boolean isBuyCredit() {
        return buyCredit;
    }

    public void setBuyCredit(boolean buyCredit) {
        this.buyCredit = buyCredit;
    }
}
