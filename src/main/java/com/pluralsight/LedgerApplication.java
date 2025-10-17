package com.pluralsight;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class LedgerApplication {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        System.out.println("""
                 <<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>
                        Welcome to XoLedging\n
                
                 The ledge where your money problems\s
                 jumps to solutions!
                 <<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>
                \s""");

        boolean running = true; // variable to control the main application loop

        while (running) { // loop keeps the application running until the user chooses to exit
            System.out.println("Which of the following options would you like to choose?");
            System.out.println(
                    // take out all entries because ledger screen has it
                    """
                            D) Add Deposit
                            P) Make Payment (Debit)
                            L) Ledger
                            X) Exit Application"""
            );

            System.out.print("\nPlease select your choice: ");
            Scanner input = new Scanner(System.in);
            String choice = input.nextLine().trim().toUpperCase(); // reading user input and formatting it to uppercase


            // use switch statement
            switch (choice) {
                case "D":
                    transactionStatement(input, true); // calling method for deposit; 'true' means it is a deposit
                    break;
                case "P":
                    transactionStatement(input, false); // calling method for payment; 'false' means it is a payment
                    break;
                case "L":
                    LedgerScreen ledgerScreen = new LedgerScreen(new TransactionFileManager());
                    ledgerScreen.displayLedgerScreen();
                    break;
            }
            if (choice.equals("X")) { // checking for user confirmation to exit
                System.out.println("Are you sure you would like to exit the XoLedging App? (Y/N)");
                String confirm = input.nextLine().trim().toUpperCase();

                if (confirm.equals("Y")) {

                    System.out.println("Goodbye!");
                    running = false;
                } else {
                    System.out.println("Loading...");
                }
                // create deposit and payment method — utilize -Math.abs
            }
        }
    }
    // method takes care of both deposits and payments by creating and isDeposit boolean flag
    private static void transactionStatement(Scanner input, boolean isDeposit) throws IOException, NoSuchAlgorithmException {

        System.out.println("Enter a description for the transaction: ");
        String description = input.nextLine();

        // drop down menu for vendors
        // print vendor list
        System.out.println("\nSelect a Vendor:");
        String[] vendors = {"Amazon", "Walmart", "Target", "Apple", "Custom Vendor"}; // array of predefined vendors
        for (int i = 0; i < vendors.length; i++) { // looping to print vendor options
            System.out.println((i + 1) + ") " + vendors[i]); // listing vendor options with index starting from 1
        }

        System.out.print("Enter vendor number: ");
        int vendorOption = Integer.parseInt(input.nextLine()); // reading vendor choice as an integer

        String vendor;
        if (vendorOption >= 1 && vendorOption <= vendors.length - 1) { // if vendor option is within list or predefined
            vendor = vendors[vendorOption - 1]; // selecting the vendor name from the array
        } else if (vendorOption == vendors.length) { // checking if the choice is 'Custom Vendor'
            System.out.print("Enter your vendor's name: ");
            vendor = input.nextLine(); // allowing user to input a custom vendor name
        } else {
            System.out.println("Invalid; defaulting to 'Unidentifiable Vendor'."); // handling invalid choice by setting a default vendor
            vendor = "Unidentifiable Vendor."; // setting the default vendor name
        }

        System.out.println("Enter decimal price of transaction: ");
        double amount = Double.parseDouble(input.nextLine().trim()); // parsing the amount input to a double


        if (!isDeposit) amount = -Math.abs(amount); // payments are negative by converting to negative absolute value

        Transactions transaction = new Transactions(
                LocalDate.now(),
                LocalTime.now(),
                description,
                vendor,
                amount
        );

        TransactionFileManager.addTransaction(transaction); // saving the new transaction to the csv file
    }
}


// this application needs to display given options for the user
/*D) Add Deposit - prompt user for the deposit information and
save it to the csv file
§ P) Make Payment (Debit) - prompt user for the debit
information and save it to the csv file
§ L) Ledger - display the ledger screen
§ X) Exit - exit the application
 */

