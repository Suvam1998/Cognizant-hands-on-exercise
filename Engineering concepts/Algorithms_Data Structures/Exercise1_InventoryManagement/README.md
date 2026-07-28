# Exercise 1: Inventory Management System

## 1. Understand the Problem

### Why data structures and algorithms matter for large inventories
A warehouse inventory can hold hundreds of thousands of products. The system
constantly performs lookups, updates (restocking / repricing), insertions, and
deletions. If the underlying data structure forces a full scan for every
operation, response time grows linearly with inventory size and the system
becomes unusable at scale. Choosing the right data structure keeps each
operation fast (ideally constant time), and choosing the right algorithm keeps
bulk operations (search, sort, reporting) efficient. Good DSA choices directly
translate into lower latency, lower memory use, and better throughput.

### Data structures suitable for this problem
| Structure | Fit |
|---|---|
| **ArrayList** | Good for ordered traversal/reporting; but lookup/update/delete by id is O(n) because you must find the element first. |
| **HashMap** | Best fit here — keyed on `productId`, giving average **O(1)** add/lookup/update/delete. |
| **TreeMap** | Use if you also need products kept **sorted** by id/key (O(log n) ops, ordered iteration). |
| **LinkedList** | Cheap insert/delete once you have the node, but O(n) lookup — poor for id-based access. |

**Choice:** `HashMap<String, Product>` keyed on `productId`.

## 2. Setup
Single-project layout; the `Product` model and the `InventoryManagement` driver
live in this folder under package `Exercise1_InventoryManagement`.

## 3. Implementation
- `Product` — attributes `productId`, `productName`, `quantity`, `price`.
- `InventoryManagement` — `addProduct`, `updateProduct`, `deleteProduct`,
  `getProduct`, backed by a `HashMap`.

## 4. Analysis

### Time complexity (HashMap)
| Operation | Average | Worst case |
|---|---|---|
| Add | O(1) | O(n) — many hash collisions in one bucket |
| Update | O(1) | O(n) |
| Delete | O(1) | O(n) |
| Lookup by id | O(1) | O(n) |

Worst case O(n) occurs only with pathological hashing; Java 8+ mitigates it by
turning an over-full bucket into a balanced tree, bounding it at O(log n).

### How to optimize
- **Good keys / hashCode:** using `productId` (already unique) keeps buckets
  balanced and collisions rare.
- **Right initial capacity & load factor:** presizing the map to the expected
  inventory size avoids repeated rehashing on growth.
- **Secondary indexes:** if you also search by name or category, add extra maps
  (e.g. `Map<String, List<Product>>` by category) so those queries stay O(1)
  instead of scanning.
- **Batch / concurrent access:** use `ConcurrentHashMap` for multi-threaded
  warehouse terminals to avoid lock contention.

## How to run
```bash
# from: Engineering concepts/Algorithms_Data Structures
javac Exercise1_InventoryManagement/*.java
java Exercise1_InventoryManagement.InventoryManagement
```
