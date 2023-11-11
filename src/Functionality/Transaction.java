package Functionality;

// Class representing a financial transaction
public class Transaction {
    String date; // Date of the transaction
    double amount; // Transaction amount
    long userAccount; // User's account number
    long exchangeAccount; // Exchange's account number

    // Default constructor to initialize date, amount, userAccount, and exchangeAccount
    public Transaction() {
        date = "";
        amount = 0.0;
        userAccount = 0L;
        exchangeAccount = 345708110L; // Default exchange account number
    }

    // Method to initiate a fund transfer and create a corresponding account statement
    public boolean initiateTransfer(long acc_no, Stock stock, boolean isBuyOrder, int quantity, String timeInforce,
            double finalprice) {
        // After the transaction is successful, create a new AccountStatement
//        AccountStatement stmt = new AccountStatement();
//        stmt.createStatement(acc_no, stock, isBuyOrder, quantity, timeInforce, finalprice);
        return true; // Return true to indicate a successful fund transfer initiation
    }
}
