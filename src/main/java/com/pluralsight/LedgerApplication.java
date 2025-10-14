package com.pluralsight;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class LedgerApplication {
    private static String LedgerScreen;

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        System.out.println("""
                 ====================================
                        Welcome to XoLedging\n
                
                 The ledge where your money problems\s
                 jumps to solutions!
                 ====================================
                \s""");

        boolean running = true;

        while (running) {
            System.out.println("Which of the following options would you like to choose?");
            System.out.println(
                    """
                            A) All Entries
                            D) Add Deposit
                            P) Make Payment (Debit)
                            L) Ledger
                            X) Exit Application"""
            );

            System.out.println("\nPlease select your choice: ");
            Scanner input = new Scanner(System.in);
            String choice = input.nextLine().trim().toUpperCase();


            // use switch statement
            switch (choice) {
                case "A":
                    ArrayList<Transactions> allTransactions = TransactionFileManager.displayTransactions();
                    System.out.println("Your transactions are:");
                    for (Transactions transaction : allTransactions) {
                        System.out.println(transaction.toString());
                    }
                    break;
                case "D":
                    transactionStatement(input, true);
                    break;
                case "P":
                    transactionStatement(input, false);
                    break;
                case "L":
                    System.out.println("Your transactions are: \n" + LedgerScreen);
                    break;
            }
            if (choice.equals("X")) {
                System.out.println("Are you sure you would like to exit the XoLedging App? (Y/N)");
                String confirm = input.nextLine().trim().toUpperCase();

                if (confirm.equals("Y")) {

                    System.out.println("Goodbye!");
                    running = false;
                } else {
                    System.out.println("You've returned to the home screen!");
                }
                // create deposit and payment method — utilize -Math.abs
            }
        }
    }

    private static void transactionStatement(Scanner input, boolean isDeposit) throws IOException, NoSuchAlgorithmException {

        System.out.println("Enter a description for the transaction: ");
        String description = input.nextLine();

        // drop down menu for vendors
        System.out.println("\nSelect a Vendor:");
        String[] vendors = {"Amazon", "Walmart", "Target", "Apple", "Custom Vendor"};
        for (int i = 0; i < vendors.length; i++) {
            System.out.println((i + 1) + ") " + vendors[i]);
        }

        System.out.print("Enter vendor number: ");
        int vendorChoice = Integer.parseInt(input.nextLine());

        String vendor;
        if (vendorChoice >= 1 && vendorChoice <= vendors.length - 1) {
            vendor = vendors[vendorChoice - 1];
        } else if (vendorChoice == vendors.length) {
            System.out.print("Enter your vendor's name: ");
            vendor = input.nextLine();
        } else {
            System.out.println("Invalid selection, defaulting to 'Unidentifiable Vendor'.");
            vendor = "Unidentifiable Vendor.";
        }

        System.out.println("Enter decimal price of transaction: ");
        double amount = Double.parseDouble(input.nextLine().trim());

        if (!isDeposit) amount = -Math.abs(amount); // payments are negative

        Transactions transaction = new Transactions(
                LocalDate.now(),
                LocalTime.now(),
                description,
                vendor,
                amount
        );

        TransactionFileManager.addTransaction(transaction);
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

