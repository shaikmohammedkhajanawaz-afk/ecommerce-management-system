package payment;

public class CardPayment implements Payment {

    private String cardNumber;

    public CardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean pay(double amount) {

        if (cardNumber == null
                || !cardNumber.matches("\\d{16}")
                || amount <= 0) {

            System.out.println("Invalid card details or amount.");
            return false;
        }

        System.out.println("Processing card payment...");
        System.out.println("Card ending: "
                + cardNumber.substring(12));
        System.out.println("Amount: Rs. " + amount);
        System.out.println("Card payment successful!");

        return true;
    }
}

