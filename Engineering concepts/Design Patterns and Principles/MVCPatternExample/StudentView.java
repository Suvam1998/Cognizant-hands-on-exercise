package MVCPatternExample;

/** View — responsible only for presentation. */
public class StudentView {
    public void displayStudentDetails(String name, String id, String grade) {
        System.out.println("---- Student Details ----");
        System.out.println("Name : " + name);
        System.out.println("ID   : " + id);
        System.out.println("Grade: " + grade);
        System.out.println("-------------------------");
    }
}
