package com.pluralsight;

import java.security.*;
import java.util.ArrayList;

public class IDGenerator {
    public static String createID(String data) throws NoSuchAlgorithmException { // method creates a short, unique id from input data

        // ask for algorithm to hex the data from Java class security
        // use hash from MD5 (algorithm)
        MessageDigest md = MessageDigest.getInstance("MD5"); // requesting the md5 hashing algorithm instance

        // hash the data into computer readable bytes
        byte[] hashBytes = md.digest((data.getBytes()));  // digesting the string data into a byte array hash (md5 produces a 128-bit hash)

        // I want to store the data in an elastic array to make it interchangeable for every hex string
        StringBuilder hexString = getStringBuilder(hashBytes); // calling private helper method to convert bytes to hex string

        return hexString.substring(0, 6).toUpperCase(); // returning the first 6 characters as the short unique ID, converted to uppercase
    }

    private static StringBuilder getStringBuilder(byte[] hashBytes) { // private helper method to build the hexadecimal string
        ArrayList<String> hexArray = new ArrayList<>(); // initializing an arraylist to temporarily hold hex values

        // loop through hash to create hexadecimal number
        // create a consistent format by making it so that
        // if a hash/hex number = to a single digit number, then pad the hex with a 0
        for (byte hashByte : hashBytes) { // looping through each byte of the hash
            String hex = Integer.toHexString(0xFF & hashByte); // converting byte to hexadecimal, masking with 0xff ensures a positive integer
            if (hex.length() == 1) { // checking if the hex value is a single digit
                hex = "0" + hex; // padding with a leading zero if the hex value is a single digit
            }
            hexArray.add(hex); // adding the two-character hex string to the arraylist

        }

        // put all the hex values together in array
        StringBuilder hexString = new StringBuilder(); // using StringBuilder for efficient string concatenation
        for (String hexValue : hexArray) { // looping through the hex values
            hexString.append(hexValue); // appending each two-character hex value to the StringBuilder

        }
        return hexString; // returning the complete hexadecimal string
    }

    public static void main(String[] args) throws NoSuchAlgorithmException { // main method included for testing the id generator
        String transactionData = ""; // initializing test data string
        String id = createID(transactionData); // generating an id from the test data
        System.out.println(id); // printing the generated id for verification
    }

    // run message to create hash in algorithm


    // create hash byte to digest or convert

    // take byte values to generate hex values

    //build string using hex/hash data

    //coonvert hex/hash to base36

    //return data


    // what I've learned:
    // Java has it own encryption class for deciphering, security and secure id generation

    // take message data and then convert it by invoking Javas encryption data hash value generator using MD5
    // MD5 allows you to make a hash utilizing any data type
    // then using that value, convert into byte (8) and take 3 for hex value (3 byte = 6 hex characters)
    // using this data create a sting for the data

}
