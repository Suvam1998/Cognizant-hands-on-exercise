package com.digitalnurture;

/** Exercise 2: service that calls an external REST API. */
public class ApiService {

    private final RestClient restClient;

    public ApiService(RestClient restClient) {
        this.restClient = restClient;
    }

    /** "Mock Response" -> "Fetched Mock Response". */
    public String fetchData() {
        return "Fetched " + restClient.getResponse();
    }
}
