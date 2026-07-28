package ProxyPatternExample;

/**
 * Real subject — expensive to create because it "loads" the image from a
 * remote server in its constructor.
 */
public class RealImage implements Image {
    private final String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromRemoteServer();
    }

    private void loadFromRemoteServer() {
        System.out.println("[RealImage] Loading '" + fileName + "' from remote server...");
        try {
            Thread.sleep(300); // simulate network latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void display() {
        System.out.println("[RealImage] Displaying '" + fileName + "'");
    }
}
