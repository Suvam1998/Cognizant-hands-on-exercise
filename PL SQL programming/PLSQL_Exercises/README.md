# PL/SQL Programming Exercises (Oracle)

Oracle PL/SQL solutions for a bank domain, covering control structures, error
handling, stored procedures, functions, triggers, cursors, and packages.

> **Target:** Oracle Database (tested syntax for Oracle 12c+ / 19c / 21c —
> uses `GENERATED ALWAYS AS IDENTITY`). Run with `SET SERVEROUTPUT ON` to see
> `DBMS_OUTPUT` messages. These scripts were **not executed** in this
> environment (no Oracle instance available); they are written to Oracle syntax
> and standard PL/SQL semantics.

## Files (run in order)
| File | Contents |
|---|---|
| `00_schema.sql` | Tables + sample data. **Run first.** Adds `IsVIP` to Customers and creates `ErrorLog` and `AuditLog` support tables (needed by the exercises). |
| `Exercise1_ControlStructures.sql` | Loops/IF: senior loan discount, VIP flag, 30-day loan reminders |
| `Exercise2_ErrorHandling.sql` | `SafeTransferFunds`, `UpdateSalary`, `AddNewCustomer` with exception handling + `ErrorLog` |
| `Exercise3_StoredProcedures.sql` | `ProcessMonthlyInterest`, `UpdateEmployeeBonus`, `TransferFunds` |
| `Exercise4_Functions.sql` | `CalculateAge`, `CalculateMonthlyInstallment`, `HasSufficientBalance` |
| `Exercise5_Triggers.sql` | `UpdateCustomerLastModified`, `LogTransaction`, `CheckTransactionRules` |
| `Exercise6_Cursors.sql` | `GenerateMonthlyStatements`, `ApplyAnnualFee`, `UpdateLoanInterestRates` |
| `Exercise7_Packages.sql` | `CustomerManagement`, `EmployeeManagement`, `AccountOperations` packages |

## How to run
Using SQL*Plus / SQLcl / SQL Developer, connected to your schema:
```sql
@00_schema.sql
@Exercise1_ControlStructures.sql
@Exercise2_ErrorHandling.sql
@Exercise3_StoredProcedures.sql
@Exercise4_Functions.sql
@Exercise5_Triggers.sql
@Exercise6_Cursors.sql
@Exercise7_Packages.sql
```
Each exercise file ends with a small test block that exercises the objects and
prints results via `DBMS_OUTPUT`.

## Notes on schema additions (required by the exercises)
The provided schema omits three things the questions rely on; `00_schema.sql`
adds them:
- **`Customers.IsVIP`** — Exercise 1 Scenario 2 sets a VIP flag.
- **`ErrorLog`** — Exercise 2 logs error messages here.
- **`AuditLog`** — Exercise 5 Scenario 2 records every transaction here.

## Interpretation notes
- **Ex1.1 "1% discount on interest rate"** is applied as `rate = rate * 0.99`
  (1% of the current rate). Change to `rate - 1` for a 1 percentage-point cut.
- **Ex4.2 EMI** uses the standard amortization formula and handles a 0% rate.
- **Employee `Salary`** is treated as a monthly figure, so annual salary is
  `Salary * 12` (Ex7.2). Adjust if your data stores annual salary.
- **`HasSufficientBalance`** returns `BOOLEAN` (usable in PL/SQL; wrap it if you
  need to call it from plain SQL).
