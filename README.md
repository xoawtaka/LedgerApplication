XoLedging: Command Line Accounting Ledger
Project Overview
XoLedging is a robust Command Line Interface (CLI) application developed in Java for tracking financial transactions. Designed as a foundational Capstone project, it demonstrates proficiency in Java fundamentals, Object-Oriented Programming (OOP) principles, file I/O operations, data parsing, and menu-driven application logic.
The application allows users to record deposits and payments, and then view, filter, and summarize their transaction history. All data is managed persistently through a flat-file database format, specifically a local transactions.csv.
Features and Functionality
1. Data Persistence & Integrity
File I/O: All transactions are read from and saved to transactions.csv.
Unique ID Generation: Each transaction is assigned a unique, deterministic ID using the IDGenerator class (see code highlight below).
2. Home Screen
The primary application menu provides core entry points:


Option
Function
D
Add Deposit: Records a positive transaction amount.
P
Make Payment (Debit): Records a negative transaction amount.
L
Ledger: Navigates to the Ledger Screen for detailed viewing and reports.
X
Exit Application: Safely terminates the program.

3. Ledger Screen
The ledger is the viewing hub, with all entries displayed newest-first.
Option
Function
A
View All Transactions: Displays every entry in the ledger.
D
View Deposits: Filters and displays only positive transactions.
P
View Payments: Filters and displays only negative transactions (debits).
R
Reports: Provides a net balance summary (placeholder for future detailed reports).
H
Return to Home: Navigates back to the main menu.

<img width="2364" height="1430" alt="image" src="https://github.com/user-attachments/assets/403e62f6-4052-429e-b4b7-7f2f84b0a645" />


⚙️ Application Structure
The project is structured with a clear separation of concerns across multiple classes:
File Name
Responsibility
LedgerApplication.java
Main application entry point (main() method) and Home Screen logic.
LedgerScreen.java
Manages the Ledger view, menu navigation, and initial data filtering (All, Deposits, Payments).
TransactionFileManager.java
Handles all interaction with the transactions.csv file, including reading, parsing, and appending data.
Transactions.java
The Data Model class representing a single ledger entry (Date, Time, Description, Vendor, Amount, ID).
IDGenerator.java
A utility class responsible for creating a unique, short ID for each transaction using cryptographic hashing (MD5).

Code Highlight: Cryptographic ID Generation
A key enhancement to this project is the IDGenerator class, which creates a short, unique identifier for each transaction. This demonstrates an understanding of utility classes and basic Java security features.
This method takes the transaction data string, applies the MD5 hashing algorithm to generate a byte array, converts the byte array to a hexadecimal string, and then truncates it to a short, unique prefix.
File: IDGenerator.java
public class IDGenerator {
    public static String createID(String data) throws NoSuchAlgorithmException {

        // 1. Get MD5 MessageDigest instance
        MessageDigest md = MessageDigest.getInstance("MD5");

        // 2. Hash the data into computer readable bytes
        byte[] hashBytes = md.digest((data.getBytes()));

        // 3. Convert hash bytes to a hexadecimal string
        StringBuilder hexString = getStringBuilder(hashBytes);

        // 4. Return the first 6 characters as the unique ID
        return hexString.substring(0, 6).toUpperCase();
    }
    // ... (rest of the helper methods)
}
<img width="2428" height="1446" alt="image" src="https://github.com/user-attachments/assets/c600ea60-fd8b-4aee-8ea7-823ea120dd36" />



Getting Started
Prerequisites
Java Development Kit (JDK) 17 or newer
A suitable Integrated Development Environment (IDE) such as IntelliJ IDEA or Eclipse.
Execution
Clone the Repository:
git clone [your_repo_url_here]
cd XoLedging


Compile: Compile the Java files using your IDE or the command line:
javac -cp . com/pluralsight/*.java


Run: Execute the main application class:
java com.pluralsight.LedgerApplication


The application will start in your console, displaying the welcome screen and the main menu options.
