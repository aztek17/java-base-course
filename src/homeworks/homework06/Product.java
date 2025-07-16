package homeworks.homework06;

import java.util.Objects;

public class Product {
    private String productName;
    private int productPrice;

    public Product(String productName, int productPrice) {
        setProductName(productName);
        setProductPrice(productPrice);
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if (!productName.isEmpty()) {
            this.productName = productName;
        } else {
            System.out.println("Название товара не может быть пустым");
        }
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        if (productPrice >= 0) {
            this.productPrice = productPrice;
        } else {
            System.out.println("Цена товара не может быть меньше нуля");
        }
    }

    @Override
    public String toString() {
        return productName;
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
