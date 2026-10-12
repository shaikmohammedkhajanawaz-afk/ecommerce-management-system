
package payment;

public class CashPayment implements Payment {

    @Override
    public boolean pay(double amount) {

        if (amount <= 0) {
            System.out.println("Invalid payment amount.");
            return false;
        }

        System.out.println("Cash on Delivery selected.");
        System.out.println("Amount payable on delivery: Rs. "
                + amount);

        return true;
    }
}
