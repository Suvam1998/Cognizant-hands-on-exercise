package com.cognizant.account;

/** Dummy account details (no backend connectivity, per the exercise). */
public record Account(String number, String type, long balance) {
}
