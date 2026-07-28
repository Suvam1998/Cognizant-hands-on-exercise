# Exercise 10: MVC Pattern

**Intent:** Separate an application into three roles so each can change
independently:
- **Model** — data + business state (`Student`).
- **View** — presentation only (`StudentView.displayStudentDetails(...)`).
- **Controller** — mediates between them (`StudentController`): updates the model
  and tells the view to render.

The test creates a `Student`, wires it to the view via the controller, displays
it, updates the grade through the controller, and re-displays.

**Run**
```bash
javac MVCPatternExample/*.java
java  MVCPatternExample.MVCTest
```
