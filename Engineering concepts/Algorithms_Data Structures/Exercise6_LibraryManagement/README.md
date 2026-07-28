# Exercise 6: Library Management System

## 1. Understand Search Algorithms
- **Linear Search:** check each element in turn until a match is found or the
  list ends. Works on **any** data (sorted or not). Time: O(n).
- **Binary Search:** repeatedly halve a **sorted** list — compare the middle
  element and discard the half that can't contain the target. Time: O(log n),
  but requires the data to be sorted first.

## 2. Setup
`Book` has `bookId`, `title`, `author` (package `Exercise6_LibraryManagement`).

## 3. Implementation
- `linearSearchByTitle` — case-insensitive scan over an unsorted array.
- `binarySearchByTitle` — case-insensitive binary search over an array sorted by
  title (`String.CASE_INSENSITIVE_ORDER`).

## 4. Analysis

### Complexity comparison
| | Linear Search | Binary Search |
|---|---|---|
| Time | O(n) | O(log n) |
| Precondition | none | data must be **sorted** |
| One-time sort cost | — | O(n log n) |
| Example n = 1,000,000 | up to 1,000,000 comparisons | ~20 comparisons |

### When to use each
- **Linear search** — best for **small** collections, **unsorted** data, or lists
  that change on every operation (keeping them sorted would cost more than it
  saves).
- **Binary search** — best for **large**, **sorted**, mostly-static collections
  (like a library catalog that is read far more often than it is edited). Sort
  once, then every title lookup is O(log n).

**Rule of thumb:** if the data set is large and you search it repeatedly, pay the
one-time O(n log n) sort so each subsequent search is O(log n). If it's tiny or
constantly changing, linear search's simplicity wins. For exact-match title
lookups at scale, a `HashMap<String, Book>` (O(1)) beats both.

## How to run
```bash
javac Exercise6_LibraryManagement/*.java
java Exercise6_LibraryManagement.LibrarySearch
```
