package Exercise5_TaskManagement;

/**
 * Singly linked list managing Task objects.
 * Each Node holds a Task and a reference to the next Node.
 */
public class TaskLinkedList {

    /** Internal node of the singly linked list. */
    private static class Node {
        Task task;
        Node next;

        Node(Task task) {
            this.task = task;
        }
    }

    private Node head;
    private int size;

    /** Add a task at the end of the list. O(n) (walk to tail). */
    public void addTask(Task task) {
        Node node = new Node(task);
        if (head == null) {
            head = node;
        } else {
            Node cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = node;
        }
        size++;
    }

    /** Search a task by id. O(n). */
    public Task searchTask(int taskId) {
        Node cur = head;
        while (cur != null) {
            if (cur.task.getTaskId() == taskId) {
                return cur.task;
            }
            cur = cur.next;
        }
        return null;
    }

    /** Traverse and print every task. O(n). */
    public void traverseTasks() {
        System.out.println("---- Tasks (" + size + ") ----");
        Node cur = head;
        while (cur != null) {
            System.out.println(cur.task);
            cur = cur.next;
        }
        System.out.println("------------------------");
    }

    /**
     * Delete a task by id. O(n) to locate; the unlink itself is O(1).
     * Returns true if a node was removed.
     */
    public boolean deleteTask(int taskId) {
        if (head == null) {
            return false;
        }
        if (head.task.getTaskId() == taskId) {   // deleting the head
            head = head.next;
            size--;
            return true;
        }
        Node prev = head;
        Node cur = head.next;
        while (cur != null) {
            if (cur.task.getTaskId() == taskId) {
                prev.next = cur.next;             // unlink
                size--;
                return true;
            }
            prev = cur;
            cur = cur.next;
        }
        System.out.println("Delete failed: task " + taskId + " not found.");
        return false;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        TaskLinkedList list = new TaskLinkedList();

        list.addTask(new Task(1, "Design database schema", "Pending"));
        list.addTask(new Task(2, "Build REST API", "In Progress"));
        list.addTask(new Task(3, "Write unit tests", "Pending"));
        list.addTask(new Task(4, "Deploy to staging", "Pending"));
        list.traverseTasks();

        System.out.println("\n>> Search id=3");
        Task t = list.searchTask(3);
        System.out.println(t);
        if (t != null) {
            t.setStatus("Done");
        }

        System.out.println("\n>> Delete id=2 (middle) and id=1 (head)");
        list.deleteTask(2);
        list.deleteTask(1);
        list.traverseTasks();

        System.out.println("\n>> Delete missing id=99");
        list.deleteTask(99);
    }
}
