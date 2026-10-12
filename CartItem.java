
package model;

public class CartItem {

    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
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

        if (quantity > product.getStock()) {
            throw new IllegalArgumentException(
                "Insufficient stock."
            );
        }

        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(
                "Quantity must be greater than zero."
            );
        }

        if (quantity > product.getStock()) {
            throw new IllegalArgumentException(
                "Insufficient stock."
            );
        }

        this.quantity = quantity;
    }

    public void displayItem() {
        System.out.println(
            product.getName()
            + " | Quantity: " + quantity
            + " | Price: Rs. " + product.getPrice()
            + " | Subtotal: Rs. " + getSubtotal()
        );
    }
}
