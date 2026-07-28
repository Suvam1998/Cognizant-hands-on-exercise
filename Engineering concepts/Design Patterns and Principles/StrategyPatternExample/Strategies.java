package StrategyPatternExample;

/** Concrete strategies. */
class CreditCardPayment implements PaymentStrategy {
    private final String cardNumber;
    public CreditCardPayment(String cardNumber) { this.cardNumber = cardNumber; }

    @Override
    public void pay(double amount) {
        String masked = "****-****-****-" + cardNumber.substring(cardNumber.length() - 4);
        System.out.printf("Paid %.2f using Credit Card %s%n", amount, masked);
    }
}

class PayPalPayment implements PaymentStrategy {
    private final String email;
    public PayPalPayment(String email) { this.email = email; }

    @Override
    public void pay(double amount) {
        System.out.printf("Paid %.2f using PayPal account %s%n", amount, email);
    }
}
