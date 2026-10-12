package service;

public class TestCartService {

    public static void main(String[] args) {

        ProductService productService =
                new ProductService();

        CartService cartService =
                new CartService(productService);

        // Display available products
        productService.viewProducts();

        // Add laptop
        System.out.println("\n=== ADD TO CART ===");
        cartService.addToCart(101, 1);

        // Add two T-shirts
        cartService.addToCart(102, 2);

        // View cart
        System.out.println("\n=== VIEW CART ===");
        cartService.viewCart();

        // Update T-shirt quantity
        System.out.println("\n=== UPDATE QUANTITY ===");
        cartService.updateQuantity(102, 3);

        cartService.viewCart();

        // Display total
        System.out.println("\nTotal amount: Rs. "
                + cartService.getTotal());

        // Remove laptop
        System.out.println("\n=== REMOVE PRODUCT ===");
        cartService.removeFromCart(101);

        cartService.viewCart();
    }
}
