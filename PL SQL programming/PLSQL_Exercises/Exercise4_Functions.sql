-- =============================================================
-- Exercise 4: Functions
-- =============================================================
SET SERVEROUTPUT ON;

-- -------------------------------------------------------------
-- Scenario 1: CalculateAge(dob) -> age in whole years.
-- -------------------------------------------------------------
CREATE OR REPLACE FUNCTION CalculateAge (
    p_dob IN DATE
) RETURN NUMBER AS
BEGIN
    RETURN FLOOR(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
END;
/

-- -------------------------------------------------------------
-- Scenario 2: CalculateMonthlyInstallment(amount, annualRate%, years)
-- Standard EMI:  EMI = P*r*(1+r)^n / ((1+r)^n - 1)
--   r = monthly rate (annualRate/12/100),  n = months
-- Handles the zero-interest case.
-- -------------------------------------------------------------
CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    p_loan_amount   IN NUMBER,
    p_annual_rate   IN NUMBER,   -- percent, e.g. 7 for 7%
    p_years         IN NUMBER
) RETURN NUMBER AS
    v_r    NUMBER;
    v_n    NUMBER;
    v_emi  NUMBER;
    v_pow  NUMBER;
BEGIN
    v_n := p_years * 12;
    IF p_annual_rate = 0 THEN
        RETURN ROUND(p_loan_amount / v_n, 2);
    END IF;

    v_r   := p_annual_rate / 12 / 100;
    v_pow := POWER(1 + v_r, v_n);
    v_emi := (p_loan_amount * v_r * v_pow) / (v_pow - 1);
    RETURN ROUND(v_emi, 2);
END;
/

-- -------------------------------------------------------------
-- Scenario 3: HasSufficientBalance(accId, amount) -> BOOLEAN.
-- (BOOLEAN is usable in PL/SQL; not directly in a SQL SELECT.)
-- -------------------------------------------------------------
CREATE OR REPLACE FUNCTION HasSufficientBalance (
    p_account_id IN NUMBER,
    p_amount     IN NUMBER
) RETURN BOOLEAN AS
    v_balance Accounts.Balance%TYPE;
BEGIN
    SELECT Balance INTO v_balance
    FROM   Accounts
    WHERE  AccountID = p_account_id;

    RETURN v_balance >= p_amount;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
END;
/

-- -------------------------------------------------------------
-- Quick tests
-- -------------------------------------------------------------
DECLARE
    v_age NUMBER;
    v_emi NUMBER;
BEGIN
    SELECT CalculateAge(DOB) INTO v_age FROM Customers WHERE CustomerID = 1;
    DBMS_OUTPUT.PUT_LINE('Age of customer 1: ' || v_age);

    v_emi := CalculateMonthlyInstallment(400000, 7, 18);
    DBMS_OUTPUT.PUT_LINE('EMI for 400000 @7% over 18y: ' || v_emi);

    IF HasSufficientBalance(1, 500) THEN
        DBMS_OUTPUT.PUT_LINE('Account 1 has sufficient balance for 500.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Account 1 does NOT have sufficient balance for 500.');
    END IF;
END;
/
