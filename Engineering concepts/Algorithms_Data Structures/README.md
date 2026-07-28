# Algorithms & Data Structures — Hands-On Exercises

Seven Java exercises covering core data structures, searching, sorting,
recursion, and Big-O analysis. Each exercise is a self-contained Java package
with a `README.md` answering the "Understand" and "Analysis" write-up questions.

| # | Exercise | Focus | Key structure / algorithm |
|---|---|---|---|
| 1 | Inventory Management System | Data structures for large data | `HashMap` (O(1) add/update/delete) |
| 2 | E-commerce Search Function | Asymptotic notation (Big O) | Linear vs Binary search |
| 3 | Sorting Customer Orders | Sorting algorithms | Bubble Sort vs Quick Sort |
| 4 | Employee Management System | Array representation | Array add/search/traverse/delete |
| 5 | Task Management System | Linked lists | Singly linked list |
| 6 | Library Management System | Search algorithms | Linear vs Binary search (by title) |
| 7 | Financial Forecasting | Recursion & optimization | Recursive future value + memoization |

## Requirements
- JDK 8 or newer (developed/verified on JDK 24).

## Build & run
Each exercise is in its own package folder. From **this** directory:

```bash
# Example — Exercise 1
javac Exercise1_InventoryManagement/*.java
java  Exercise1_InventoryManagement.InventoryManagement
```

Substitute the folder and main class for the others:

| Exercise | Main class to run |
|---|---|
| 1 | `Exercise1_InventoryManagement.InventoryManagement` |
| 2 | `Exercise2_EcommerceSearch.ProductSearch` |
| 3 | `Exercise3_SortingOrders.OrderSorter` |
| 4 | `Exercise4_EmployeeManagement.EmployeeManagement` |
| 5 | `Exercise5_TaskManagement.TaskLinkedList` |
| 6 | `Exercise6_LibraryManagement.LibrarySearch` |
| 7 | `Exercise7_FinancialForecasting.FinancialForecasting` |

Each exercise's own `README.md` contains the full theory and complexity analysis.
