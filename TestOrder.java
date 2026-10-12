package model;
import java.util.ArrayList;
import java.util.List;

public class TestOrder {

    public static void main(String[] args) {

        Customer customer = new Customer(
            1,
            "Rahul",
            "rahul@gmail.com",
            "rahul123",
            "Hyderabad"
        );

        Product laptop = new Electronics(
            101, "Laptop", 55000, 10
        );

        Cart cart = new Cart();
        cart.addProduct(laptop, 1);

        List<CartItem> items = new ArrayList<>(cart.getItems());

        Order order = new Order(
            1001,
            customer,
            items,
            cart.getTotal()
        );

        order.setPaymentMethod("UPI");
        order.confirmOrder();
        order.displayOrder();

        order.shipOrder();
        order.deliverOrder();

        System.out.println("\nAfter delivery:");
        order.displayOrder();
    }
}
