package Exercise1_InventoryManagement;

import java.util.HashMap;
import java.util.Map;

/**
 * Inventory management system backed by a HashMap keyed on productId.
 *
 * A HashMap is chosen over an ArrayList because the primary access pattern in a
 * warehouse system is lookup / update / delete BY productId. A HashMap gives
 * average O(1) for all three, whereas an ArrayList would need an O(n) scan to
 * find the product first.
 */
public class InventoryManagement {

    private final Map<String, Product> inventory = new HashMap<>();

    /** Add a product. O(1) average. Returns false if the id already exists. */
    public boolean addProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            System.out.println("Add failed: product " + product.getProductId() + " already exists.");
            return false;
        }
        inventory.put(product.getProductId(), product);
        return true;
    }

    /** Update quantity and price of an existing product. O(1) average. */
    public boolean updateProduct(String productId, int newQuantity, double newPrice) {
        Product p = inventory.get(productId);
        if (p == null) {
            System.out.println("Update failed: product " + productId + " not found.");
            return false;
        }
        p.setQuantity(newQuantity);
        p.setPrice(newPrice);
        return true;
    }

    /** Delete a product by id. O(1) average. */
    public boolean deleteProduct(String productId) {
        if (inventory.remove(productId) == null) {
            System.out.println("Delete failed: product " + productId + " not found.");
            return false;
        }
        return true;
    }

    /** Lookup by id. O(1) average. */
    public Product getProduct(String productId) {
        return inventory.get(productId);
    }

    public void printInventory() {
        System.out.println("---- Current Inventory (" + inventory.size() + " items) ----");
        inventory.values().forEach(System.out::println);
        System.out.println("--------------------------------------------");
    }

    public static void main(String[] args) {
        InventoryManagement mgr = new InventoryManagement();

        mgr.addProduct(new Product("P001", "Wireless Mouse", 150, 799.00));
        mgr.addProduct(new Product("P002", "Mechanical Keyboard", 80, 2499.00));
        mgr.addProduct(new Product("P003", "USB-C Cable", 500, 199.00));
        mgr.printInventory();

        System.out.println("\n>> Updating P002 (restock + price change)");
        mgr.updateProduct("P002", 120, 2299.00);
        System.out.println(mgr.getProduct("P002"));

        System.out.println("\n>> Deleting P001");
        mgr.deleteProduct("P001");

        System.out.println("\n>> Attempting duplicate add and invalid delete");
        mgr.addProduct(new Product("P003", "Duplicate Cable", 10, 199.00));
        mgr.deleteProduct("P999");

        System.out.println();
        mgr.printInventory();
    }
}
