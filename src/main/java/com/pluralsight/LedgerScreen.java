package com.pluralsight;

import java.io.FileNotFoundException;
import java.util.*;

public class LedgerScreen {
    private final Scanner input;

    public LedgerScreen(TransactionFileManager fileManager) {
        this.input = new Scanner(System.in);
        TransactionSorter sorter = new TransactionSorter();
    }

    public void displayLedgerScreen() throws FileNotFoundException {
        boolean running = true;

        while (running) {
            System.out.print("""
                    \n ---- Ledger Statements ----
                    A) View All Transactions
                    D) View Deposits
                    P) View Payments
                    R) Reports
                    H) Return to Home
                    
                    Enter a choice from the ledger menu:\s
                    """);
            String choice = input.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    displayAll();
                    break;
                case "D":
                    displayDeposits();
                    break;
                case "P":
                    displayPayments();
                    break;
                case "R":
                    displayReports();
                    break;
                case "H":
                    running = false;
                    break;
                default:
                    System.out.println("Sorry, invalid option. Try again.");
            }
        }
    }

    private void displayAll() throws FileNotFoundException {
        System.out.print("""
                         \n ---- Ledger Statements ----
                \s""");

        //create array list with class transactions to create an instance that equals the transactions static method
        ArrayList<Transactions> allTransactions = TransactionFileManager.displayTransactions();

        //sort by using transactions.compare() method with the parameters of transactions with/through :: vendor, date and time
        allTransactions.sort(Comparator.comparing(Transactions::getVendor)
                .thenComparing(Transactions::getDate).reversed()
                .thenComparing(Transactions::getTime).reversed());

        System.out.println(allTransactions);



        // I want to create these methods by calling upon the list via data in transactions

        // this will allow me to filter with collections.sort
    }

    private void displayDeposits() {
        System.out.print("""
                \\n ---- Ledger Deposits ----
        """);


    }

    private void displayPayments() {
        System.out.print("""
                \\n ---- Ledger Payments ----
        """);

        // I want to sort via - or negative money payments in transactions

    }

    private void displayReports() {
        System.out.print("""
                \\n ---- Ledger Reports ----
        """);
    }
}