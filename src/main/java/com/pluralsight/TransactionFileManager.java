package com.pluralsight;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TransactionFileManager {

    // reading and saving data to transaction.csv
    public static void addTransaction(Transactions transaction) throws IOException { // method to append a new transaction to the file
        String fileName = "transactions.csv"; // defining the target filename
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true)); // 'true' in filewriter means append mode
        writer.write(transaction.toString()); // writing the transaction string (which is formatted for csv)
        writer.close(); // always closing the writer to ensure data is flushed and saved to the file
    }

    public static ArrayList<Transactions> displayTransactions() throws FileNotFoundException { // method returns an arraylist of all transaction objects
        ArrayList<Transactions> allTransactions = new ArrayList<>(); // initializing the list to store transaction objects

        try {
            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv")); // setting up reader to read the file line by line
            String line;
            // split lines by |
            while ((line = reader.readLine()) != null) { // loop continues as long as there are lines to read
                Transactions newTransaction = getTransactions(line); // calling helper method to parse the line into an object
                allTransactions.add(newTransaction); // adding the parsed transaction object to the list
            }
            reader.close(); // closing the reader after reading all lines

        } catch (IOException | NoSuchAlgorithmException e) { // catching multiple possible exceptions (ioexception, nosuchalgorithm)
            throw new RuntimeException(e); // re-throwing a runtime exception for simpler error handling
        }
        return allTransactions; // returning the list of all transactions
    }

    private static Transactions getTransactions(String line) throws NoSuchAlgorithmException { // helper method processes a single line of csv data
        try {
            String[] transactionData = line.split("\\|"); // splitting the line using the pipe '|' delimiter (needs to be escaped with \\)
            LocalDate date = LocalDate.parse(transactionData[0]); // parsing the first element to localdate
            LocalTime time = LocalTime.parse(transactionData[1]); // parsing the second element to localtime
            String description = transactionData[2]; // description is the third element
            String vendor = transactionData[3]; // vendor is the fourth element
            double amount = Double.parseDouble(transactionData[4]); // parsing the fifth element to double

            return new Transactions(date, time, description, vendor, amount); // returning the newly created transaction object
        }
        catch (Exception e) { // catching general exceptions during parsing
            throw new RuntimeException(e); // wrapping and re-throwing the exception
        }

    }
}
