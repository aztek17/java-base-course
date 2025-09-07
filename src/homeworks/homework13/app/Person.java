package homeworks.homework13.app;

import homeworks.homework13.exception.WrongConditionException;
import homeworks.homework13.utils.CheckCondition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private String name;
    protected int amountMoney;
    private final ArrayList<Product> products = new ArrayList<>();
    private int age;

    public Person(String name, int amountMoney, int age) throws IOException {
        setName(name);
        setAmountMoney(amountMoney);
        setAge(age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IOException {
        if (!name.isEmpty()) {
            this.name = name;
        } else {
            App.writeToFile("Имя не может быть пустым");
        }
    }

    public int getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(Integer amountMoney) {
        try {
            this.amountMoney = (int) new CheckCondition().validate(amountMoney,
                    condition -> condition.intValue() >= 0);
        } catch (WrongConditionException exception) {
            App.writeToFile(exception.getMessage() + "Деньги не могут быть отрицательным числом");
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) throws IOException {
        try {
            new CheckCondition().validate(getAmountMoney(),
                    condition -> condition.intValue() >= product.getProductPrice());
            this.products.add(product);
            setAmountMoney(getAmountMoney() - product.getProductPrice());
            App.writeToFile(getName() + " купил " + product.getProductName());
        } catch (WrongConditionException exception) {
            App.writeToFile(exception.getMessage() + getName() + " не может позволить себе " + product.getProductName());
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        try {
            this.age = (int) new CheckCondition().validate(age, condition -> condition.intValue() >= 0);
        } catch (WrongConditionException exception) {
            App.writeToFile(exception.getMessage() + "Возраст не может быть отрицательным числом");
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
        if (products.isEmpty()) {
            return name + " - Ничего не куплено";
        } else {
            return name + " - " + products;
        }
    }
}
