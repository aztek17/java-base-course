package homeworks.homework06;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private String name;
    private int amountMoney;
    private ArrayList<Product> products;

    public Person(String name, int amountMoney) {
        setName(name);
        this.amountMoney = amountMoney;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Имя не может быть пустым");
        }
    }

    public int getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(int amountMoney) {
        if (amountMoney > 0) {
            this.amountMoney = amountMoney;
        }  else {
            System.out.println("Деньги не могут быть отрицательными");
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        if (getAmountMoney() >= product.getProductPrice()) {
            this.products.add(product);
            setAmountMoney(getAmountMoney() - product.getProductPrice());
            System.out.println(getName() + " купил " + product.getProductName());
        } else {
            System.out.println(getName() + " не может позволить себе " + product.getProductName());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;
        return amountMoney == person.amountMoney && Objects.equals(name, person.name) && Objects.equals(products, person.products);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(name);
        result = 31 * result + amountMoney;
        result = 31 * result + Objects.hashCode(products);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", amountMoney=" + amountMoney +
                ", products=" + products +
                '}';
    }
}
