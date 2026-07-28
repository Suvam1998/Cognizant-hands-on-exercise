package ObserverPatternExample;

/** Observer interface — notified when the subject changes. */
public interface Observer {
    void update(String symbol, double price);
}
