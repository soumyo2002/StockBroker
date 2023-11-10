package Functionality;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;

//Class representing an account statement
public class AccountStatement {
	Stock stock; // Instance of the Stock class
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
		Date date = new Date();
		AccountStatement stmt =  new AccountStatement();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//Checking if user is already trading in the same stock or not
		if((!txnDetails.isEmpty()) && (txnDetails.containsKey(symbol))) {
			stmt.date = dateFormat.format(date);
			stmt.presentValue = stock.getPrice();
			stmt.acc_no = acc_no;
			//Buy order
			if(isBuyOrder) {
			double currentPrice = stmt.avgPrice;
			//If user has a long position
			if(stmt.position == "long") {
			//average price by considering all the prices at which the user traded this stock.
			double avgprice = (currentPrice+finalprice)/(stmt.quantity+quantity);
			stmt.avgPrice = avgprice;
			//Eg: User holds 10 stocks and places order to buy 5 more stocks,then total holdings = 15 stocks
			stmt.quantity += quantity;
			}
			//If user has a short position
			else 
			{
				int quantity_held = stmt.quantity;
				//Checking if user has placed order to buy more or stocks than it holds
				//Eg: User sold 10 stocks and places order to buy 5 stocks,then total holdings = 5 stocks
				if(quantity<=quantity_held) {
				stmt.avgPrice -=(quantity*finalprice);
				stmt.quantity -= quantity;
				//If user buys all stocks he had sold then remove it from his holdings
				//Eg: User sold 10 stocks and places order to buy 10 stocks,then total holdings = 0 stocks
				if(stmt.quantity == 0) {
					txnDetails.remove(symbol);
				}
				}else {
					//Eg: User sold 10 stocks and places order to buy 20 stocks,then total holdings = 10 stocks
					//but his position changes from short to long
					stmt.quantity = quantity - quantity_held;
					stmt.avgPrice =(stmt.quantity*finalprice);
					stmt.position = "long";
				}
			}
			}
			else {
				//Sell Order
				if(!isBuyOrder) {
					double currentPrice = stmt.avgPrice;
					//If user already has a short position taken
					if(stmt.position == "short") {
					//average price by considering all the prices at which the user traded this stock.
					double avgprice = (currentPrice+finalprice)/(stmt.quantity+quantity);
					stmt.avgPrice = avgprice;
					//Eg: User sold 10 stocks and places order to sell 5 more stocks,then total holdings = 15 stocks
					stmt.quantity += quantity;
					}
					//If user has a long position taken
					else 
					{
						int quantity_held = stmt.quantity;
						//Checking if user has placed order to sell stocks more or less than he has
						//Eg: User has 10 stocks and places order to sell 5 stocks,then total holdings = 10 stocks
						if(quantity<=quantity_held) {
						stmt.avgPrice -=(quantity*finalprice);
						stmt.quantity -= quantity;
						//Eg: User has 10 stocks and places order to sell 10 stocks,then total holdings = 0 stocks
						if(stmt.quantity == 0) {
							//remove the stock from his holdings
							txnDetails.remove(symbol);
						}
						}else {
							//Eg: User has 10 stocks and places order to sell 20 stocks,then total holdings = 10 stocks
							//and position changes to short
							stmt.quantity = quantity - quantity_held;
							stmt.avgPrice =(stmt.quantity*finalprice);
							stmt.position = "short";
						}
					}
					}
				
				
				}
			//Calculating profit and loss so far
			stmt.pL = stmt.avgPrice - stmt.presentValue;
			}
			//If user is already not trading in that stock then we create a fresh holdings for him
			else {
			AccountStatement newStmt = new AccountStatement();
			newStmt.acc_no = acc_no;
			newStmt.avgPrice = finalprice;
	        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			newStmt.date = dateformat.format(date);
			newStmt.pL = 0.0;
			if(isBuyOrder)
				newStmt.position = "long";
			else
				newStmt.position = "short";
			newStmt.presentValue = avgPrice;
			newStmt.quantity = quantity;
			newStmt.stock = stock;
			
			txnDetails.put(symbol, newStmt);
		}
		
	}
	// Method to get the account statements (optionally, tax statements) based on specified criteria
	public HashMap<String, AccountStatement> getStatement(String name, boolean istaxStmt) {
		if(!istaxStmt) {
		return txnDetails;
		}else {
			HashMap<String,AccountStatement>taxStmt = new HashMap<>();
			for (String key : txnDetails.keySet()) {
				AccountStatement statement = txnDetails.get(key);
				String transactionDate = statement.date;
				if (isInTaxYear(transactionDate)) { // Assuming you have a method to check if the date is within the specified range
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
