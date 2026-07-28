package AdapterPatternExample;

/** Target interface the application expects. */
public interface PaymentProcessor {
    void processPayment(double amount);
}
