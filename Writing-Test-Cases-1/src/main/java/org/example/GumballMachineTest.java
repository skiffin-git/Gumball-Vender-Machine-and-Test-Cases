package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GumballMachineTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    // testInsertCoin tests the insertCoin method of GumballMachine
    // It tests 4 cases of coin inputs: 5, 10, 25, and 3 cents
    // It tests if the balance of the gumball machine is updated correctly for each input
    // It tests if the output message is as expected for each input
    @Test
    public void testInsertCoin() {
        GumballMachine gumballMachine = new GumballMachine();
        gumballMachine.insertCoin(5);
        assertEquals(5, gumballMachine.getBalance());
        assertEquals("New balance is: 5 cents\r\n", outContent.toString());
        outContent.reset();
        gumballMachine.insertCoin(10);
        assertEquals(15, gumballMachine.getBalance());
        assertEquals("New balance is: 15 cents\r\n", outContent.toString());
        outContent.reset();
        gumballMachine.insertCoin(25);
        assertEquals(40, gumballMachine.getBalance());
        assertEquals("New balance is: 40 cents\r\n", outContent.toString());
        outContent.reset();
        gumballMachine.insertCoin(3);
        assertEquals(40, gumballMachine.getBalance());
        assertEquals("Invalid input, returned 3 on the push of the dispenses lever\r\n", outContent.toString());
        outContent.reset();
    }

    // testDispenseGumball tests the dispenseGumball method of GumballMachine
    // It tests 4 cases of gumball dispensing: Red, Yellow, Yellow again, and Red when no coins inserted
    // It tests if the balance of the gumball machine is updated correctly for each input
    // It tests if the output message is as expected for each input
    @Test
    public void testDispenseGumball() {
        GumballMachine gumballMachine = new GumballMachine();
        // quick set balance to 25
        gumballMachine.setBalance(20);

        gumballMachine.dispenseGumball("r");
        assertEquals(15, gumballMachine.getBalance());
        assertEquals("Dispensing Red gumball, remaining coins: 15\r\n", outContent.toString());
        outContent.reset();

        gumballMachine.dispenseGumball("Y");
        assertEquals(5, gumballMachine.getBalance());
        assertEquals("Dispensing Yellow gumball, remaining coins: 5\r\n", outContent.toString());
        outContent.reset();

        gumballMachine.dispenseGumball("y");
        assertEquals(5, gumballMachine.getBalance());
        assertEquals("Not enough coins inserted. Please insert 5 cents more\r\n", outContent.toString());
        outContent.reset();

        // quick set balance to 25
        gumballMachine.setBalance(0);
        assertEquals(0, gumballMachine.getBalance());
        gumballMachine.dispenseGumball("r");
        assertEquals("Not enough coins inserted. Please insert 5 cents more\r\n", outContent.toString());
        outContent.reset();
    }

    // testReturnCoins tests the returnCoins method of GumballMachine
    // It tests 2 cases: when there are coins inserted and when there are no coins inserted
    // It tests if the balance of the gumball machine is updated correctly for each input
    // It tests if the output message is as expected for each input
    @Test
    public void testReturnCoins() {
        GumballMachine gumballMachine = new GumballMachine();
        gumballMachine.setBalance(25);

        gumballMachine.returnCoins("g");
        assertEquals(0, gumballMachine.getBalance());
        assertEquals("Returning 25 cents in change\r\n", outContent.toString());
        outContent.reset();

        gumballMachine.returnCoins("G");
        assertEquals(0, gumballMachine.getBalance());
        assertEquals("No coins left to return\r\n", outContent.toString());
        outContent.reset();
    }
    /**
     * Test case for the showOptions method of the GumballMachine class.
     * This test case checks if the showOptions method outputs the correct options message
     * when the input "o" is given. The expected outcome is that the output of the showOptions method
     * is equal to the expected options message and the balance of the gumball machine is 0 cents.
     */
    @Test
    public void testShowOptions() {
        GumballMachine gumballMachine = new GumballMachine();
        gumballMachine.showOptions("o");
        assertEquals("--- OPTIONS ---\n" +
                "Insert 5, 10 or 25 cents, type: 5, 10 or 25\n" +
                "To dispense Red or Yellow gumball, type: R or r and Y or y accordingly\n" +
                "To Get remaining coins, type: G or g\n" +
                "To show the Options again, type O or o\n" +
                "To Quit the program, type: Q or q\n" +
                "Current balance is: 0 cents\r\n", outContent.toString());
        outContent.reset();
    }
    /*
     * Test case for main function of the gumball machine program
     *
     * The test case sets the input stream
     * to simulate user incorrect inputs and
     * sets the output stream to capture the program's output.
     *
     * The test inputs are:u,1000,-25,hello,q
     * The expected output is:
     * Invalid input
     * Invalid input, returned 1000 on the push of the dispenses lever
     * Invalid input
     * Invalid input
     * Invalid input
     *
     * The test case asserts that the output matches the expected output.
     */
    @Test
    public void testMain() {
        InputStream testInput = new ByteArrayInputStream("u\n1000\n-25\n \nhello\nq\n".getBytes());
        System.setIn(testInput);
        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOutput));

        main.main(null);

        assertEquals("--- WELCOME TO GUMBALL MACHINE ---\r\n" +
                "--- OPTIONS ---\n" +
                "Insert 5, 10 or 25 cents, type: 5, 10 or 25\n" +
                "To dispense Red or Yellow gumball, type: R or r and Y or y accordingly\n" +
                "To Get remaining coins, type: G or g\n" +
                "To show the Options again, type O or o\n" +
                "To Quit the program, type: Q or q\n" +
                "Current balance is: 0 cents\r\n" +
                "Invalid input\r\n" +
                "Invalid input, returned 1000 on the push of the dispenses lever\r\n" +
                "Invalid input\r\n" +
                "Invalid input\r\n" +
                "Invalid input\r\n" +
                "--- EXITING FROM GUMBALL MACHINE ---\r\n", testOutput.toString());

        System.setIn(System.in);
        System.setOut(System.out);
    }
}


