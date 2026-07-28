package DecoratorPatternExample;

/**
 * Demonstrates stacking channels at runtime: Email + SMS + Slack, all through
 * the single Notifier interface.
 */
public class DecoratorTest {
    public static void main(String[] args) {
        // Base: email only
        Notifier email = new EmailNotifier();

        // Email + SMS
        Notifier emailSms = new SMSNotifierDecorator(new EmailNotifier());

        // Email + SMS + Slack (decorators composed dynamically)
        Notifier allChannels =
                new SlackNotifierDecorator(
                        new SMSNotifierDecorator(
                                new EmailNotifier()));

        System.out.println("== Email only ==");
        email.send("Server rebooted.");

        System.out.println("\n== Email + SMS ==");
        emailSms.send("High CPU usage detected.");

        System.out.println("\n== Email + SMS + Slack ==");
        allChannels.send("Production outage!");
    }
}
