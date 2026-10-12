package model;

public class TestProduct {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  Product p1 = new Electronics(
		            101, "Laptop", 55000, 10
		        );

		        Product p2 = new Clothing(
		            102, "T-Shirt", 999, 20
		        );

		        System.out.println("=== PRODUCT DETAILS ===");

		        p1.displayProduct();
		        p2.displayProduct();

	}

}
