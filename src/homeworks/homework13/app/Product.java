package homeworks.homework13.app;

import java.util.Objects;
import java.util.Random;

public class Product {
    private String productName;
    private int productPrice;
    private boolean isAccessibleToChild;

    public Product(String productName, int productPrice) {
        setProductName(productName);
        setProductPrice(productPrice);
        this.isAccessibleToChild = new Random().nextBoolean();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if (!productName.isEmpty()) {
            if (productName.length() < 3 || productName.matches("\\d+")) {
                App.writeToFile("Недопустимое имя продукта");
                return;
            }
            this.productName = productName;
        } else {
            App.writeToFile("Название товара не может быть пустым");
        }
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        if (productPrice > 0) {
            this.productPrice = productPrice;
        } else {
            App.writeToFile("Недопустимая стоимость продукта");
        }
    }

    public boolean isAccessibleToChild() {
        return isAccessibleToChild;
    }

    public void setAccessibleToChild(boolean accessibleToChild) {
        isAccessibleToChild = accessibleToChild;
    }

    @Override
    public String toString() {
        return (productName == null ? "" : productName);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return productPrice == product.productPrice && Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(productName);
        result = 31 * result + productPrice;
        return result;
    }
}
