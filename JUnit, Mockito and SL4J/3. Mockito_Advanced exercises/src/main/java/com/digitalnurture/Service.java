package com.digitalnurture;

/** Exercise 1 & 5: service that depends on a Repository. */
public class Service {

    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    /** "Mock Data" -> "Processed Mock Data". */
    public String processData() {
        return "Processed " + repository.getData();
    }
}
