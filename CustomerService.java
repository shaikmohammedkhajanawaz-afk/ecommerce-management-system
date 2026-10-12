
package service;

import model.Customer;
import model.Admin;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    private List<Customer> customers;
    private Admin admin;

    public CustomerService() {
        customers = new ArrayList<>();

        // Default admin account for this console demo
        admin = new Admin(
            1,
            "Administrator",
            "admin@gmail.com",
            "admin123"
        );
    }

    // Customer registration
    public boolean registerCustomer(
            int id,
            String name,
            String email,
            String password,
            String address) {

        if (name == null || name.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || password == null || password.isEmpty()
                || address == null || address.trim().isEmpty()) {

            System.out.println("All fields are required.");
            return false;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            System.out.println("Please enter a valid email.");
            return false;
        }

        if (password.length() < 6) {
            System.out.println(
                "Password must contain at least 6 characters."
            );
            return false;
        }

        for (Customer customer : customers) {

            if (customer.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Email already registered.");
                return false;
            }
        }

        Customer customer = new Customer(
            id, name, email, password, address
        );

        customers.add(customer);

        System.out.println("Registration successful!");
        return true;
    }

    // Customer login
    public Customer loginCustomer(String email, String password) {

        for (Customer customer : customers) {

            if (customer.getEmail().equalsIgnoreCase(email)
                    && customer.checkPassword(password)) {

                System.out.println(
                    "Customer login successful. Welcome, "
                    + customer.getName() + "!"
                );

                return customer;
            }
        }

        System.out.println("Invalid email or password.");
        return null;
    }

    // Admin login
    public Admin loginAdmin(String email, String password) {

        if (admin.getEmail().equalsIgnoreCase(email)
                && admin.checkPassword(password)) {

            System.out.println("Admin login successful!");
            return admin;
        }

        System.out.println("Invalid admin credentials.");
        return null;
    }

    // View registered customers
    public void viewCustomers() {

        if (customers.isEmpty()) {
            System.out.println("No registered customers.");
            return;
        }

        for (Customer customer : customers) {
            customer.displayDetails();
            System.out.println("--------------------");
        }
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }
}
