package Functionality;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;


public class AccountStatement {
	Stock stock;
	double avgPrice;
	double presentValue;
	double pL;
	String position;
	String date;
	int quantity;
	long acc_no;
	HashMap<String,AccountStatement> txnDetails;
	Scanner sc = new Scanner(System.in);
	
	private static AccountStatement instance;

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
	
	public void createStatement(long acc_no, Stock stock, boolean isBuyOrder, int quantity, String timeInforce, double finalprice) {
		String symbol = stock.getSymbol();
		Date date = new Date();
		AccountStatement stmt =  new AccountStatement();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if((!txnDetails.isEmpty()) && (txnDetails.containsKey(symbol))) {
			stmt.date = dateFormat.format(date);
			stmt.presentValue = stock.getPrice();
			stmt.acc_no = acc_no;
			if(isBuyOrder) {
			double currentPrice = stmt.avgPrice;
			if(stmt.position == "long") {
			double avgprice = (currentPrice+finalprice)/(stmt.quantity+quantity);
			stmt.avgPrice = avgprice;
			stmt.quantity += quantity;
			}else {
				int quantity_held = stmt.quantity;
				if(quantity<=quantity_held) {
				stmt.avgPrice -=(quantity*finalprice);
				stmt.quantity -= quantity;
				if(stmt.quantity == 0) {
					txnDetails.remove(symbol);
				}
				}else {
					stmt.avgPrice =(quantity*finalprice);
					stmt.quantity = quantity;
					stmt.position = "long";
				}
			}
			}
			else {
				if(!isBuyOrder) {
					double currentPrice = stmt.avgPrice;
					if(stmt.position == "short") {
					double avgprice = (currentPrice+finalprice)/(stmt.quantity+quantity);
					stmt.avgPrice = avgprice;
					stmt.quantity += quantity;
					}else {
						int quantity_held = stmt.quantity;
						if(quantity<=quantity_held) {
						stmt.avgPrice -=(quantity*finalprice);
						stmt.quantity -= quantity;
						if(stmt.quantity == 0) {
							txnDetails.remove(symbol);
						}
						}else {
							stmt.avgPrice =(quantity*finalprice);
							stmt.quantity = quantity;
							stmt.position = "short";
						}
					}
					}
				
				
				}
			stmt.pL = stmt.avgPrice - stmt.presentValue;
			}else {
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
	
	public boolean isInTaxYear(String date) {
		System.out.println("Enter tax year");
		int year = sc.nextInt();
		 String[] parts = date.split("-");
	        int transactionYear = Integer.parseInt(parts[0]);
	        int month = Integer.parseInt(parts[1]);
	        return (year == transactionYear && month >= 4) || (year + 1 == transactionYear && month <= 3);
    }

}
