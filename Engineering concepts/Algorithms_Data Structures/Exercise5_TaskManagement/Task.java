package Exercise5_TaskManagement;

/**
 * Task model stored inside the singly linked list.
 */
public class Task {
    private int taskId;
    private String taskName;
    private String status;   // e.g. "Pending", "In Progress", "Done"

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Task{id=%d, name=%s, status=%s}", taskId, taskName, status);
    }
}
