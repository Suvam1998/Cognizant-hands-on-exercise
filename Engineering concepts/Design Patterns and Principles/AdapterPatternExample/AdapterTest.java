package AdapterPatternExample;

/**
 * The client works only against PaymentProcessor; adapters hide the
 * differences between the underlying gateways.
 */
public class AdapterTest {
    public static void main(String[] args) {
        PaymentProcessor paypal = new PayPalAdapter();
        PaymentProcessor stripe = new StripeAdapter();

        System.out.println("Processing via PayPal:");
        paypal.processPayment(49.99);

        System.out.println("\nProcessing via Stripe:");
        stripe.processPayment(49.99);
    }
}
