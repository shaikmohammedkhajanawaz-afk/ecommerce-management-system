package service;
import model.Customer;

public class TestOrderService {

    public static void main(String[] args) {

        ProductService productService =
                new ProductService();

        CartService cartService =
                new CartService(productService);

        PaymentService paymentService =
                new PaymentService();

        OrderService orderService =
                new OrderService(paymentService);

        Customer customer = new Customer(
                1,
                "Rahul",
                "rahul@gmail.com",
                "rahul123",
                "Kurnool"
        );

        // Add products
        cartService.addToCart(101, 1);
        cartService.addToCart(102, 2);

        // Display cart
        cartService.viewCart();

        // Checkout using Cash on Delivery
        orderService.checkout(
                customer,
                cartService.getCart(),
                "COD",
                ""
        );

        // View order history
        System.out.println("\n===== ORDER HISTORY =====");
        orderService.viewOrderHistory(customer);

        // Check updated stock
        System.out.println("\n===== UPDATED PRODUCTS =====");
        productService.viewProducts();
    }
}
