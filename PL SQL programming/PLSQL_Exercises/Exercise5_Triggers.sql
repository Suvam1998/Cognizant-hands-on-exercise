-- =============================================================
-- Exercise 5: Triggers
-- =============================================================
SET SERVEROUTPUT ON;

-- -------------------------------------------------------------
-- Scenario 1: Update LastModified on any customer update.
-- -------------------------------------------------------------
CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
    BEFORE UPDATE ON Customers
    FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;
/

-- -------------------------------------------------------------
-- Scenario 2: Audit every inserted transaction.
-- -------------------------------------------------------------
CREATE OR REPLACE TRIGGER LogTransaction
    AFTER INSERT ON Transactions
    FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (TransactionID, AccountID, Amount, TransactionType, LogDate)
    VALUES (:NEW.TransactionID, :NEW.AccountID, :NEW.Amount,
            :NEW.TransactionType, SYSDATE);
END;
/

-- -------------------------------------------------------------
-- Scenario 3: Enforce deposit/withdrawal rules before insert.
-- -------------------------------------------------------------
CREATE OR REPLACE TRIGGER CheckTransactionRules
    BEFORE INSERT ON Transactions
    FOR EACH ROW
DECLARE
    v_balance Accounts.Balance%TYPE;
BEGIN
    IF :NEW.Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20010, 'Transaction amount must be positive');
    END IF;

    IF UPPER(:NEW.TransactionType) = 'WITHDRAWAL' THEN
        SELECT Balance INTO v_balance
        FROM   Accounts
        WHERE  AccountID = :NEW.AccountID;

        IF :NEW.Amount > v_balance THEN
            RAISE_APPLICATION_ERROR(-20011,
                'Withdrawal exceeds available balance');
        END IF;
    END IF;
END;
/

-- -------------------------------------------------------------
-- Quick tests
-- -------------------------------------------------------------
BEGIN
    -- Fires UpdateCustomerLastModified
    UPDATE Customers SET Name = Name WHERE CustomerID = 1;

    -- Valid deposit -> fires CheckTransactionRules (ok) + LogTransaction
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (100, 1, SYSDATE, 50, 'Deposit');

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Valid deposit inserted and audited.');
END;
/

-- This should be rejected by CheckTransactionRules (over-withdrawal):
BEGIN
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (101, 1, SYSDATE, 999999, 'Withdrawal');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Rejected as expected: ' || SQLERRM);
END;
/
