package com.digitalnurture;

/**
 * Subject-under-test. It depends on ExternalApi, which is injected via the
 * constructor so tests can pass in a Mockito mock.
 */
public class MyService {

    private final ExternalApi api;

    public MyService(ExternalApi api) {
        this.api = api;
    }

    /** Delegates to the external API. */
    public String fetchData() {
        return api.getData();
    }

    /** Delegates a parameterized call — used for argument matching. */
    public String fetchDataById(int id) {
        return api.getDataById(id);
    }

    /** Delegates a void action. */
    public void saveData(String data) {
        api.saveData(data);
    }

    /**
     * Performs a sequence of calls in a fixed order — used to verify
     * interaction order: connect -> saveData -> disconnect.
     */
    public void performSequence(String data) {
        api.connect();
        api.saveData(data);
        api.disconnect();
    }
}
