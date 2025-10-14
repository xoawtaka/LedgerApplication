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
                    System.out.println("Invalid option, try again.");
            }
        }
    }

    private void showAll() {
    }

    private void showDeposits() {
    }

    private void showPayments() {
    }

    private void showReports() {
    }
}