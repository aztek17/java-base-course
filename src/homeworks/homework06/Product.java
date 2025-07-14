package homeworks.homework06;

public class Product {
    private String productName;
    private int productPrice;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if (!productName.isEmpty()) {
            this.productName = productName;
        }
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        if (productPrice > 0) {
            this.productPrice = productPrice;
        }
    }
}
