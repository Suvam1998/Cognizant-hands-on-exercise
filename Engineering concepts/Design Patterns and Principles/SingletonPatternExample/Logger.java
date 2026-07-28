package SingletonPatternExample;

/**
 * Singleton Logger.
 *
 * Guarantees a single shared instance for the whole application:
 *  - a private static instance held by the class,
 *  - a private constructor so no one else can `new` it,
 *  - a public static accessor that always returns the same instance.
 *
 * Uses the thread-safe "initialization-on-demand holder" idiom.
 */
public class Logger {

    // Private constructor prevents external instantiation.
    private Logger() {
        System.out.println("[Logger] Single instance created.");
    }

    // Holder is loaded (and INSTANCE created) only on first getInstance() call.
    private static class Holder {
        private static final Logger INSTANCE = new Logger();
    }

    /** Public global access point — always the same instance. */
    public static Logger getInstance() {
        return Holder.INSTANCE;
    }

    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}
