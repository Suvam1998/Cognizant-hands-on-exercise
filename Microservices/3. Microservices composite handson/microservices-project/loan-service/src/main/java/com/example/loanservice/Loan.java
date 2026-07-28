package com.example.loanservice;

public record Loan(String number, String type, long loan, long emi, int tenure) {
}
