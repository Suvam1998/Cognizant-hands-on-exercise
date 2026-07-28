package ProxyPatternExample;

/**
 * Proxy — stands in for RealImage. Adds:
 *  - lazy initialization: the RealImage is created only on the first display(),
 *  - caching: subsequent display() calls reuse the already-loaded RealImage.
 */
public class ProxyImage implements Image {
    private final String fileName;
    private RealImage realImage;   // created lazily, then cached

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {                 // lazy load on first use
            realImage = new RealImage(fileName);
        } else {
            System.out.println("[ProxyImage] Using cached image for '" + fileName + "'");
        }
        realImage.display();
    }
}
