package DecoratorPatternExample;

/** Concrete component — the base notification channel. */
public class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Email  -> " + message);
    }
}
