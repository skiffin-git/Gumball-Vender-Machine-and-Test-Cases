package org.example;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}


