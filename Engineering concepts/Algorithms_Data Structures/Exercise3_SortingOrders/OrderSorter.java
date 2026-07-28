package Exercise3_SortingOrders;

import java.util.Arrays;

/**
 * Sorts customer orders by totalPrice using Bubble Sort and Quick Sort.
 * (Ascending order; reverse for highest-value-first prioritisation.)
 */
public class OrderSorter {

    /**
     * Bubble Sort: repeatedly swap adjacent out-of-order elements.
     * Time: O(n^2). The early-exit flag makes a nearly-sorted array O(n).
     */
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    Order tmp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = tmp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break; // already sorted
            }
        }
    }

    /**
     * Quick Sort: divide-and-conquer around a pivot.
     * Time: O(n log n) average, O(n^2) worst case (poor pivots).
     */
    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(orders, low, high);
            quickSort(orders, low, pivotIndex - 1);
            quickSort(orders, pivotIndex + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice(); // last element as pivot
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() <= pivot) {
                i++;
                swap(orders, i, j);
            }
        }
        swap(orders, i + 1, high);
        return i + 1;
    }

    private static void swap(Order[] orders, int a, int b) {
        Order tmp = orders[a];
        orders[a] = orders[b];
        orders[b] = tmp;
    }

    private static Order[] sampleOrders() {
        return new Order[]{
                new Order(1, "Aarav", 2500.00),
                new Order(2, "Bhavna", 999.50),
                new Order(3, "Chetan", 15750.00),
                new Order(4, "Divya", 480.00),
                new Order(5, "Esha", 7200.75)
        };
    }

    public static void main(String[] args) {
        Order[] a = sampleOrders();
        System.out.println("Original: " + Arrays.toString(a));

        Order[] b = a.clone();
        bubbleSort(b);
        System.out.println("\nBubble Sort (asc by totalPrice):");
        Arrays.stream(b).forEach(System.out::println);

        Order[] c = a.clone();
        quickSort(c, 0, c.length - 1);
        System.out.println("\nQuick Sort (asc by totalPrice):");
        Arrays.stream(c).forEach(System.out::println);
    }
}
