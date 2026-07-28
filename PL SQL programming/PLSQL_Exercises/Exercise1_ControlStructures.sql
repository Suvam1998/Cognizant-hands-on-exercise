-- =============================================================
-- Exercise 1: Control Structures
-- =============================================================
SET SERVEROUTPUT ON;

-- -------------------------------------------------------------
-- Scenario 1: 1% discount on loan interest for customers > 60.
-- "1% discount" is interpreted as reducing the rate by 1% of its
-- current value (rate = rate * 0.99).
-- -------------------------------------------------------------
BEGIN
    FOR c IN (
        SELECT cu.CustomerID,
               FLOOR(MONTHS_BETWEEN(SYSDATE, cu.DOB) / 12) AS Age
        FROM   Customers cu
    ) LOOP
        IF c.Age > 60 THEN
            UPDATE Loans
            SET    InterestRate = InterestRate * 0.99
            WHERE  CustomerID = c.CustomerID;

            DBMS_OUTPUT.PUT_LINE('Applied 1% discount to loans of customer '
                                 || c.CustomerID || ' (age ' || c.Age || ').');
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- -------------------------------------------------------------
-- Scenario 2: Set IsVIP = TRUE for customers with balance > 10000.
-- -------------------------------------------------------------
BEGIN
    FOR c IN (SELECT CustomerID, Balance FROM Customers) LOOP
        IF c.Balance > 10000 THEN
            UPDATE Customers
            SET    IsVIP = 'TRUE'
            WHERE  CustomerID = c.CustomerID;

            DBMS_OUTPUT.PUT_LINE('Customer ' || c.CustomerID
                                 || ' promoted to VIP (balance ' || c.Balance || ').');
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- -------------------------------------------------------------
-- Scenario 3: Reminders for loans due within the next 30 days.
-- -------------------------------------------------------------
BEGIN
    FOR r IN (
        SELECT l.LoanID, l.EndDate, c.Name
        FROM   Loans l
        JOIN   Customers c ON c.CustomerID = l.CustomerID
        WHERE  l.EndDate BETWEEN SYSDATE AND SYSDATE + 30
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: Dear ' || r.Name
            || ', your loan #' || r.LoanID
            || ' is due on ' || TO_CHAR(r.EndDate, 'YYYY-MM-DD') || '.');
    END LOOP;
END;
/
