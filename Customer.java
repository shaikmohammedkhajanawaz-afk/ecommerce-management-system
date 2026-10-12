
package model;

public class Customer extends User {

    private String address;

    public Customer(int userId, String name, String email,
                    String password, String address) {
        super(userId, name, email, password);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Address: " + address);
        System.out.println("Role: Customer");
    }
}
