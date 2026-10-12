package service;
import payment.*;

public class TestPayment {

    public static void main(String[] args) {

        PaymentService service = new PaymentService();

        System.out.println("===== UPI PAYMENT =====");
        Payment upi = new UpiPayment("rahul@okaxis");
        service.processPayment(upi, 55000);

        System.out.println("\n===== CARD PAYMENT =====");
        Payment card = new CardPayment("1234567812345678");
        service.processPayment(card, 55000);

        System.out.println("\n===== CASH ON DELIVERY =====");
        Payment cod = new CashPayment();
        service.processPayment(cod, 55000);
    }
}
