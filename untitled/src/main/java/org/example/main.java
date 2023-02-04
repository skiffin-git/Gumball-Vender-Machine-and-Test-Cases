package org.example;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        // create a new instance of Scanner to read user inputs
        Scanner scanner = new Scanner(System.in);

        // create a new instance of GumballMachine
        GumballMachine gumballMachine = new GumballMachine();

        // flag to control the main loop
        boolean done = false;

        // Display a welcome message
        System.out.println("--- WELCOME TO GUMBALL MACHINE ---");
        // show available options to the user
        gumballMachine.showOptions("o");

        // main loop to provide gumball dispensing options
        while (!done) {

            // read user input
            String input = scanner.nextLine();

            // check if the user wants to exit the program
            if(input.equalsIgnoreCase("q")){
                System.out.println("--- EXITING FROM GUMBALL MACHINE ---");
                break;
            }

            // check if the user want to check the menu again
            if(!gumballMachine.showOptions(input)){
                // check if the user wants to return coins
                if(!gumballMachine.returnCoins(input)){

                    // check if the user wants to dispense a gumball of specific color
                    if(!gumballMachine.dispenseGumball(input)){

                        // try to insert coin
                        try {
                            // transform input string to int and add coins to the machine's bank
                            gumballMachine.insertCoin(Integer.parseInt(input));

                        } catch (NumberFormatException e) {
                            // If the input cannot be transformed to int, display an error message
                            // Also catches all invalid input that didn't pass other if statements
                            System.out.println("Invalid input");
                        }
                    };
                };
            }
        }
    }
}
