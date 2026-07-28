package Exercise6_LibraryManagement;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Library search: linear search on an unsorted list and binary search on a
 * list sorted by title. Titles are compared case-insensitively.
 */
public class LibrarySearch {

    /**
     * Linear search by title. Scans every book. O(n). Works on unsorted data.
     */
    public static Book linearSearchByTitle(Book[] books, String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                return b;
            }
        }
        return null;
    }

    /**
     * Binary search by title. REQUIRES books sorted by title (case-insensitive).
     * O(log n).
     */
    public static Book binarySearchByTitle(Book[] sortedBooks, String title) {
        int low = 0;
        int high = sortedBooks.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = sortedBooks[mid].getTitle().compareToIgnoreCase(title);
            if (cmp == 0) {
                return sortedBooks[mid];
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Book[] books = {
                new Book(1, "The Pragmatic Programmer", "Hunt & Thomas"),
                new Book(2, "Clean Code", "Robert Martin"),
                new Book(3, "Effective Java", "Joshua Bloch"),
                new Book(4, "Introduction to Algorithms", "CLRS"),
                new Book(5, "Design Patterns", "Gang of Four")
        };

        System.out.println("=== Linear search (unsorted) ===");
        System.out.println("'Clean Code'   -> " + linearSearchByTitle(books, "Clean Code"));
        System.out.println("'Unknown Book' -> " + linearSearchByTitle(books, "Unknown Book"));

        Book[] sorted = books.clone();
        Arrays.sort(sorted, Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));
        System.out.println("\nSorted by title:");
        Arrays.stream(sorted).forEach(System.out::println);

        System.out.println("\n=== Binary search (sorted) ===");
        System.out.println("'Effective Java' -> " + binarySearchByTitle(sorted, "Effective Java"));
        System.out.println("'Refactoring'    -> " + binarySearchByTitle(sorted, "Refactoring"));
    }
}
