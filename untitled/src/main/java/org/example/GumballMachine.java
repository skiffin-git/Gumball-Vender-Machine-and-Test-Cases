
package org.example;

import java.util.Scanner;

/**
 * The `GumballMachine` class implements a simple gumball machine that dispenses red or yellow gumballs.
 * Users can insert coins of value 5, 10, or 25 cents and then dispense a gumball of a chosen color.
 * The user can also return their remaining coins if they wish to quit using the machine.
 */
public class GumballMachine {

    private int balance = 0; // in cents
    private int redGumballPrice = 5; // in cents
    private int yellowGumballPrice = 10; // in cents

    /**
     * This method accepts an integer input that represents
     * the coin inserted in the gumball machine.
     * @param coin The value of the inserted coin in cents.
     */
    public void insertCoin(int coin) {
        if (coin == 5 || coin == 10 || coin == 25) {
            balance += coin;
            System.out.println("New balance is: " + balance + " cents");
        } else {
            System.out.println("Invalid input, returned " + coin + " on the push of the dispenses lever");
        }
    }

    /**
     * This method dispenses a gumball of a specified color (red or yellow),
     * provided the user has inserted enough coins to pay for the gumball.
     * @param input A string representing the color of the gumball
     * to be dispensed (either "r" or "y").
     */
    public void dispenseGumball(String input) {
        if (input.equalsIgnoreCase("r")) {
            if (balance >= redGumballPrice) {
                balance -= redGumballPrice;
                System.out.println("Dispensing Red gumball, remaining coins: " + balance);
            } else {
                System.out.println("Not enough coins inserted. Please insert " + (redGumballPrice - balance) + " cents more");
            }
        } else if (input.equalsIgnoreCase("r") || input.equalsIgnoreCase("y")){
            if (balance >= yellowGumballPrice) {
                balance -= yellowGumballPrice;
                System.out.println("Dispensing Yellow gumball");
            } else {
                System.out.println("Not enough coins inserted. Please insert " + (yellowGumballPrice - balance) + " cents more");
            }
        } else {
            System.out.println("Invalid gumball color");
        }
    }
    /**
     * This method returns the remaining coins in the user's balance if they are present.
     * @param input A string representing the user's intention
     * to return their remaining coins (either "g" or "G").
     */
    public void returnCoins(String input) {
        if (input.equalsIgnoreCase("g")) {
            if (balance > 0) {
                System.out.println("Returning " + balance + " cents in change");
            } else {
                System.out.println("No coins left to return");
            }
            balance = 0;
        }
    }
    /**
     * Displays the options available to the user on the gumball machine.
     */
    public void showOptions(){
        System.out.println("--- OPTIONS ---\n" +
                "Insert 5, 10 or 25 cents (type: 5, 10 or 25)\n" +
                "To dispense Red or Yellow gumball (type: R or r and Y or y accordingly)\n" +
                "To Get remaining coins (type: G or g)\n" +
                "To Quit (type: Q or q)\n" +
                "Current balance is: " + balance + " cents");
    }

    // balance getters and setters are fore testing
    /**
     * Getter method to retrieve the current balance in the gumball machine.
     * @return The current balance of the gumball machine in cents.
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Setter method to set the balance in the gumball machine.
     * @param balance The balance to be set in cents.
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }
}