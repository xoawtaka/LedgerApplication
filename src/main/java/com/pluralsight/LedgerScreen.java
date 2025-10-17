package com.pluralsight;

import java.io.FileNotFoundException;
import java.util.*;

public class LedgerScreen {
    private final Scanner input; // private field for the scanner instance

    public LedgerScreen(TransactionFileManager fileManager) { // constructor requiring a file manager instance (dependency injection)
        this.input = new Scanner(System.in); // initializing the scanner for user input
    }

    public void displayLedgerScreen() throws FileNotFoundException { // method to display and manage the ledger screen menu
        boolean running = true; // boolean flag to control the main loop

        while (running) { // loop continues until the user chooses to return to home
            System.out.print("""
                    \n ---- Ledger Statements ----
                    A) View All Transactions
                    D) View Deposits
                    P) View Payments
                    R) Reports
                    H) Return to Home
                    
                    Enter a choice from the ledger menu:\s
                    """); // printing the ledger menu options using a text block
            String choice = input.nextLine().trim().toUpperCase(); // reading input, trimming whitespace, and converting to uppercase

            switch (choice) { // starting the menu switch statement
                case "A":
                    displayAll(); // calling method to show all transactions grouped by vendor
                    break;
                case "D":
                    displayDeposits(); // calling method to show only deposits
                    break;
                case "P":
                    displayPayments(); // calling method to show only payments (debits)
                    break;
                case "R":
                    displayReports(); // calling method to show the overall balance report
                    break;
                case "H":
                    running = false; // setting running to false exits the loop and returns to the previous screen (Home)
                    break;
                default:
                    System.out.println("Sorry, invalid option. Try again."); // handling invalid user input
            }
        }
    }

    private void displayAll() throws FileNotFoundException { // method to display all transactions, grouped and totaled by vendor
        System.out.print("""
                         \n ---- Ledger Statements ----
                         """); // printing the header

        //create array list with class transactions to create an instance that equals the transactions static method
        ArrayList<Transactions> allTransactions = TransactionFileManager.displayTransactions(); // fetching all transactions from the file

        //sort by using transactions.compare() method with the parameters of transactions with/through :: vendor, date and time
        allTransactions.sort( // sorting the list using a chain of comparators
                // utilizing method reference syntax rather than lambdas (t -> t.getBlahBlahMethod)
                Comparator.comparing(Transactions::getVendor, String.CASE_INSENSITIVE_ORDER) // primary sort: alphabetically by vendor (case-insensitive)
                        .thenComparing(Transactions::getDate).reversed() // secondary sort: by date, in reverse order (newest first)
                        .thenComparing(Transactions::getTime).reversed()); // tertiary sort: by time, in reverse order

        String transactionVendor = ""; // variable to track the current vendor being processed (for grouping)
        double overallBalance = 0; // variable to accumulate the final net balance of the account
        double vendorTotal = 0; // variable to accumulate the running total for the current vendor group

        for (Transactions transaction : allTransactions) { // iterating through the sorted list of transactions
            if (!transaction.getVendor().equalsIgnoreCase(transactionVendor)) { // check if the vendor has changed
                if (!transactionVendor.isEmpty()) { // check if this is not the first vendor in the list
                    System.out.printf("\n< %s transaction total: %.2f >\n", transactionVendor, vendorTotal); // printing the total for the completed vendor group
                    System.out.println("___________________________________________________\n"); // separator line
                    overallBalance = overallBalance + vendorTotal; // adding the completed vendor total to the overall balance
                    vendorTotal = 0; // resetting the vendor total for the new vendor group
                }
                transactionVendor = transaction.getVendor(); // updating the current vendor to the new vendor's name
                System.out.println("<><><><><<><><><><><><><><><><><><><><><><><><><>"); // prominent separator
                System.out.println("Vendor: " + transactionVendor); // displaying the new vendor's name
                System.out.println("___________________________________________________"); // separator line
            }
            System.out.println(transaction); // printing the current transaction details
            vendorTotal = vendorTotal + transaction.getAmount(); // accumulating the transaction amount into the current vendor's total
        }

        System.out.println("___________________________________________________"); // final separator for the last vendor group
        System.out.printf("\n< %s transaction total: %.2f >\n", transactionVendor, vendorTotal); // printing the total for the last vendor group
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><>\n"); // prominent separator
        overallBalance = overallBalance + vendorTotal; // adding the last vendor total to the overall balance

        System.out.printf("\nOverall Account Balance: %.2f\n", overallBalance); // displaying the final calculated overall net balance
        /*System.out.print(allTransactions);*/ // commented-out debug or placeholder code
        // I want to create these methods by calling upon the list via data in transactions
        // this will allow me to filter with collections.sort // internal note: indicates intent to use sorting/filtering
    }

    private void displayDeposits() throws FileNotFoundException { // method to filter and display only deposits
        System.out.print("""
                        \n ---- Ledger Deposits ----
                """); // printing the deposits header
        ArrayList<Transactions> depositTransactions = TransactionFileManager.displayTransactions(); // fetching all transactions
        depositTransactions.sort( // sorting the list
                Comparator.comparing(Transactions::getDate) // sort primarily by date (oldest first due to no reversed())
                        .thenComparing(Transactions::getTime).reversed()); // sort secondarily by time, in reverse order

        double depositTotal = 0; // variable to hold the sum of all deposit amounts
        for (Transactions transaction : depositTransactions) { // iterating through the transactions
            if (transaction.getAmount() > 0) { // checking if the transaction is a deposit (amount > 0)
                depositTotal += transaction.getAmount(); // accumulating the deposit amount
                System.out.println(transaction); // printing the deposit transaction
            }
        }

        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><>"); // separator
        System.out.printf("\n< Deposit total: %.2f >\n", depositTotal); // displaying the total deposit amount
        System.out.print("<><><><><><><><><><><><><><><><><><><><><><><><><>\n"); // separator

        // display Math.abs (amount) — positive // internal note: indicating intent to show positive values
    }

    private void displayPayments() throws FileNotFoundException { // method to filter and display only payments (debits)
        System.out.print("""
                        \n ---- Ledger Deposits ----
                """); // printing the payments header (note: the header text is currently set to "Ledger Deposits")
        ArrayList<Transactions> installments = TransactionFileManager.displayTransactions(); // fetching all transactions
        installments.sort( // sorting the list
                Comparator.comparing(Transactions::getDate) // sort primarily by date (oldest first)
                        .thenComparing(Transactions::getTime).reversed()); // sort secondarily by time, in reverse order

        double installmentTotal = 0; // variable to hold the sum of all payment amounts (will be negative)
        for (Transactions transaction : installments) { // iterating through the transactions
            if (transaction.getAmount() < 0) { // checking if the transaction is a payment (amount < 0)
                installmentTotal += transaction.getAmount(); // accumulating the payment amount
                System.out.println(transaction); // printing the payment transaction
            }
        }

        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><>"); // separator
        System.out.printf("\n< Transaction total: %.2f >\n", installmentTotal); // displaying the total payment amount
        System.out.print("<><><><><><><><><><><><><><><><><><><><><><><><><>\n"); // separator
        // display -Math.abs (amount) — negative transactions // internal note: confirming values are negative

        // I want to sort via - or negative money payments in transactions // internal note: confirming filtering goal
    }

    private void displayReports() throws FileNotFoundException { // method to display the overall net balance report
        System.out.print("""
                        \n ---- Ledger Reports ----
                """); // printing the reports header
        ArrayList<Transactions> reports = TransactionFileManager.displayTransactions(); // fetching all transactions

        double reportTotal = getReportTotal(reports);

        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><>"); // separator
        System.out.printf("\n< Your net balance is %.2f! \nGet your money up, not your funny up!!! >\n", reportTotal); // displaying the net balance with a motivational message
        System.out.print("<><><><><><><><><><><><><><><><><><><><><><><><><>\n"); // separator

    }

    private static double getReportTotal(ArrayList<Transactions> reports) {
        double installmentTotal = 0; // variable to sum all payments (negative)
        double depositTotal = 0; // variable to sum all deposits (positive)
        for (Transactions transaction : reports) { // iterating through the transactions
            if (transaction.getAmount() > 0) { // checking for deposits
                depositTotal += transaction.getAmount(); // adding to deposit total
            }
            else if (transaction.getAmount() < 0) { // checking for payments
                installmentTotal += transaction.getAmount(); // adding to payment total (negative value)
            }
        }

        // calculating the final net balance (deposits + negative payments)
        return depositTotal + installmentTotal;
    }
}
