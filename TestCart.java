package model;

public class TestCart {

	public static void main(String[] args) {
		  Product laptop =
		            new Electronics(101, "Laptop", 55000, 10);

		        Product tshirt =
		            new Clothing(102, "T-Shirt", 999, 20);

		        Cart cart = new Cart();

		        // Add products
		        cart.addProduct(laptop, 1);
		        cart.addProduct(tshirt, 2);

		        // Display cart
		        cart.viewCart();

		        // Update T-shirt quantity
		        cart.updateQuantity(102, 3);

		        // Display updated cart
		        System.out.println("\nAfter updating quantity:");
		        cart.viewCart();

		        // Remove the laptop
		        cart.removeProduct(101);

		        // Display final cart
		        System.out.println("\nAfter removing laptop:");
		        cart.viewCart();
	}

}
