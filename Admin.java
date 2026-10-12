
package model;

public class Admin extends User {

    public Admin(int userId, String name, String email,
                 String password) {
        super(userId, name, email, password);
    }

    public void displayAdminDetails() {
        super.displayDetails();
        System.out.println("Role: Admin");
    }
}
