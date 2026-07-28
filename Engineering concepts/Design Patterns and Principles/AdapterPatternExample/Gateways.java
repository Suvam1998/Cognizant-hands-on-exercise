package AdapterPatternExample;

/**
 * Adaptees — third-party gateways with their own incompatible method
 * signatures. We cannot change these classes.
 */
class PayPalGateway {
    public void sendPayment(double amountInDollars) {
        System.out.printf("[PayPal] Sent $%.2f via PayPal API.%n", amountInDollars);
    }
}

class StripeGateway {
    public void makeTransaction(int amountInCents, String currency) {
        System.out.printf("[Stripe] Charged %d cents (%s) via Stripe API.%n",
                amountInCents, currency);
    }
}
