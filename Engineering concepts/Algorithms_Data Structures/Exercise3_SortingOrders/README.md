# Exercise 3: Sorting Customer Orders

## 1. Understand Sorting Algorithms
| Algorithm | Idea | Time (best / avg / worst) | Space | Stable |
|---|---|---|---|---|
| **Bubble Sort** | Repeatedly swap adjacent out-of-order elements; largest "bubbles" to the end each pass. | O(n) / O(n²) / O(n²) | O(1) | Yes |
| **Insertion Sort** | Grow a sorted prefix by inserting each new element into place. | O(n) / O(n²) / O(n²) | O(1) | Yes |
| **Quick Sort** | Pick a pivot, partition into <pivot / >pivot, recurse. | O(n log n) / O(n log n) / O(n²) | O(log n) | No |
| **Merge Sort** | Split in half, sort each half, merge. | O(n log n) / O(n log n) / O(n log n) | O(n) | Yes |

- **Bubble/Insertion** are simple, in-place, and fine for small or nearly-sorted
  data, but quadratic on large data.
- **Quick Sort** is the usual in-memory default (fast, low extra memory).
- **Merge Sort** guarantees O(n log n) and is stable — preferred when worst-case
  bounds matter or for external/linked-list sorting.

## 2. Setup
`Order` has `orderId`, `customerName`, `totalPrice` (package
`Exercise3_SortingOrders`).

## 3. Implementation
- `bubbleSort` — sorts orders ascending by `totalPrice` (with early-exit flag).
- `quickSort` — Lomuto partition, last element as pivot.

## 4. Analysis

### Performance comparison
| | Bubble Sort | Quick Sort |
|---|---|---|
| Average time | O(n²) | O(n log n) |
| Worst time | O(n²) | O(n²) (bad pivots) |
| Best time | O(n) (already sorted) | O(n log n) |
| Extra space | O(1) | O(log n) recursion stack |
| Comparisons/swaps on large n | very high | much lower |

### Why Quick Sort is generally preferred
For any non-trivial number of orders, O(n log n) crushes O(n²): at n = 10,000,
Quick Sort does ~130k operations while Bubble Sort does ~100 million. Quick Sort
also has excellent cache locality and sorts in place. Its O(n²) worst case is
avoided in practice with **randomized or median-of-three pivots**. Bubble Sort
is kept only for teaching and for tiny/nearly-sorted inputs.

> Note: Java's `Arrays.sort` uses **Dual-Pivot Quicksort** for primitives and
> **Timsort** (a merge-sort variant) for objects — production code should use it
> rather than a hand-rolled sort.

## How to run
```bash
javac Exercise3_SortingOrders/*.java
java Exercise3_SortingOrders.OrderSorter
```
