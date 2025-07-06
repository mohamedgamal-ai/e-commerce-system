import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Initialize products
        Cheese cheese = new Cheese("Cheddar Cheese", 10.0, 5, 0.5, LocalDate.now().plusDays(10));
        TV tv = new TV("Smart TV", 500.0, 2, 15.0);
        MobileScratchCard scratchCard = new MobileScratchCard("Mobile Scratch Card", 5.0, 10);
        Cheese expiredCheese = new Cheese("Expired Cheese", 8.0, 3, 0.4, LocalDate.now().minusDays(5));

        // Initialize customer
        Customer customer = new Customer("John Doe", 1000.0);

        // Initialize shipping service
        ShippingService shippingService = new StandardShippingService();

        // Scenario 1: Successful checkout
        System.out.println("\n--- Scenario 1: Successful Checkout ---");
        Cart cart1 = new Cart();
        try {
            cart1.addProduct(cheese, 2);
            cart1.addProduct(tv, 1);
            cart1.addProduct(scratchCard, 3);
            System.out.println("Customer balance before checkout: " + customer.getBalance());
            cart1.checkout(customer, shippingService);
            System.out.println("Customer balance after checkout: " + customer.getBalance());
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // Scenario 2: Empty cart
        System.out.println("\n--- Scenario 2: Empty Cart ---");
        Cart cart2 = new Cart();
        try {
            cart2.checkout(customer, shippingService);
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // Scenario 3: Product out of stock
        System.out.println("\n--- Scenario 3: Product Out of Stock ---");
        Cart cart3 = new Cart();
        try {
            cart3.addProduct(cheese, 10); // Only 3 left after previous purchase
            cart3.checkout(customer, shippingService);
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // Scenario 4: Expired product
        System.out.println("\n--- Scenario 4: Expired Product ---");
        Cart cart4 = new Cart();
        try {
            cart4.addProduct(expiredCheese, 1);
            cart4.checkout(customer, shippingService);
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // Scenario 5: Insufficient balance
        System.out.println("\n--- Scenario 5: Insufficient Balance ---");
        Customer poorCustomer = new Customer("Poor John", 10.0);
        Cart cart5 = new Cart();
        try {
            cart5.addProduct(tv, 1);
            cart5.checkout(poorCustomer, shippingService);
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

