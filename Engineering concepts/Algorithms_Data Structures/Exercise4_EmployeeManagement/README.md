# Exercise 4: Employee Management System

## 1. Understand Array Representation

### How arrays are represented in memory
An array is a **contiguous block of memory** of fixed size. All elements are the
same type, so the address of element `i` is computed directly:
`address(i) = base_address + i * element_size`. Because this is a single
multiply-and-add, accessing any element by index is **O(1) random access**.

### Advantages
- **O(1) indexed access** — jump straight to any position.
- **Cache-friendly** — contiguous layout means sequential traversal hits CPU
  cache lines efficiently.
- **Low overhead** — no per-element pointers (unlike linked lists).
- **Predictable memory** — size and layout are known up front.

## 2. Setup
`Employee` has `employeeId`, `name`, `position`, `salary` (package
`Exercise4_EmployeeManagement`).

## 3. Implementation
`EmployeeManagement` stores records in an `Employee[]` and supports:
- `addEmployee` — append at the next free slot,
- `searchEmployee` — linear search by id,
- `traverseEmployees` — iterate all in order,
- `deleteEmployee` — find, then shift the tail left to stay contiguous.

## 4. Analysis

### Time complexity
| Operation | Complexity | Note |
|---|---|---|
| Add (append) | O(1) | just write to `array[count++]` (capacity permitting) |
| Search by id | O(n) | must scan; array isn't indexed by id |
| Traverse | O(n) | visit every element once |
| Delete by id | O(n) | O(n) find + O(n) shift to close the gap |
| Access by index | O(1) | direct address computation |

### Limitations of arrays & when to use them
**Limitations**
- **Fixed size** — capacity is set at creation; growing means allocating a new,
  larger array and copying (O(n)).
- **Costly middle insert/delete** — O(n) shifting to keep elements contiguous.
- **O(n) search** unless a separate index (e.g. a HashMap) is maintained.

**Use arrays when**
- the size is known/bounded and stable,
- you need fast **indexed** access and tight, cache-friendly memory,
- inserts/deletes are mostly at the end (append/pop).

For frequent id lookups use a `HashMap`; for frequent middle insert/delete use a
linked list; for a growable array use `ArrayList` (amortized O(1) append).

## How to run
```bash
javac Exercise4_EmployeeManagement/*.java
java Exercise4_EmployeeManagement.EmployeeManagement
```
