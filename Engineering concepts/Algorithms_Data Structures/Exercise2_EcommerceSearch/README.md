# Exercise 2: E-commerce Platform Search Function

## 1. Understand Asymptotic Notation

### Big O notation
Big O describes how an algorithm's running time (or memory) **grows as the input
size n grows**, ignoring constant factors and lower-order terms. It lets us
compare algorithms independently of hardware or language: an O(log n) algorithm
will eventually beat an O(n) one as data grows, no matter the machine. It
expresses the **upper bound** — the worst-case growth rate.

### Best / average / worst case for search
| Scenario | Linear Search | Binary Search |
|---|---|---|
| **Best** | O(1) — target is the first element | O(1) — target is the middle element |
| **Average** | O(n) — target somewhere in the middle | O(log n) |
| **Worst** | O(n) — target is last or absent | O(log n) — target absent / at an edge |

## 2. Setup
`Product` has `productId`, `productName`, `category` (package
`Exercise2_EcommerceSearch`).

## 3. Implementation
- **Linear search** over an unsorted array (`linearSearchById`).
- **Binary search** over an array sorted by `productId` (`binarySearchById`).

## 4. Analysis

### Complexity comparison
| Aspect | Linear Search | Binary Search |
|---|---|---|
| Time | O(n) | O(log n) |
| Data requirement | none | must be **sorted** |
| Preprocessing cost | none | O(n log n) to sort once |
| Best for | small or unsorted / frequently-changing data | large, mostly-static, sorted data |

### Which is more suitable, and why
For an e-commerce catalog that is **large and read far more often than it
changes**, **binary search wins**: sort the data once (O(n log n)) and every
subsequent lookup is O(log n) instead of O(n). For a catalog of a few dozen
items, or one that changes on every request, linear search's simplicity and
zero preprocessing can be preferable.

In a real platform, id/keyword lookups are usually served by a **hash index
(O(1))** or an inverted-index search engine (Elasticsearch/Lucene). Binary
search is the right model when data lives in a **sorted array** and you need
range queries; hashing is better for exact-match lookups.

## How to run
```bash
javac Exercise2_EcommerceSearch/*.java
java Exercise2_EcommerceSearch.ProductSearch
```
