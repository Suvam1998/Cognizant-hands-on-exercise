package com.digitalnurture;

/**
 * The external dependency we will MOCK in tests. In real life this might be a
 * remote REST client, a database gateway, etc. We only need the interface.
 */
public interface ExternalApi {

    /** Returns some data (mocked in tests). */
    String getData();

    /** Returns data for a specific id — used to demo argument matching. */
    String getDataById(int id);

    /** A void action — used to demo stubbing/verifying void methods. */
    void saveData(String data);

    /** Ordered-interaction demo helpers. */
    void connect();

    void disconnect();
}
