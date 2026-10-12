
package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cart {

    private List<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    // Add a product to the cart
    public void addProduct(Product product, int quantity) {

        if (product == null) {
            throw new IllegalArgumentException(
                "Product cannot be null."
            );
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                "Quantity must be greater than zero."
            );
        }

        for (CartItem item : items) {

            if (item.getProduct().getProductId()
                    == product.getProductId()) {

                int newQuantity = item.getQuantity() + quantity;

                if (newQuantity > product.getStock()) {
                    throw new IllegalArgumentException(
                        "Insufficient stock."
                    );
                }

                item.setQuantity(newQuantity);

                System.out.println(
                    "Cart quantity updated successfully."
                );
                return;
            }
        }

        CartItem newItem = new CartItem(product, quantity);
        items.add(newItem);

        System.out.println("Product added to cart successfully.");
    }

    // Display all cart items
    public void viewCart() {

        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.println("\n========== YOUR CART ==========");

        for (CartItem item : items) {
            item.displayItem();
        }

        System.out.println("--------------------------------");
        System.out.println("Total: Rs. " + getTotal());
    }

    // Remove a product using its ID
    public void removeProduct(int productId) {

        Iterator<CartItem> iterator = items.iterator();

        while (iterator.hasNext()) {

            CartItem item = iterator.next();

            if (item.getProduct().getProductId() == productId) {
                iterator.remove();
                System.out.println("Product removed from cart.");
                return;
            }
        }

        System.out.println("Product not found in cart.");
    }

    // Update the quantity of a product
    public void updateQuantity(int productId, int quantity) {

        for (CartItem item : items) {

            if (item.getProduct().getProductId() == productId) {

                item.setQuantity(quantity);

                System.out.println("Quantity updated successfully.");
                return;
            }
        }

        System.out.println("Product not found in cart.");
    }

    // Calculate total cart amount
    public double getTotal() {

        double total = 0;

        for (CartItem item : items) {
            total += item.getSubtotal();
        }

        return total;
    }

    // Check whether the cart is empty
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // Return the cart items
    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    // Clear the cart after a successful order
    public void clearCart() {
        items.clear();
    }
}
