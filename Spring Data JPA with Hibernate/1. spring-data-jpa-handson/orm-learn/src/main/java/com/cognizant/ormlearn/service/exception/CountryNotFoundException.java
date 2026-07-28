package com.cognizant.ormlearn.service.exception;

/** Hands-on 6: thrown when no country matches the given code. */
public class CountryNotFoundException extends Exception {

    public CountryNotFoundException(String message) {
        super(message);
    }
}
