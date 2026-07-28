# Exercise 5: Task Management System

## 1. Understand Linked Lists
A linked list stores elements in **nodes**, each holding data plus a reference to
the next node. Unlike an array, nodes are **not contiguous** in memory.

| Type | Structure | Notes |
|---|---|---|
| **Singly Linked List** | each node points to `next` only | traverse one direction; O(1) insert/delete at head |
| **Doubly Linked List** | each node points to `next` and `prev` | traverse both directions; O(1) delete of a known node |
| **Circular Linked List** | tail points back to head | useful for round-robin / cyclic iteration |

This exercise implements a **singly linked list**.

## 2. Setup
`Task` has `taskId`, `taskName`, `status` (package `Exercise5_TaskManagement`).

## 3. Implementation
`TaskLinkedList` with an inner `Node` class supports:
- `addTask` — append at the tail,
- `searchTask` — find by id,
- `traverseTasks` — visit all nodes,
- `deleteTask` — unlink by id (handles head, middle, missing).

## 4. Analysis

### Time complexity
| Operation | Complexity | Note |
|---|---|---|
| Add at head | O(1) | just repoint head |
| Add at tail | O(n) | walk to the tail (O(1) if a tail pointer is kept) |
| Search | O(n) | no random access; must follow links |
| Traverse | O(n) | visit each node once |
| Delete by id | O(n) | O(n) to find + O(1) to unlink |

### Advantages of linked lists over arrays for dynamic data
- **Dynamic size** — grows/shrinks one node at a time; no fixed capacity and no
  costly resize-and-copy.
- **O(1) insert/delete at a known position** — just repoint references; no
  shifting of the tail like an array requires.
- **No wasted contiguous block** — memory is allocated per node as needed.

**Trade-offs:** no O(1) indexed access (search is always O(n)), and each node
carries an extra pointer plus has worse cache locality than an array. Use linked
lists when the data set changes size frequently and you insert/delete often;
use arrays when you need fast indexed access and mostly read.

## How to run
```bash
javac Exercise5_TaskManagement/*.java
java Exercise5_TaskManagement.TaskLinkedList
```
