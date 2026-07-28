package com.digitalnurture;

/**
 * Exercise 4 subject-under-test: a stateful class that is a good fit for the
 * Arrange-Act-Assert pattern and @Before/@After setup/teardown (a fresh
 * account is arranged before every test).
 */
public class BankAccount {

    private double balance;

    public BankAccount(double openingBalance) {
        this.balance = openingBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal must be positive");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient funds");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}
