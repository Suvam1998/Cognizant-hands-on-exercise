package DecoratorPatternExample;

/**
 * Abstract decorator — implements Notifier and wraps another Notifier,
 * delegating to it so extra channels can be layered on dynamically.
 */
public abstract class NotifierDecorator implements Notifier {
    protected final Notifier wrapped;

    protected NotifierDecorator(Notifier wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void send(String message) {
        wrapped.send(message); // pass the request down the chain first
    }
}

/** Adds an SMS channel on top of whatever it wraps. */
class SMSNotifierDecorator extends NotifierDecorator {
    public SMSNotifierDecorator(Notifier wrapped) { super(wrapped); }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("SMS    -> " + message);
    }
}

/** Adds a Slack channel on top of whatever it wraps. */
class SlackNotifierDecorator extends NotifierDecorator {
    public SlackNotifierDecorator(Notifier wrapped) { super(wrapped); }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Slack  -> " + message);
    }
}
