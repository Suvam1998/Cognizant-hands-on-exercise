package CommandPatternExample;

/**
 * Invoker — holds a command and triggers it, without knowing the receiver
 * or how the action is performed.
 */
public class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        if (command == null) {
            System.out.println("No command assigned to this button.");
            return;
        }
        command.execute();
    }
}
