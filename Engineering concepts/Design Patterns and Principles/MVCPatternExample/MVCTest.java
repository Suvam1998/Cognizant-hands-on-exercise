package MVCPatternExample;

/**
 * Demonstrates the MVC flow: create the model, wire it to the view through a
 * controller, display, then update via the controller and re-display.
 */
public class MVCTest {
    public static void main(String[] args) {
        // Model
        Student student = new Student("Suvam", "S101", "A");
        // View
        StudentView view = new StudentView();
        // Controller ties them together
        StudentController controller = new StudentController(student, view);

        System.out.println(">> Initial state:");
        controller.updateView();

        System.out.println("\n>> Updating grade via controller...");
        controller.setStudentGrade("A+");

        System.out.println("\n>> After update:");
        controller.updateView();
    }
}
