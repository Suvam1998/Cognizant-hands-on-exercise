-- =============================================================
-- Exercise 7: Packages
-- =============================================================
SET SERVEROUTPUT ON;

-- -------------------------------------------------------------
-- Scenario 1: CustomerManagement
-- -------------------------------------------------------------
CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddCustomer(p_id NUMBER, p_name VARCHAR2, p_dob DATE, p_balance NUMBER);
    PROCEDURE UpdateCustomer(p_id NUMBER, p_name VARCHAR2, p_balance NUMBER);
    FUNCTION  GetCustomerBalance(p_id NUMBER) RETURN NUMBER;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

    PROCEDURE AddCustomer(p_id NUMBER, p_name VARCHAR2, p_dob DATE, p_balance NUMBER) AS
    BEGIN
        INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
        VALUES (p_id, p_name, p_dob, p_balance, SYSDATE);
        COMMIT;
    END AddCustomer;

    PROCEDURE UpdateCustomer(p_id NUMBER, p_name VARCHAR2, p_balance NUMBER) AS
    BEGIN
        UPDATE Customers
        SET    Name = p_name, Balance = p_balance, LastModified = SYSDATE
        WHERE  CustomerID = p_id;
        COMMIT;
    END UpdateCustomer;

    FUNCTION GetCustomerBalance(p_id NUMBER) RETURN NUMBER AS
        v_balance Customers.Balance%TYPE;
    BEGIN
        SELECT Balance INTO v_balance FROM Customers WHERE CustomerID = p_id;
        RETURN v_balance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN NULL;
    END GetCustomerBalance;

END CustomerManagement;
/

-- -------------------------------------------------------------
-- Scenario 2: EmployeeManagement
-- -------------------------------------------------------------
CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee(p_id NUMBER, p_name VARCHAR2, p_position VARCHAR2,
                           p_salary NUMBER, p_department VARCHAR2, p_hire_date DATE);
    PROCEDURE UpdateEmployee(p_id NUMBER, p_position VARCHAR2, p_salary NUMBER,
                             p_department VARCHAR2);
    FUNCTION  CalculateAnnualSalary(p_id NUMBER) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

    PROCEDURE HireEmployee(p_id NUMBER, p_name VARCHAR2, p_position VARCHAR2,
                           p_salary NUMBER, p_department VARCHAR2, p_hire_date DATE) AS
    BEGIN
        INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
        VALUES (p_id, p_name, p_position, p_salary, p_department, p_hire_date);
        COMMIT;
    END HireEmployee;

    PROCEDURE UpdateEmployee(p_id NUMBER, p_position VARCHAR2, p_salary NUMBER,
                             p_department VARCHAR2) AS
    BEGIN
        UPDATE Employees
        SET    Position = p_position, Salary = p_salary, Department = p_department
        WHERE  EmployeeID = p_id;
        COMMIT;
    END UpdateEmployee;

    -- Monthly salary is stored; annual salary = 12 * monthly.
    FUNCTION CalculateAnnualSalary(p_id NUMBER) RETURN NUMBER AS
        v_salary Employees.Salary%TYPE;
    BEGIN
        SELECT Salary INTO v_salary FROM Employees WHERE EmployeeID = p_id;
        RETURN v_salary * 12;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN NULL;
    END CalculateAnnualSalary;

END EmployeeManagement;
/

-- -------------------------------------------------------------
-- Scenario 3: AccountOperations
-- -------------------------------------------------------------
CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE OpenAccount(p_account_id NUMBER, p_customer_id NUMBER,
                          p_type VARCHAR2, p_opening_balance NUMBER);
    PROCEDURE CloseAccount(p_account_id NUMBER);
    FUNCTION  GetTotalBalance(p_customer_id NUMBER) RETURN NUMBER;
END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS

    PROCEDURE OpenAccount(p_account_id NUMBER, p_customer_id NUMBER,
                          p_type VARCHAR2, p_opening_balance NUMBER) AS
    BEGIN
        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
        VALUES (p_account_id, p_customer_id, p_type, p_opening_balance, SYSDATE);
        COMMIT;
    END OpenAccount;

    PROCEDURE CloseAccount(p_account_id NUMBER) AS
    BEGIN
        DELETE FROM Accounts WHERE AccountID = p_account_id;
        COMMIT;
    END CloseAccount;

    FUNCTION GetTotalBalance(p_customer_id NUMBER) RETURN NUMBER AS
        v_total NUMBER;
    BEGIN
        SELECT NVL(SUM(Balance), 0) INTO v_total
        FROM   Accounts
        WHERE  CustomerID = p_customer_id;
        RETURN v_total;
    END GetTotalBalance;

END AccountOperations;
/

-- -------------------------------------------------------------
-- Quick tests
-- -------------------------------------------------------------
DECLARE
    v_bal NUMBER;
    v_annual NUMBER;
    v_total NUMBER;
BEGIN
    CustomerManagement.AddCustomer(20, 'Pkg Customer',
        TO_DATE('1995-02-02','YYYY-MM-DD'), 3000);
    v_bal := CustomerManagement.GetCustomerBalance(20);
    DBMS_OUTPUT.PUT_LINE('Customer 20 balance: ' || v_bal);

    EmployeeManagement.HireEmployee(20, 'Pkg Emp', 'Analyst', 55000, 'Finance', SYSDATE);
    v_annual := EmployeeManagement.CalculateAnnualSalary(20);
    DBMS_OUTPUT.PUT_LINE('Employee 20 annual salary: ' || v_annual);

    AccountOperations.OpenAccount(30, 20, 'Savings', 3000);
    v_total := AccountOperations.GetTotalBalance(20);
    DBMS_OUTPUT.PUT_LINE('Customer 20 total balance across accounts: ' || v_total);
END;
/
