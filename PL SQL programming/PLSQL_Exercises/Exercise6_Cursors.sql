-- =============================================================
-- Exercise 6: Cursors (explicit)
-- =============================================================
SET SERVEROUTPUT ON;

-- -------------------------------------------------------------
-- Scenario 1: GenerateMonthlyStatements -- transactions this month.
-- -------------------------------------------------------------
DECLARE
    CURSOR c_statements IS
        SELECT c.CustomerID, c.Name, t.TransactionID, t.TransactionDate,
               t.Amount, t.TransactionType
        FROM   Transactions t
        JOIN   Accounts  a ON a.AccountID  = t.AccountID
        JOIN   Customers c ON c.CustomerID = a.CustomerID
        WHERE  TRUNC(t.TransactionDate, 'MM') = TRUNC(SYSDATE, 'MM')
        ORDER  BY c.CustomerID, t.TransactionDate;

    v_prev_customer Customers.CustomerID%TYPE := NULL;
BEGIN
    FOR rec IN c_statements LOOP
        IF v_prev_customer IS NULL OR rec.CustomerID <> v_prev_customer THEN
            DBMS_OUTPUT.PUT_LINE('--------------------------------------------');
            DBMS_OUTPUT.PUT_LINE('Statement for ' || rec.Name
                                 || ' (Customer ' || rec.CustomerID || ')');
            v_prev_customer := rec.CustomerID;
        END IF;
        DBMS_OUTPUT.PUT_LINE('  ' || TO_CHAR(rec.TransactionDate, 'YYYY-MM-DD')
            || '  ' || RPAD(rec.TransactionType, 10)
            || '  ' || rec.Amount);
    END LOOP;
END;
/

-- -------------------------------------------------------------
-- Scenario 2: ApplyAnnualFee -- deduct a fixed fee from all accounts.
-- -------------------------------------------------------------
DECLARE
    CURSOR c_accounts IS
        SELECT AccountID, Balance FROM Accounts FOR UPDATE;
    v_fee CONSTANT NUMBER := 50;
BEGIN
    FOR acct IN c_accounts LOOP
        UPDATE Accounts
        SET    Balance = Balance - v_fee,
               LastModified = SYSDATE
        WHERE  CURRENT OF c_accounts;

        DBMS_OUTPUT.PUT_LINE('Annual fee of ' || v_fee
            || ' deducted from account ' || acct.AccountID
            || ' (new balance ' || (acct.Balance - v_fee) || ').');
    END LOOP;
    COMMIT;
END;
/

-- -------------------------------------------------------------
-- Scenario 3: UpdateLoanInterestRates -- new policy.
-- Policy example: large loans (>= 100000) get +0.5 point,
-- everyone else gets +0.25 point.
-- -------------------------------------------------------------
DECLARE
    CURSOR c_loans IS
        SELECT LoanID, LoanAmount, InterestRate FROM Loans FOR UPDATE;
    v_new_rate Loans.InterestRate%TYPE;
BEGIN
    FOR ln IN c_loans LOOP
        IF ln.LoanAmount >= 100000 THEN
            v_new_rate := ln.InterestRate + 0.5;
        ELSE
            v_new_rate := ln.InterestRate + 0.25;
        END IF;

        UPDATE Loans
        SET    InterestRate = v_new_rate
        WHERE  CURRENT OF c_loans;

        DBMS_OUTPUT.PUT_LINE('Loan ' || ln.LoanID || ': rate '
            || ln.InterestRate || ' -> ' || v_new_rate);
    END LOOP;
    COMMIT;
END;
/
