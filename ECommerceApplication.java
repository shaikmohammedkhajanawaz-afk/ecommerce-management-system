package main;
import java.util.Scanner;

import model.Admin;
import model.Customer;
import model.Product;
import model.Electronics;
import model.Clothing;
import model.Grocery;

import service.CustomerService;
import service.ProductService;
import service.CartService;
import service.PaymentService;
import service.OrderService;

public class ECommerceApplication {

    static Scanner sc = new Scanner(System.in);

    static CustomerService customerService =
            new CustomerService();

    static ProductService productService =
            new ProductService();

    static PaymentService paymentService =
            new PaymentService();

    static OrderService orderService =
            new OrderService(paymentService);

    static int nextCustomerId = 2;

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n================================");
            System.out.println("   E-COMMERCE MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Customer Registration");
            System.out.println("2. Customer Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = readInt();

            switch (choice) {

                case 1:
                    registerCustomer();
                    break;

                case 2:
                    customerLogin();
                    break;

                case 3:
                    adminLogin();
                    break;

                case 4:
                    System.out.println(
                            "Thank you for using our application!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 4);

        sc.close();
    }

    // Customer registration
    static void registerCustomer() {

        System.out.println("\n--- CUSTOMER REGISTRATION ---");

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        System.out.print("Enter address: ");
        String address = sc.nextLine();

        customerService.registerCustomer(
                nextCustomerId++,
                name,
                email,
                password,
                address
        );
    }

    // Customer login
    static void customerLogin() {

        System.out.println("\n--- CUSTOMER LOGIN ---");

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        Customer customer =
                customerService.loginCustomer(email, password);

        if (customer != null) {
            System.out.println(
                    "Welcome, " + customer.getName() + "!");
            customerMenu(customer);
        } else {
            System.out.println("Invalid email or password.");
        }
    }

    // Customer menu
    static void customerMenu(Customer customer) {

        CartService cartService =
                new CartService(productService);

        int choice;

        do {
            System.out.println("\n--- CUSTOMER MENU ---");
            System.out.println("1. View Products");
            System.out.println("2. Search Product by ID");
            System.out.println("3. Add Product to Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Remove Product from Cart");
            System.out.println("6. Update Cart Quantity");
            System.out.println("7. Checkout");
            System.out.println("8. View Order History");
            System.out.println("9. Logout");
            System.out.print("Enter your choice: ");

            choice = readInt();

            switch (choice) {

                case 1:
                    productService.viewProducts();
                    break;

                case 2:
                    searchProduct();
                    break;

                case 3:
                    System.out.print("Enter product ID: ");
                    int productId = readInt();

                    System.out.print("Enter quantity: ");
                    int quantity = readInt();

                    cartService.addToCart(productId, quantity);
                    break;

                case 4:
                    cartService.viewCart();
                    System.out.println(
                            "Total: Rs. " + cartService.getTotal());
                    break;

                case 5:
                    System.out.print("Enter product ID to remove: ");
                    cartService.removeFromCart(readInt());
                    break;

                case 6:
                    System.out.print("Enter product ID: ");
                    int updateId = readInt();

                    System.out.print("Enter new quantity: ");
                    int newQuantity = readInt();

                    cartService.updateQuantity(
                            updateId, newQuantity);
                    break;

                case 7:
                    checkout(customer, cartService);
                    break;

                case 8:
                    orderService.viewOrderHistory(customer);
                    break;

                case 9:
                    System.out.println("Customer logged out.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 9);
    }

    // Search product
    static void searchProduct() {

        System.out.print("Enter product ID: ");
        int id = readInt();

        Product product = productService.searchProduct(id);

        if (product != null) {
            product.displayProduct();
        } else {
            System.out.println("Product not found.");
        }
    }

    // Checkout
    static void checkout(
            Customer customer,
            CartService cartService) {

        if (cartService.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.println("\n--- PAYMENT METHODS ---");
        System.out.println("1. UPI");
        System.out.println("2. Card");
        System.out.println("3. Cash on Delivery");
        System.out.print("Choose payment method: ");

        int choice = readInt();

        String method;
        String details = "";

        switch (choice) {

            case 1:
                method = "UPI";
                System.out.print("Enter UPI ID: ");
                details = sc.nextLine();
                break;

            case 2:
                method = "CARD";
                System.out.print("Enter 16-digit card number: ");
                details = sc.nextLine();
                break;

            case 3:
                method = "COD";
                break;

            default:
                System.out.println("Invalid payment choice.");
                return;
        }

        orderService.checkout(
                customer,
                cartService.getCart(),
                method,
                details
        );
    }

    // Admin login
    static void adminLogin() {

        System.out.println("\n--- ADMIN LOGIN ---");

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        Admin admin =
                customerService.loginAdmin(email, password);

        if (admin != null) {
            System.out.println("Admin login successful!");
            adminMenu();
        } else {
            System.out.println("Invalid admin credentials.");
        }
    }

    // Admin menu
    static void adminMenu() {

        int choice;

        do {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Product Name");
            System.out.println("4. Update Product Price");
            System.out.println("5. Update Product Stock");
            System.out.println("6. Remove Product");
            System.out.println("7. View All Orders");
            System.out.println("8. View Customers");
            System.out.println("9. Logout");
            System.out.print("Enter your choice: ");

            choice = readInt();

            switch (choice) {

                case 1:
                    addProduct();
                    break;

                case 2:
                    productService.viewProducts();
                    break;

                case 3:
                    System.out.print("Enter product ID: ");
                    int nameId = readInt();

                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();

                    productService.updateProductName(
                            nameId, newName);
                    break;

                case 4:
                    System.out.print("Enter product ID: ");
                    int priceId = readInt();

                    System.out.print("Enter new price: ");
                    double price = readDouble();

                    productService.updateProductPrice(
                            priceId, price);
                    break;

                case 5:
                    System.out.print("Enter product ID: ");
                    int stockId = readInt();

                    System.out.print("Enter new stock: ");
                    int stock = readInt();

                    productService.updateStock(
                            stockId, stock);
                    break;

                case 6:
                    System.out.print("Enter product ID to remove: ");
                    productService.removeProduct(readInt());
                    break;

                case 7:
                    orderService.viewAllOrders();
                    break;

                case 8:
                    customerService.viewCustomers();
                    break;

                case 9:
                    System.out.println("Admin logged out.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 9);
    }

    // Add product
    static void addProduct() {

        System.out.println("\n--- ADD PRODUCT ---");

        System.out.print("Enter product ID: ");
        int id = readInt();

        System.out.print("Enter product name: ");
        String name = sc.nextLine();

        System.out.print("Enter price: ");
        double price = readDouble();

        System.out.print("Enter stock quantity: ");
        int stock = readInt();

        System.out.println("1. Electronics");
        System.out.println("2. Clothing");
        System.out.println("3. Grocery");
        System.out.print("Choose category: ");

        int category = readInt();

        Product product;

        switch (category) {

            case 1:
                product = new Electronics(
                        id, name, price, stock);
                break;

            case 2:
                product = new Clothing(
                        id, name, price, stock);
                break;

            case 3:
                product = new Grocery(
                        id, name, price, stock);
                break;

            default:
                System.out.println("Invalid category.");
                return;
        }

        productService.addProduct(product);
    }

    // Read integer safely
    static int readInt() {

        while (true) {
            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid integer: ");
            }
        }
    }

    // Read decimal safely
    static double readDouble() {

        while (true) {
            try {
                double value =
                        Double.parseDouble(sc.nextLine().trim());

                if (Double.isFinite(value)) {
                    return value;
                }

                System.out.print("Enter a finite number: ");

            } catch (NumberFormatException e) {
                System.out.print("Enter a valid amount: ");
            }
        }
    }
}
