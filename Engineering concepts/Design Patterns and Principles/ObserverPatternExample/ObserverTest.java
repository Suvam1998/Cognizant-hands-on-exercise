package ObserverPatternExample;

/**
 * Demonstrates registering observers, pushing updates, and deregistering.
 */
public class ObserverTest {
    public static void main(String[] args) {
        StockMarket stock = new StockMarket("AAPL", 190.00);

        Observer mobile = new MobileApp("Suvam");
        Observer web = new WebApp();

        stock.registerObserver(mobile);
        stock.registerObserver(web);

        stock.setPrice(192.50);   // both notified
        stock.setPrice(188.75);   // both notified

        System.out.println("\n>> Mobile app unsubscribes.");
        stock.deregisterObserver(mobile);

        stock.setPrice(195.00);   // only web notified
    }
}
