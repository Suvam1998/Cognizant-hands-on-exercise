package ProxyPatternExample;

/**
 * Demonstrates lazy loading + caching: the remote load happens only once,
 * on the first display() call, even though display() is called repeatedly.
 */
public class ProxyTest {
    public static void main(String[] args) {
        Image image = new ProxyImage("landscape.png");

        System.out.println(">> Proxy created (no remote load yet).\n");

        System.out.println(">> First display() - triggers remote load:");
        image.display();

        System.out.println("\n>> Second display() - served from cache:");
        image.display();

        System.out.println("\n>> Third display() - still cached:");
        image.display();
    }
}
