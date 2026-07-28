package CommandPatternExample;

/** Concrete commands — bind a receiver to an action. */
class LightOnCommand implements Command {
    private final Light light;
    public LightOnCommand(Light light) { this.light = light; }

    @Override
    public void execute() { light.on(); }
}

class LightOffCommand implements Command {
    private final Light light;
    public LightOffCommand(Light light) { this.light = light; }

    @Override
    public void execute() { light.off(); }
}
