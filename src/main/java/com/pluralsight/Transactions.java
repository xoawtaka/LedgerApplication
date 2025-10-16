package com.pluralsight;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Transactions {
    private String id;
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    //creating constructor for getters and setters — Transaction class w/ fields inside the parameters for what the class will utilize
    public Transactions(LocalDate date, LocalTime time, String description, String vendor, double amount) throws NoSuchAlgorithmException {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;

        // create string and variable name  for data ^ tp convert into a unique id for transactions
        String transactionData = date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
        this.id = IDGenerator.createID(transactionData);


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "Order ID: " + id + "\n" + date + "|" + time + "|" + description + "|" + vendor + "|" + amount + "\n";
    }
}
