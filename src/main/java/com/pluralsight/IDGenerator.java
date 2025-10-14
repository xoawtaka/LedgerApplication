package com.pluralsight;

import java.security.*;
import java.util.ArrayList;

public class IDGenerator {
    public static String createID(String data) throws NoSuchAlgorithmException {

        // ask for algorithm to hex the data from Java class security
        // use hash from MD5 (algorithm)
        MessageDigest md = MessageDigest.getInstance("MD5");

        // hash the data into computer readable bytes
        byte[] hashBytes = md.digest((data.getBytes()));

        // I want to store the data in an elastic array to make it interchangeable for every hex string
        StringBuilder hexString = getStringBuilder(hashBytes);

        return hexString.substring(0, 6).toUpperCase();
    }

    private static StringBuilder getStringBuilder(byte[] hashBytes) {
        ArrayList<String> hexArray = new ArrayList<>();

        // loop through hash to create hexadecimal number
        // create a consistent format by making it so that
        // if a hash/hex number = to a single digit number, then pad the hex with a 0
        for (byte hashByte : hashBytes) {
            String hex = Integer.toHexString(0xFF & hashByte);
            if (hex.length() == 1) {
                hex = "0" + hex;
            }
            hexArray.add(hex);

        }

        // put all the hex values together in array
        StringBuilder hexString = new StringBuilder();
        for (String hexValue : hexArray) {
            hexString.append(hexValue);

        }
        return hexString;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String transactionData = "";
        String id = createID(transactionData);
        System.out.println(id);
    }

    // run message to create hash in algorithm


    // create hash byte to digest or convert

    // take byte values to generate hex values

    //build string using hex/hash data

    //coonvert hex/hash to base36

    //return data

}


// what I've learned:
// Java has it own encryption class for deciphering, security and secure id generation

// take message data and then convert it by invoking Javas encryption data hash value generator using MD5
// MD5 allows you to make a hash utilizing any data type
// then using that value, convert into byte (8) and take 3 for hex value (3 byte = 6 hex characters)
// using this data create a sting for the data
