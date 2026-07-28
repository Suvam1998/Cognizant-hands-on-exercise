package Exercise4_EmployeeManagement;

/**
 * Employee management system backed by a fixed-capacity primitive array.
 * Demonstrates add, search, traverse, and delete directly on an Employee[].
 */
public class EmployeeManagement {

    private final Employee[] employees;
    private int count;   // number of slots currently in use

    public EmployeeManagement(int capacity) {
        this.employees = new Employee[capacity];
        this.count = 0;
    }

    /** Add at the next free slot. O(1) if capacity remains. */
    public boolean addEmployee(Employee e) {
        if (count == employees.length) {
            System.out.println("Add failed: array is full (capacity " + employees.length + ").");
            return false;
        }
        employees[count++] = e;
        return true;
    }

    /** Linear search by id. O(n). */
    public Employee searchEmployee(int employeeId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                return employees[i];
            }
        }
        return null;
    }

    /** Visit every element in order. O(n). */
    public void traverseEmployees() {
        System.out.println("---- Employees (" + count + ") ----");
        for (int i = 0; i < count; i++) {
            System.out.println("[" + i + "] " + employees[i]);
        }
        System.out.println("-----------------------------");
    }

    /**
     * Delete by id. O(n): O(n) to find + O(n) to shift the tail left to keep
     * the array contiguous.
     */
    public boolean deleteEmployee(int employeeId) {
        int index = -1;
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Delete failed: employee " + employeeId + " not found.");
            return false;
        }
        for (int i = index; i < count - 1; i++) {
            employees[i] = employees[i + 1];   // shift left to close the gap
        }
        employees[--count] = null;             // clear the now-unused slot
        return true;
    }

    public static void main(String[] args) {
        EmployeeManagement mgr = new EmployeeManagement(5);

        mgr.addEmployee(new Employee(1, "Aarav Sharma", "Software Engineer", 95000));
        mgr.addEmployee(new Employee(2, "Bhavna Rao", "Team Lead", 130000));
        mgr.addEmployee(new Employee(3, "Chetan Iyer", "QA Analyst", 72000));
        mgr.addEmployee(new Employee(4, "Divya Nair", "DevOps Engineer", 110000));
        mgr.traverseEmployees();

        System.out.println("\n>> Search id=3");
        System.out.println(mgr.searchEmployee(3));

        System.out.println("\n>> Delete id=2");
        mgr.deleteEmployee(2);
        mgr.traverseEmployees();

        System.out.println("\n>> Fill to capacity, then overflow (capacity = 5)");
        mgr.addEmployee(new Employee(5, "Esha Menon", "Product Manager", 145000)); // -> 4 used
        mgr.addEmployee(new Employee(6, "Farhan Ali", "Intern", 30000));           // -> 5 used (full)
        mgr.addEmployee(new Employee(7, "Gaurav Patel", "Analyst", 68000));        // rejected: full
        mgr.traverseEmployees();
    }
}
