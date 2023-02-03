package org.example;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        // create new instances
        Scanner scanner = new Scanner(System.in);
        GumballMachine gumballMachine = new GumballMachine();
        boolean done = false; // for while loop

        // welcome message
        System.out.println("--- WELCOME TO GUMBA MACHINE ---");

        // main loop
        while (!done) {

            // show options
            gumballMachine.showOptions();

            // read line
            String input = scanner.nextLine();

            // if done
            if(input.equalsIgnoreCase("q")){
                System.out.println("--- EXITING FROM GUMBA MACHINE ---");
                break;
            }

            // check for return coins
            if(!gumballMachine.returnCoins(input)){
                // check for dispense color based gum
                if(!gumballMachine.dispenseGumball(input)){
                    // try insert coin
                    try {
                        // transform string to int and add coins to bank
                        gumballMachine.insertCoin(Integer.parseInt(input));

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input");
                    }
                };
            };
        }
    }
}
