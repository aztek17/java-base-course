package homeworks.homework07;

public class Child extends Person {

    public Child(String name, int amountMoney, int age) {
        super(name, amountMoney, age);
    }

    @Override
    public void addProduct(Product product) {
        if (getAge() < 6) {
            System.out.println("Ребенок не может покупать продукты");
        } else if (!product.isAccessibleToChild()) {
            System.out.println("Этот товар не разрешен к продаже ребенку");
        } else {
            super.addProduct(product);
        }
    }
}
