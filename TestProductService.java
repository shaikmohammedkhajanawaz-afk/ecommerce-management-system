package service;

public class TestProductService {

    public static void main(String[] args) {

        ProductService service = new ProductService();

        // View products
        service.viewProducts();

        // Search product
        System.out.println("\n--- Search Laptop ---");
        service.searchProduct(101);

        // Update price
        System.out.println("\n--- Update Price ---");
        service.updateProductPrice(101, 52000);

        // Update stock
        System.out.println("\n--- Update Stock ---");
        service.updateStock(101, 15);

        // View updated product
        service.searchProduct(101);

        // Remove product
        System.out.println("\n--- Remove Product ---");
        service.removeProduct(103);

        // View final products
        service.viewProducts();
    }
}
