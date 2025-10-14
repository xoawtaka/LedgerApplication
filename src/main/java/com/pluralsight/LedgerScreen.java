package com.pluralsight;

import java.util.*;

public class LedgerScreen {
    private final Scanner input;

    public LedgerScreen(TransactionFileManager fileManager) {
        this.input = new Scanner(System.in);
        TransactionSorter sorter = new TransactionSorter();
    }

    public void displayLedgerScreen() {
        boolean running = true;

        while (running) {
            System.out.print("""
                    \\n ---- Ledger Statements ----
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
                    showAll();
                    break;
                case "D":
                    showDeposits();
                    break;
                case "P":
                    showPayments();
                    break;
                case "R":
                    showReports();
                    break;
                case "H":
                    running = false;
                    break;
                default:
                    System.out.println("Sorry, invalid option. Try again.");
            }
        }
    }

    private void showAll() {
        System.out.print("""
                \\n ---- Ledger Statements ----
       \s""");
        ArrayList<Transactions> transactions = ;
        // I want to create these methods by calling upon the list via data in transactions

        // this will allow me to filter with collections.sort
    }

    private void showDeposits() {
        System.out.print("""
                \\n ---- Ledger Deposits ----
        """);
        // I want to create these methods by calling upon the list via data in transactions

        // this will allow me to filter with collections.sort
        // I want to sort via +money in transactions
    }

    private void showPayments() {
        System.out.print("""
                \\n ---- Ledger Payments ----
        """);
        // I want to create these methods by calling upon the list via data in transactions

        // this will allow me to filter with collections.sort
        // I want to sort via - or negative money payments in transactions

    }

    private void showReports() {
        System.out.print("""
                \\n ---- Ledger Reports ----
        """);
    }
}