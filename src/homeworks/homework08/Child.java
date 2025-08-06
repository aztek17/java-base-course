package homeworks.homework08;

import java.io.IOException;

public class Child extends Person {

    public Child(String name, int amountMoney, int age) throws IOException {
        super(name, amountMoney, age);
    }

    @Override
    public void addProduct(Product product) throws IOException {
        if (getAge() < 6) {
            writeDataToFile("Ребенок не может покупать продукты");
        } else if (!product.isAccessibleToChild()) {
            writeDataToFile("Этот товар не разрешен к продаже ребенку");
        } else {
            super.addProduct(product);
        }
    }
}
