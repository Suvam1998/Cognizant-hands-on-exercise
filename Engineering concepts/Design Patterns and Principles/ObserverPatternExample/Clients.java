package ObserverPatternExample;

/** Concrete observers — react to price updates. */
class MobileApp implements Observer {
    private final String user;
    public MobileApp(String user) { this.user = user; }

    @Override
    public void update(String symbol, double price) {
        System.out.printf("  [MobileApp:%s] %s is now %.2f%n", user, symbol, price);
    }
}

class WebApp implements Observer {
    @Override
    public void update(String symbol, double price) {
        System.out.printf("  [WebApp] Dashboard updated: %s = %.2f%n", symbol, price);
    }
}
