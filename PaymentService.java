
package service;

import payment.Payment;

public class PaymentService {

    public boolean processPayment(Payment payment, double amount) {

        if (payment == null) {
            System.out.println("Please select a payment method.");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Invalid payment amount.");
            return false;
        }

        return payment.pay(amount);
    }
}
