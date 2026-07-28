package StrategyPatternExample;

/**
 * Demonstrates selecting different payment strategies at runtime through the
 * same context.
 */
public class StrategyTest {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        System.out.println(">> Customer chooses Credit Card:");
        context.setPaymentStrategy(new CreditCardPayment("1234567812349876"));
        context.checkout(129.99);

        System.out.println("\n>> Customer switches to PayPal:");
        context.setPaymentStrategy(new PayPalPayment("suvam@example.com"));
        context.checkout(59.50);
    }
}
