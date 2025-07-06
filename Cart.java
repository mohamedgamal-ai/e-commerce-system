import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Cart {
    private Map<Product, Integer> items;

    public Cart() {
        this.items = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough " + product.getName() + " in stock.");
        }
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public void clearCart() {
        items.clear();
    }

    public double checkout(Customer customer, ShippingService shippingService) {
        if (items.isEmpty()) {
            throw new IllegalStateException("Cart is empty.");
        }

        double subtotal = 0;
        double shippingFee = 0;
        List<Shippable> shippableProducts = new java.util.ArrayList<>();

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product instanceof Expirable && ((Expirable) product).isExpired()) {
                throw new IllegalStateException(product.getName() + " is expired.");
            }
            if (product.getQuantity() < quantity) {
                throw new IllegalStateException(product.getName() + " is out of stock.");
            }

            subtotal += product.getPrice() * quantity;

            if (product instanceof Shippable) {
                shippableProducts.add((Shippable) product);
            }
        }

        shippingFee = shippingService.calculateShippingFee(shippableProducts);
        double totalAmount = subtotal + shippingFee;

        if (customer.getBalance() < totalAmount) {
            throw new IllegalStateException("Insufficient balance. Remaining balance: " + customer.getBalance() + ", Total amount: " + totalAmount);
        }

        customer.decreaseBalance(totalAmount);

        // Decrease product quantities in stock
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            product.decreaseQuantity(quantity);
        }

        System.out.println("Order Subtotal: " + String.format("%.2f", subtotal));
        System.out.println("Shipping Fees: " + String.format("%.2f", shippingFee));
        System.out.println("Total Amount: " + String.format("%.2f", totalAmount));
        System.out.println("Customer\'s Remaining Balance: " + String.format("%.2f", customer.getBalance()));

        clearCart();
        return totalAmount;
    }
}

