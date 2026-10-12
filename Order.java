
package model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private int orderId;
    private Customer customer;
    private List<CartItem> items;
    private double totalAmount;
    private String paymentMethod;
    private String status;

    public Order(int orderId, Customer customer,
                 List<CartItem> items, double totalAmount) {

        if (customer == null) {
            throw new IllegalArgumentException(
                "Customer cannot be null."
            );
        }

        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException(
                "Order cannot be empty."
            );
        }

        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>(items);
        this.totalAmount = totalAmount;
        this.paymentMethod = "Not selected";
        this.status = "CREATED";
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void confirmOrder() {
        if (!status.equals("CREATED")) {
            System.out.println(
                "Only created orders can be confirmed."
            );
            return;
        }

        status = "CONFIRMED";
        System.out.println("Order confirmed successfully.");
    }

    public void shipOrder() {
        if (!status.equals("CONFIRMED")) {
            System.out.println(
                "Only confirmed orders can be shipped."
            );
            return;
        }

        status = "SHIPPED";
        System.out.println("Order shipped successfully.");
    }

    public void deliverOrder() {
        if (!status.equals("SHIPPED")) {
            System.out.println(
                "Only shipped orders can be delivered."
            );
            return;
        }

        status = "DELIVERED";
        System.out.println("Order delivered successfully.");
    }

    public void cancelOrder() {
        if (status.equals("DELIVERED")) {
            System.out.println(
                "Delivered orders cannot be cancelled."
            );
            return;
        }

        if (status.equals("CANCELLED")) {
            System.out.println("Order is already cancelled.");
            return;
        }

        status = "CANCELLED";
        System.out.println("Order cancelled successfully.");
    }

    public void displayOrder() {

        System.out.println("\n========== ORDER DETAILS ==========");
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customer.getName());

        System.out.println("\nOrdered Products:");

        for (CartItem item : items) {
            item.displayItem();
        }

        System.out.println("-----------------------------------");
        System.out.println("Total Amount: Rs. " + totalAmount);
        System.out.println("Payment Method: " + paymentMethod);
        System.out.println("Order Status: " + status);
        System.out.println("===================================");
    }
}

