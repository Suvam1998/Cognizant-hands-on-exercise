-- =============================================================
-- Exercise 3: Stored Procedures
-- =============================================================
SET SERVEROUTPUT ON;

-- -------------------------------------------------------------
-- Scenario 1: ProcessMonthlyInterest -- +1% to savings accounts.
-- -------------------------------------------------------------
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
BEGIN
    UPDATE Accounts
    SET    Balance       = Balance + (Balance * 0.01),
           LastModified  = SYSDATE
    WHERE  AccountType = 'Savings';

    DBMS_OUTPUT.PUT_LINE('Monthly interest applied to ' || SQL%ROWCOUNT
                         || ' savings account(s).');
    COMMIT;
END;
/

-- -------------------------------------------------------------
-- Scenario 2: UpdateEmployeeBonus -- add bonus % to a department.
-- -------------------------------------------------------------
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department IN VARCHAR2,
    p_bonus_pct  IN NUMBER
) AS
BEGIN
    UPDATE Employees
    SET    Salary = Salary + (Salary * p_bonus_pct / 100)
    WHERE  Department = p_department;

    DBMS_OUTPUT.PUT_LINE('Applied ' || p_bonus_pct || '% bonus to '
                         || SQL%ROWCOUNT || ' employee(s) in ' || p_department || '.');
    COMMIT;
END;
/

-- -------------------------------------------------------------
-- Scenario 3: TransferFunds -- with sufficient-balance check.
-- -------------------------------------------------------------
CREATE OR REPLACE PROCEDURE TransferFunds (
    p_from_account IN NUMBER,
    p_to_account   IN NUMBER,
    p_amount       IN NUMBER
) AS
    v_balance Accounts.Balance%TYPE;
BEGIN
    SELECT Balance INTO v_balance
    FROM   Accounts
    WHERE  AccountID = p_from_account
    FOR UPDATE;

    IF v_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20002,
            'Insufficient balance in account ' || p_from_account);
    END IF;

    UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_from_account;
    UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_to_account;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transferred ' || p_amount || ' from '
                         || p_from_account || ' to ' || p_to_account || '.');
END;
/

-- -------------------------------------------------------------
-- Quick tests
-- -------------------------------------------------------------
BEGIN
    ProcessMonthlyInterest;
    UpdateEmployeeBonus('IT', 10);
    TransferFunds(3, 1, 500);   -- account 3 has plenty of balance
END;
/
