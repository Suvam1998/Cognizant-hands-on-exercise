package StrategyPatternExample;

/**
 * Context — holds a PaymentStrategy and delegates the work to it.
 * The strategy can be swapped at runtime.
 */
public class PaymentContext {
    private PaymentStrategy strategy;

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void checkout(double amount) {
        if (strategy == null) {
            throw new IllegalStateException("No payment strategy selected.");
        }
        strategy.pay(amount);
    }
}
