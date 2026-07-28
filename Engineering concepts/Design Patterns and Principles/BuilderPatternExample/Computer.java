package BuilderPatternExample;

/**
 * Immutable product built via a static nested Builder.
 * Required fields are passed to the Builder constructor; optional fields
 * are set through fluent with-methods.
 */
public class Computer {
    // required
    private final String cpu;
    private final String ram;
    // optional
    private final String storage;
    private final String gpu;
    private final boolean wifi;
    private final boolean bluetooth;

    // Private: instances are created only through the Builder.
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.gpu = builder.gpu;
        this.wifi = builder.wifi;
        this.bluetooth = builder.bluetooth;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ram='" + ram + '\'' +
                ", storage='" + storage + '\'' +
                ", gpu='" + gpu + '\'' +
                ", wifi=" + wifi +
                ", bluetooth=" + bluetooth +
                '}';
    }

    /** Static nested Builder. */
    public static class Builder {
        private final String cpu;   // required
        private final String ram;   // required
        private String storage = "256GB SSD";  // sensible defaults
        private String gpu = "Integrated";
        private boolean wifi = false;
        private boolean bluetooth = false;

        public Builder(String cpu, String ram) {
            this.cpu = cpu;
            this.ram = ram;
        }

        public Builder storage(String storage) { this.storage = storage; return this; }
        public Builder gpu(String gpu)          { this.gpu = gpu; return this; }
        public Builder wifi(boolean wifi)       { this.wifi = wifi; return this; }
        public Builder bluetooth(boolean bt)    { this.bluetooth = bt; return this; }

        public Computer build() {
            return new Computer(this);
        }
    }
}
