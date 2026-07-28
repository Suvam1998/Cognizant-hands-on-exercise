package AdapterPatternExample;

/**
 * Adapters — implement the target PaymentProcessor interface and translate
 * its calls into each gateway's specific method.
 */
class PayPalAdapter implements PaymentProcessor {
    private final PayPalGateway gateway = new PayPalGateway();

    @Override
    public void processPayment(double amount) {
        gateway.sendPayment(amount); // dollars -> dollars
    }
}

class StripeAdapter implements PaymentProcessor {
    private final StripeGateway gateway = new StripeGateway();

    @Override
    public void processPayment(double amount) {
        int cents = (int) Math.round(amount * 100); // dollars -> cents
        gateway.makeTransaction(cents, "USD");
    }
}
