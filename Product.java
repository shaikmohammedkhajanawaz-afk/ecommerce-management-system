
package model;

public class Product {

    private int productId;
    private String name;
    private double price;
    private int stock;
    private String category;

    public Product(int productId, String name, double price,
                   int stock, String category) {
        if (price < 0 || stock < 0) {
            throw new IllegalArgumentException(
                "Price and stock cannot be negative."
            );
        }

        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException(
                "Price cannot be negative."
            );
        }
        this.price = price;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException(
                "Stock cannot be negative."
            );
        }
        this.stock = stock;
    }

    public void displayProduct() {
        System.out.println(
            "ID: " + productId
            + " | Name: " + name
            + " | Category: " + category
            + " | Price: Rs. " + price
            + " | Stock: " + stock
        );
    }
}
