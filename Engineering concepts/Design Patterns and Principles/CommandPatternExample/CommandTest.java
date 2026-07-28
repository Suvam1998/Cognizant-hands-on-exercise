package CommandPatternExample;

/**
 * Demonstrates issuing on/off commands to a device via the RemoteControl
 * invoker.
 */
public class CommandTest {
    public static void main(String[] args) {
        Light livingRoom = new Light("Living Room");

        Command lightOn = new LightOnCommand(livingRoom);
        Command lightOff = new LightOffCommand(livingRoom);

        RemoteControl remote = new RemoteControl();

        System.out.println(">> Press ON:");
        remote.setCommand(lightOn);
        remote.pressButton();

        System.out.println("\n>> Press OFF:");
        remote.setCommand(lightOff);
        remote.pressButton();
    }
}
