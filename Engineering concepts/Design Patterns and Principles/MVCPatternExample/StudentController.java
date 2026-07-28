package MVCPatternExample;

/**
 * Controller — mediates between Model and View. It updates the model and
 * tells the view what to render, keeping the two decoupled.
 */
public class StudentController {
    private final Student model;
    private final StudentView view;

    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }

    // Setters update the model.
    public void setStudentName(String name)   { model.setName(name); }
    public void setStudentId(String id)       { model.setId(id); }
    public void setStudentGrade(String grade) { model.setGrade(grade); }

    // Getters read from the model.
    public String getStudentName()  { return model.getName(); }
    public String getStudentGrade() { return model.getGrade(); }

    /** Ask the view to render the current model state. */
    public void updateView() {
        view.displayStudentDetails(model.getName(), model.getId(), model.getGrade());
    }
}
