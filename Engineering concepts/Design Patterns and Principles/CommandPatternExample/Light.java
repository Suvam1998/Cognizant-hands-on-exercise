package CommandPatternExample;

/** Receiver — knows how to perform the actual work. */
public class Light {
    private final String location;

    public Light(String location) { this.location = location; }

    public void on()  { System.out.println(location + " light is ON"); }
    public void off() { System.out.println(location + " light is OFF"); }
}
