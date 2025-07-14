package homeworks.homework06;

public class Person {
    private String name;
    private int amountMoney;
    private Product[] products;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        }
    }

    public int getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(int amountMoney) {
        if (amountMoney > 0) {
            this.amountMoney = amountMoney;
        }
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }
}
