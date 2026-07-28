package com.digitalnurture;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Exercise 4: Arrange-Act-Assert (AAA) pattern with test fixtures and
 * setup/teardown methods.
 *
 * - @BeforeClass / @AfterClass run once for the whole class (must be static).
 * - @Before / @After run before/after EVERY test method, giving each test a
 *   fresh fixture (here, a new BankAccount) so tests stay independent.
 */
public class BankAccountTest {

    private BankAccount account;   // the test fixture

    @BeforeClass
    public static void initAll() {
        System.out.println("@BeforeClass: starting BankAccountTest suite");
    }

    @Before
    public void setUp() {
        // Arrange (shared): every test starts with a fresh account of 100.0
        account = new BankAccount(100.0);
    }

    @After
    public void tearDown() {
        // Teardown: release the fixture after each test
        account = null;
    }

    @AfterClass
    public static void tearDownAll() {
        System.out.println("@AfterClass: finished BankAccountTest suite");
    }

    @Test
    public void testDeposit() {
        // Arrange — done in setUp(); Act
        account.deposit(50.0);
        // Assert
        assertEquals(150.0, account.getBalance(), 0.0001);
    }

    @Test
    public void testWithdraw() {
        // Act
        account.withdraw(40.0);
        // Assert
        assertEquals(60.0, account.getBalance(), 0.0001);
    }

    @Test
    public void testWithdrawTooMuchThrows() {
        // Act + Assert: withdrawing more than the balance is rejected
        assertThrows(IllegalStateException.class, () -> account.withdraw(1000.0));
        // Balance must be unchanged
        assertEquals(100.0, account.getBalance(), 0.0001);
    }

    @Test
    public void testNegativeDepositThrows() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-10.0));
    }
}
