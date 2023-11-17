package Functionality;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;

//Class representing an account statement
public class AccountStatement {
	Stock stock; // Instance of the Stock class
	String symbol; //symbol of the stock
    double avgPrice; // Average price of the stock
    double presentValue; // Present value of the stock
    double pL; // Profit or Loss
    String position; // Position (long or short)
    String date; // Date of the statement
    int quantity; // Quantity of stocks
    long acc_no; // Account number
    HashMap<String, AccountStatement> txnDetails; // Transaction details (symbol -> statement)
    Scanner sc = new Scanner(System.in); // Scanner for user input

    //defult constructor
	public AccountStatement() {
		stock = new Stock();
		symbol = "";
		avgPrice = 0.0;
		quantity = 0;
		pL = 0.0;
		position = "";
		date = "";
		acc_no = 0;
		txnDetails = new HashMap<>();
	}
	
	// Method to create a new account statement based on transaction details
	public void createStatement(long acc_no, Stock stock, boolean isBuyOrder, int quantity, String timeInforce, double finalprice) {
		String symbol = stock.getSymbol();
		//Creating Date object
		Date date = new Date();
		AccountStatement stmt;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//Checking if user is already trading in the same stock or not
		if((!txnDetails.isEmpty()) && (txnDetails.containsKey(symbol))) {
			//extracting stock symbol
			stmt = txnDetails.get(symbol);
			stmt.date = dateFormat.format(date);
			stmt.presentValue = stock.getPrice();
			stmt.acc_no = acc_no;
			//Buy order
			if(isBuyOrder) {
			double totalCost = stmt.avgPrice * stmt.quantity + finalprice;
			//Eg: User holds 10 stocks and places order to buy 5 more stocks,then total holdings = 15 stocks
		    stmt.quantity += quantity;
		  //average price by considering all the prices at which the user traded this stock.
		    stmt.avgPrice = totalCost / stmt.quantity;
			}else {
				double totalPrice = stmt.avgPrice * stmt.quantity;
			    totalPrice -= finalprice; // Deduct the value of the sold stocks
			    stmt.quantity -= quantity;//updating quantity
			    if (stmt.quantity == 0) {
			    	//As the stocks are sold,data is removed
			        txnDetails.remove(symbol);
			    } else {
			        stmt.avgPrice = totalPrice / stmt.quantity;
			    }
			    stmt.pL = stmt.avgPrice - stmt.presentValue;
			}
		}
			//If user is already not trading in that stock then we create a fresh holdings for him
			else {
			//Creating object
			AccountStatement newStmt = new AccountStatement();
			newStmt.symbol = stock.getSymbol();
			newStmt.acc_no = acc_no;
			newStmt.avgPrice = (finalprice/quantity);
	        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			newStmt.date = dateformat.format(date);
			newStmt.pL = 0.0;
			//Setting position
			if(isBuyOrder)
				newStmt.position = "long";
			else
				newStmt.position = "short";
			newStmt.presentValue = stock.getPrice();
			newStmt.quantity = quantity;
			newStmt.stock = stock;
			
			//Inserting details in Db
			txnDetails.put(symbol, newStmt);
		}
		
	}
	// Method to get the account statements (optionally, tax statements) based on specified criteria
	public HashMap<String, AccountStatement> getStatement(String name, boolean istaxStmt) {
		if(!istaxStmt) {
		return txnDetails;
		}else {
			HashMap<String,AccountStatement>taxStmt = new HashMap<>();
			//Iterating over all the transactions
			for (String key : txnDetails.keySet()) {
				AccountStatement statement = txnDetails.get(key);
				String transactionDate = statement.date;
				if (isInTaxYear(transactionDate)) { 
					taxStmt.put(key, statement);
                }
            }
            return taxStmt;
			}
		}
	
	// Method to check if a given date is within the tax year
	public boolean isInTaxYear(String date) {
		System.out.println("Enter tax year");
		int year = sc.nextInt();
		 String[] parts = date.split("-");
	        int transactionYear = Integer.parseInt(parts[0]);
	        int month = Integer.parseInt(parts[1]);
	        return (year == transactionYear && month >= 4) || (year + 1 == transactionYear && month <= 3);
    }

}
