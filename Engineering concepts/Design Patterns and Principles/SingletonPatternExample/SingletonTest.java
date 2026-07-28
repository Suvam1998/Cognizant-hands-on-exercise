package SingletonPatternExample;

/**
 * Verifies that Logger is a singleton: two references obtained separately
 * point to the exact same object.
 */
public class SingletonTest {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        logger1.log("Application started.");

        Logger logger2 = Logger.getInstance();
        logger2.log("Processing data.");

        System.out.println("\nlogger1 hashCode: " + System.identityHashCode(logger1));
        System.out.println("logger2 hashCode: " + System.identityHashCode(logger2));
        System.out.println("Same instance? " + (logger1 == logger2));
    }
}
