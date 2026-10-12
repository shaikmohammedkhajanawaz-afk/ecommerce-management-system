package service;

import model.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {

    private Map<Integer, Product> products;

    public ProductService() {
        products = new HashMap<>();

        // Sample products
        addProduct(new Product(101, "Laptop", 55000, 10,
                "Electronics"));

        addProduct(new Product(102, "T-Shirt", 999, 20,
                "Clothing"));

        addProduct(new Product(103, "Rice", 600, 50,
                "Grocery"));
    }

    // Add product
    public void addProduct(Product product) {

        if (product == null) {
            System.out.println("Invalid product.");
            return;
        }

        if (products.containsKey(product.getProductId())) {
            System.out.println("Product ID already exists.");
            return;
        }

        products.put(product.getProductId(), product);
    }

    // View all products
    public void viewProducts() {

        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.println("\n======= AVAILABLE PRODUCTS =======");

        for (Product product : products.values()) {
            product.displayProduct();
        }
    }

    // Search product by ID
    // Returns the product without displaying it
    public Product searchProduct(int productId) {
        return products.get(productId);
    }

    // Search products by name
    public List<Product> searchByName(String name) {

        List<Product> result = new ArrayList<>();

        if (name == null || name.trim().isEmpty()) {
            return result;
        }

        for (Product product : products.values()) {

            if (product.getName().toLowerCase()
                    .contains(name.toLowerCase())) {

                result.add(product);
            }
        }

        return result;
    }

    // Update product name
    public void updateProductName(int productId, String name) {

        Product product = products.get(productId);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Product name cannot be empty.");
            return;
        }

        product.setName(name);
        System.out.println("Product name updated successfully.");
    }

    // Update product price
    public void updateProductPrice(int productId, double price) {

        Product product = products.get(productId);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        try {
            product.setPrice(price);
            System.out.println("Product price updated successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Update stock
    public void updateStock(int productId, int stock) {

        Product product = products.get(productId);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        try {
            product.setStock(stock);
            System.out.println("Stock updated successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Remove product
    public void removeProduct(int productId) {

        if (products.remove(productId) != null) {
            System.out.println("Product removed successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    // Get product by ID without displaying it
    public Product getProductById(int productId) {
        return products.get(productId);
    }

    // Return all products
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
}
