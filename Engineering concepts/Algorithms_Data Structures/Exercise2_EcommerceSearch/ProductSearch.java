package Exercise2_EcommerceSearch;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Demonstrates linear search (on an unsorted array) and binary search
 * (on an array sorted by productId) for the e-commerce platform.
 */
public class ProductSearch {

    /**
     * Linear search by productId.
     * Scans every element until a match is found.
     * Time: O(n). Works on unsorted data.
     */
    public static Product linearSearchById(Product[] products, int targetId) {
        for (Product p : products) {
            if (p.getProductId() == targetId) {
                return p;
            }
        }
        return null;
    }

    /**
     * Binary search by productId. REQUIRES the array to be sorted by productId.
     * Repeatedly halves the search range.
     * Time: O(log n).
     */
    public static Product binarySearchById(Product[] sortedProducts, int targetId) {
        int low = 0;
        int high = sortedProducts.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;   // avoids integer overflow
            int midId = sortedProducts[mid].getProductId();
            if (midId == targetId) {
                return sortedProducts[mid];
            } else if (midId < targetId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Product[] products = {
                new Product(105, "Laptop Stand", "Accessories"),
                new Product(101, "Bluetooth Speaker", "Audio"),
                new Product(110, "Gaming Mouse", "Peripherals"),
                new Product(103, "Noise Cancelling Headphones", "Audio"),
                new Product(108, "Webcam 1080p", "Peripherals")
        };

        System.out.println("=== Linear search (unsorted array) ===");
        Product r1 = linearSearchById(products, 110);
        System.out.println("Search id=110 -> " + r1);
        System.out.println("Search id=999 -> " + linearSearchById(products, 999));

        // Binary search needs a sorted array.
        Product[] sorted = products.clone();
        Arrays.sort(sorted, Comparator.comparingInt(Product::getProductId));
        System.out.println("\nSorted by id: " + Arrays.toString(sorted));

        System.out.println("\n=== Binary search (sorted array) ===");
        System.out.println("Search id=103 -> " + binarySearchById(sorted, 103));
        System.out.println("Search id=104 -> " + binarySearchById(sorted, 104));
    }
}
