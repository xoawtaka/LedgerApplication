package com.pluralsight;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TransactionFileManager {

    // reading and saving data to transaction.csv
    public static void addTransaction(Transactions transaction) throws IOException {
        String fileName = "transactions.csv";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(transaction.toString());
        writer.close();
    }

    public static ArrayList<Transactions> displayTransactions() throws FileNotFoundException {
        ArrayList<Transactions> allTransactions = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
            String line;
            // split lines by |
            while ((line = reader.readLine()) != null) {
                Transactions newTransaction = getTransactions(line);
                allTransactions.add(newTransaction);
            }

        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return allTransactions;
    }

    private static Transactions getTransactions(String line) throws NoSuchAlgorithmException {
        try {
            String[] transactionData = line.split("\\|");
            LocalDate date = LocalDate.parse(transactionData[0]);
            LocalTime time = LocalTime.parse(transactionData[1]);
            String description = transactionData[2];
            String vendor = transactionData[3];
            double amount = Double.parseDouble(transactionData[4]);

            return new Transactions(date, time, description, vendor, amount);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
