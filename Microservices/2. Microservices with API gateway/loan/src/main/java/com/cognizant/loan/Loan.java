package com.cognizant.loan;

/** Dummy loan details (no backend connectivity, per the exercise). */
public record Loan(String number, String type, long loan, long emi, int tenure) {
}
