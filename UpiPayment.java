
package payment;

public class UpiPayment implements Payment {

    private String upiId;

    public UpiPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public boolean pay(double amount) {

        if (upiId == null || !upiId.matches(
                "[A-Za-z0-9._-]+@[A-Za-z0-9]+")
                || amount <= 0) {

            System.out.println("Invalid UPI details or amount.");
            return false;
        }

        System.out.println("Processing UPI payment...");
        System.out.println("UPI ID: " + upiId);
        System.out.println("Amount: Rs. " + amount);
        System.out.println("UPI payment successful!");

        return true;
    }
}
