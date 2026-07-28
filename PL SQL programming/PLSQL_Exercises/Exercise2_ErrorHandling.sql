-- =============================================================
-- Exercise 2: Error Handling
-- =============================================================
SET SERVEROUTPUT ON;

-- -------------------------------------------------------------
-- Scenario 1: SafeTransferFunds with rollback + error logging.
-- -------------------------------------------------------------
CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_from_account IN NUMBER,
    p_to_account   IN NUMBER,
    p_amount       IN NUMBER
) AS
    e_insufficient_funds EXCEPTION;
    v_balance            Accounts.Balance%TYPE;
BEGIN
    -- Lock the source row and read its balance.
    SELECT Balance INTO v_balance
    FROM   Accounts
    WHERE  AccountID = p_from_account
    FOR UPDATE;

    IF p_amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Transfer amount must be positive');
    END IF;

    IF v_balance < p_amount THEN
        RAISE e_insufficient_funds;
    END IF;

    UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_from_account;
    UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_to_account;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transferred ' || p_amount || ' from ' || p_from_account
                         || ' to ' || p_to_account || '.');
EXCEPTION
    WHEN e_insufficient_funds THEN
        ROLLBACK;
        INSERT INTO ErrorLog (Context, ErrorMessage)
        VALUES ('SafeTransferFunds',
                'Insufficient funds in account ' || p_from_account);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('ERROR: Insufficient funds. Transaction rolled back.');
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        INSERT INTO ErrorLog (Context, ErrorMessage)
        VALUES ('SafeTransferFunds', 'Account not found');
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('ERROR: Account not found. Transaction rolled back.');
    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO ErrorLog (Context, ErrorMessage)
        VALUES ('SafeTransferFunds', SQLERRM);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM || '. Transaction rolled back.');
END;
/

-- -------------------------------------------------------------
-- Scenario 2: UpdateSalary; handle non-existent employee.
-- -------------------------------------------------------------
CREATE OR REPLACE PROCEDURE UpdateSalary (
    p_employee_id IN NUMBER,
    p_percentage  IN NUMBER
) AS
BEGIN
    UPDATE Employees
    SET    Salary = Salary + (Salary * p_percentage / 100)
    WHERE  EmployeeID = p_employee_id;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE NO_DATA_FOUND;   -- no such employee
    END IF;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Salary updated for employee ' || p_employee_id
                         || ' by ' || p_percentage || '%.');
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        INSERT INTO ErrorLog (Context, ErrorMessage)
        VALUES ('UpdateSalary', 'Employee ID ' || p_employee_id || ' does not exist');
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('ERROR: Employee ' || p_employee_id || ' not found.');
    WHEN OTHERS THEN
        INSERT INTO ErrorLog (Context, ErrorMessage)
        VALUES ('UpdateSalary', SQLERRM);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
END;
/

-- -------------------------------------------------------------
-- Scenario 3: AddNewCustomer; prevent duplicate IDs.
-- -------------------------------------------------------------
CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_customer_id IN NUMBER,
    p_name        IN VARCHAR2,
    p_dob         IN DATE,
    p_balance     IN NUMBER
) AS
BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE);

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Customer ' || p_customer_id || ' added.');
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        INSERT INTO ErrorLog (Context, ErrorMessage)
        VALUES ('AddNewCustomer', 'Customer ID ' || p_customer_id || ' already exists');
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('ERROR: Customer ' || p_customer_id
                             || ' already exists. Insert prevented.');
    WHEN OTHERS THEN
        INSERT INTO ErrorLog (Context, ErrorMessage)
        VALUES ('AddNewCustomer', SQLERRM);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
END;
/

-- -------------------------------------------------------------
-- Quick tests
-- -------------------------------------------------------------
BEGIN
    SafeTransferFunds(1, 2, 5000);         -- should fail: insufficient funds
    UpdateSalary(999, 10);                 -- should fail: no such employee
    AddNewCustomer(1, 'Dup John', SYSDATE, 100); -- should fail: duplicate id
    AddNewCustomer(10, 'New Guy', TO_DATE('2000-01-01','YYYY-MM-DD'), 500); -- ok
END;
/
