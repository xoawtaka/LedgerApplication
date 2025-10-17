package com.pluralsight;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Transactions {
    private String id; // field for the unique transaction id
    private LocalDate date; // field for the transaction date
    private LocalTime time; // field for the transaction time
    private String description; // field for the transaction description
    private String vendor; // field for the vendor name
    private double amount; // field for the transaction amount

    // creating constructor for getters and setters — Transaction class w/ fields inside the parameters for what the class will utilize
    public Transactions(LocalDate date, LocalTime time, String description, String vendor, double amount) throws NoSuchAlgorithmException {
        this.date = date; // setting the LocalDate instance variable
        this.time = time; // setting the LocalTime instance variable
        this.description = description; // setting the description instance variable
        this.vendor = vendor; // setting the vendor instance variable
        this.amount = amount; // setting the amount instance variable

        // create string and variable name  for data ^ tp convert into a unique id for transactions
        String transactionData = date + "|" + time + "|" + description + "|" + vendor + "|" + amount; // concatenating transaction data into a single string for hashing
        this.id = IDGenerator.createID(transactionData); // generating a unique id using the static id generator method


    }

    public String getId() { // getter method for the unique transaction id
        return id;
    }

    public void setId(String id) { // setter method for the unique transaction id
        this.id = id;
    }

    public LocalDate getDate() { // getter method for the transaction date
        return date;
    }

    public void setDate(LocalDate date) { // setter method for the transaction date
        this.date = date;
    }

    public LocalTime getTime() { // getter method for the transaction time
        return time;
    }

    public void setTime(LocalTime time) { // setter method for the transaction time
        this.time = time;
    }

    public String getDescription() { // getter method for the transaction description
        return description;
    }

    public void setDescription(String description) { // setter method for the transaction description
        this.description = description;
    }

    public String getVendor() { // getter method for the vendor
        return vendor;
    }

    public void setVendor(String vendor) { // setter method for the vendor
        this.vendor = vendor;
    }

    public double getAmount() { // getter method for the amount
        return amount;
    }

    public void setAmount(double amount) { // setter method for the amount
        this.amount = amount;
    }


    @Override
    public String toString() { // overriding the default toString method for csv formatting
        return "\n" + date + "|" + time + "|" + description + "|" + vendor + "|" + amount + "| -> Order ID: " + id; // format matches the required csv layout
        /*"\n---------------------------------------------------------------------\n"*/ // original commented line preserved
    }
}
