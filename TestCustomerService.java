package service;
import model.Customer;
import model.Admin;

public class TestCustomerService {

    public static void main(String[] args) {

        CustomerService service = new CustomerService();

        // Register a customer
        System.out.println("=== REGISTRATION ===");

        service.registerCustomer(
            101,
            "Rahul",
            "rahul@gmail.com",
            "rahul123",
            "Hyderabad"
        );

        // Customer login
        System.out.println("\n=== CUSTOMER LOGIN ===");

        Customer customer = service.loginCustomer(
            "rahul@gmail.com",
            "rahul123"
        );

        if (customer != null) {
            customer.displayDetails();
        }

        // Admin login
        System.out.println("\n=== ADMIN LOGIN ===");

        Admin admin = service.loginAdmin(
            "admin@gmail.com",
            "admin123"
        );

        if (admin != null) {
            admin.displayAdminDetails();
        }
    }
}
