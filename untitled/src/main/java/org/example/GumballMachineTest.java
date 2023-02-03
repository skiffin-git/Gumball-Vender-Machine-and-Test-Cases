package org.example;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
    public class GumballMachineTest {
    private GumballMachine gumballMachine;

    @Before
    public void setUp() {
        gumballMachine = new GumballMachine();
    }

    @Test
    public void testInsertCoin_validInput() {
        gumballMachine.insertCoin(5);
        assertEquals(5, gumballMachine.getBalance());
        gumballMachine.insertCoin(10);
        assertEquals(15, gumballMachine.getBalance());
        gumballMachine.insertCoin(25);
        assertEquals(40, gumballMachine.getBalance());
    }

    @Test
    public void testInsertCoin_invalidInput() {
        gumballMachine.insertCoin(3);
        assertEquals(0, gumballMachine.getBalance());
    }

    @Test
    public void testDispenseGumball_red() {
        gumballMachine.setBalance(5);
        gumballMachine.dispenseGumball("r");
        assertEquals(0, gumballMachine.getBalance());
    }

    @Test
    public void testDispenseGumball_yellow() {
        gumballMachine.setBalance(10);
        gumballMachine.dispenseGumball("y");
        assertEquals(0, gumballMachine.getBalance());
    }

    @Test
    public void testDispenseGumball_notEnoughCoins() {
        gumballMachine.setBalance(0);
        gumballMachine.dispenseGumball("r");
        assertEquals(0, gumballMachine.getBalance());
    }

    @Test
    public void testDispenseGumball_invalidColor() {
        gumballMachine.setBalance(10);
        gumballMachine.dispenseGumball("green");
        assertEquals(10, gumballMachine.getBalance());
    }

    @Test
    public void testReturnCoins_withCoins() {
        gumballMachine.setBalance(20);
        gumballMachine.returnCoins("g");
        assertEquals(0, gumballMachine.getBalance());
    }

    @Test
    public void testReturnCoins_withoutCoins() {
        gumballMachine.returnCoins("g");
        assertEquals(0, gumballMachine.getBalance());
    }
}

