package BuilderPatternExample;

/**
 * Demonstrates building different Computer configurations with the same
 * fluent Builder — only the parts you care about are specified.
 */
public class BuilderTest {
    public static void main(String[] args) {
        Computer office = new Computer.Builder("Intel i5", "16GB")
                .storage("512GB SSD")
                .wifi(true)
                .build();

        Computer gaming = new Computer.Builder("AMD Ryzen 9", "32GB")
                .storage("2TB NVMe")
                .gpu("NVIDIA RTX 4080")
                .wifi(true)
                .bluetooth(true)
                .build();

        Computer basic = new Computer.Builder("Intel i3", "8GB").build();

        System.out.println("Office : " + office);
        System.out.println("Gaming : " + gaming);
        System.out.println("Basic  : " + basic);
    }
}
