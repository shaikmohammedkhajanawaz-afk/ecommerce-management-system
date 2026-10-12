
package service;

import java.util.ArrayList;
import java.util.List;

import model.Cart;
import model.CartItem;
import model.Customer;
import model.Order;
import model.Product;

import payment.Payment;
import payment.UpiPayment;
import payment.CardPayment;
import payment.CashPayment;

public class OrderService {

    private static int nextOrderId = 1001;

    private List<Order> orders = new ArrayList<>();

    private PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Checkout and create an order
    public Order checkout(
            Customer customer,
            Cart cart,
            String paymentMethod,
            String paymentDetails) {

        if (customer == null) {
            System.out.println("Please login first.");
            return null;
        }

        if (cart == null || cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return null;
        }

        // Check stock before payment
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();

            if (item.getQuantity() > product.getStock()) {
                System.out.println(
                    "Insufficient stock for: " + product.getName()
                );
                return null;
            }
        }

        // Calculate cart amount
        double cartTotal = cart.getTotal();

        // Demo discount: 10% discount on the cart
        double discount = cartTotal * 0.10;
        double finalAmount = cartTotal - discount;

        System.out.println("\n===== CHECKOUT =====");
        System.out.println("Cart Amount: Rs. " + cartTotal);
        System.out.println("Discount (10%): Rs. " + discount);
        System.out.println("Final Amount: Rs. " + finalAmount);

        // Select payment method
        Payment payment;

        if (paymentMethod.equalsIgnoreCase("UPI")) {

            payment = new UpiPayment(paymentDetails);

        } else if (paymentMethod.equalsIgnoreCase("CARD")) {

            payment = new CardPayment(paymentDetails);

        } else if (paymentMethod.equalsIgnoreCase("COD")) {

            payment = new CashPayment();

        } else {

            System.out.println("Invalid payment method.");
            return null;
        }

        // Process payment
        boolean paymentSuccessful =
                paymentService.processPayment(payment, finalAmount);

        if (!paymentSuccessful) {
            System.out.println("Payment failed. Order not created.");
            return null;
        }

        // Create order
        Order order = new Order(
                nextOrderId++,
                customer,
                cart.getItems(),
                finalAmount
        );

        order.setPaymentMethod(paymentMethod.toUpperCase());

        // Confirm order
        order.confirmOrder();

        // Reduce product stock
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();

            product.setStock(
                product.getStock() - item.getQuantity()
            );
        }

        // Store order in memory
        orders.add(order);

        // Empty cart after successful checkout
        cart.clearCart();

        System.out.println("\nOrder placed successfully!");
        order.displayOrder();

        return order;
    }

    // View all orders
    public void viewAllOrders() {

        if (orders.isEmpty()) {
            System.out.println("No orders available.");
            return;
        }

        for (Order order : orders) {
            order.displayOrder();
            System.out.println("----------------------");
        }
    }

    // View all orders belonging to one customer
    public void viewOrderHistory(Customer customer) {

        if (customer == null) {
            System.out.println("Please login first.");
            return;
        }

        boolean found = false;

        for (Order order : orders) {

            if (order.getCustomer().getUserId()
                    == customer.getUserId()) {

                order.displayOrder();
                System.out.println("----------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("You have no previous orders.");
        }
    }
}
