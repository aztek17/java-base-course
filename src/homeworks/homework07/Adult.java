package homeworks.homework07;

import java.util.Random;

public class Adult extends Person {

    private boolean buyCredit;

    public Adult(String name, int amountMoney, int age) {
        super(name, amountMoney, age);
        this.buyCredit = new Random().nextBoolean();
    }

    @Override
    public void addProduct(Product product) {
        if (getAmountMoney() >= product.getProductPrice()) {
            getProducts().add(product);
            setAmountMoney(getAmountMoney() - product.getProductPrice());
            System.out.println(getName() + " купил " + product.getProductName());
        } else if (getAmountMoney() < product.getProductPrice() && isBuyCredit()) {
            getProducts().add(product);
            setAmountMoney(getAmountMoney() - product.getProductPrice());
            System.out.println(getName() + " купил товар " + product.getProductName() + " в кредит");
        } else {
            System.out.println(getName() + " не может позволить себе " + product.getProductName());
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
