package com.digitalnurture;

/** Exercise 4: service that interacts with network resources. */
public class NetworkService {

    private final NetworkClient networkClient;

    public NetworkService(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }

    /** "Mock Connection" -> "Connected to Mock Connection". */
    public String connectToServer() {
        return "Connected to " + networkClient.connect();
    }
}
