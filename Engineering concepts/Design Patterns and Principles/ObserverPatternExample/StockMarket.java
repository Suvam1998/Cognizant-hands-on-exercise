package ObserverPatternExample;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete subject — holds a stock price and notifies all registered
 * observers whenever the price changes.
 */
public class StockMarket implements Stock {
    private final List<Observer> observers = new ArrayList<>();
    private final String symbol;
    private double price;

    public StockMarket(String symbol, double initialPrice) {
        this.symbol = symbol;
        this.price = initialPrice;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(symbol, price);
        }
    }

    /** Changing the price pushes an update to every observer. */
    public void setPrice(double price) {
        System.out.printf("%n[StockMarket] %s price changed to %.2f%n", symbol, price);
        this.price = price;
        notifyObservers();
    }
}
