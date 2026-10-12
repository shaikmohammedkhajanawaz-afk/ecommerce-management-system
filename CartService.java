
package service;

import model.Cart;
import model.Product;

public class CartService {

    private Cart cart;
    private ProductService productService;

    public CartService(ProductService productService) {
        this.cart = new Cart();
        this.productService = productService;
    }

    // Add product to cart
    public void addToCart(int productId, int quantity) {

        Product product =
                productService.getProductById(productId);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        try {
            cart.addProduct(product, quantity);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // View cart
    public void viewCart() {
        cart.viewCart();
    }

    // Remove product
    public void removeFromCart(int productId) {
        cart.removeProduct(productId);
    }

    // Update quantity
    public void updateQuantity(int productId, int quantity) {

        try {
            cart.updateQuantity(productId, quantity);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Get total amount
    public double getTotal() {
        return cart.getTotal();
    }

    // Check whether cart is empty
    public boolean isEmpty() {
        return cart.isEmpty();
    }

    // Access cart for checkout
    public Cart getCart() {
        return cart;
    }

    // Clear cart after successful order
    public void clearCart() {
        cart.clearCart();
    }
}
