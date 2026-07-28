package StrategyPatternExample;

/** Strategy interface — a family of interchangeable payment algorithms. */
public interface PaymentStrategy {
    void pay(double amount);
}
