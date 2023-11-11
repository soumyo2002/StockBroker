package Functionality;
import java.util.*;

//Class representing a Stock Broker
public class StockBroker {
 private HashMap<String, User> userDB;    // Database of users
 private HashMap<String, Stock> stockList; // List of stocks
 Scanner sc;  // Scanner for user input
 AccountStatement stmt = new AccountStatement();//creating object of account statement class
//Default constructor to initialize userDB, stockList, and Scanner
	public StockBroker() {
		//HashMap to store data for users
		userDB = new HashMap<>();
		//HashMap to store data for stocks
		stockList = new HashMap<>();
		sc = new Scanner(System.in);
	}
	// Getter and setter methods
	public HashMap<String,User> getUserDB() {
		return userDB;
	}

	public void setUserDB(HashMap<String,User> userDB) {
		this.userDB = userDB;
	}

	public HashMap<String,Stock> getStockList() {
		return stockList;
	}

	public void setStockList(HashMap<String,Stock> stockList) {
		this.stockList = stockList;
	}
	// Method to add a new stock to the stock list
	public void addStock() {
		Authentication auth = new Authentication();
		//Checking if the user has special privilege to manipulate stockList
		if(auth.AdminAuth()) {
			String symbol,exchange;
			double price,brokerageCharge;
			int quantity;
			System.out.println("Enter Stock symbol");
			symbol=sc.next();
			if(stockList.containsKey(symbol)) {
				System.out.println("Stock is already Listed!");
				return;
			}
			System.out.println("Enter Stock exchange");
			exchange=sc.next();
			System.out.println("Enter Stock price");
			price=sc.nextDouble();
			System.out.println("Enter Stock brokerageCharge");
			brokerageCharge=sc.nextDouble();
			System.out.println("Enter Stock quantity");
			quantity=sc.nextInt();
			//Creating the Stock object
			Stock ob = new Stock();
			ob.setBrokerageCharge(brokerageCharge);
			ob.setExchange(exchange);
			ob.setPrice(price);
			ob.setQuantity(quantity);
			ob.setSymbol(symbol);
			//insert the data into stocklist map
			stockList.put(symbol,ob);
			System.out.println("Stock added successfully!");
		}else {
			System.out.println("Wrong password!");
		}
		
	}
	
	// Method to remove a stock from the stock list
	public void removeStock() {
	    Authentication auth = new Authentication();
	    //Checking if the user has special privilege to manipulate stockList
	    if(auth.AdminAuth()) {
	        String symbol;
	        System.out.println("Enter Stock symbol");
	        symbol = sc.next();
	        Stock removedStock = stockList.remove(symbol);
	        if (removedStock != null) {
	            System.out.println("Stock removed successfully!");
	        } else {
	            System.out.println("Stock not found!");
	        }
	        System.out.println(stockList);
	    } else {
	        System.out.println("Wrong password!");
	    }
	}

	
	// Method to register users
	public boolean register_users(String username, String password, String email, String pan, String phone,
			String adhaar, String IFSC, String dob, String MICR, String category, long acc_no) {
		Authentication ob = new Authentication();
		//Check if user is already registered
		 if(!userDB.isEmpty()) {
			if(userDB.containsKey("username")) {
				System.out.println("Username already exists!");
				return false;
			}	
			//Checking if user is using same PAN,email or phone number to create account
			for(User user : userDB.values()) {
			if (user.getPan().equals(pan)) {
				System.out.println("Sorry!only one trading account can be opened with one PAN with one broker");
				return false;
			} else if (user.getEmail().equals(email)) {
				System.out.println("Email ID already registered!Please log in");
				return false;
			} else if (user.getPhone().equals(phone)) {
				System.out.println("Phone number already registered!Please log in");
				return false;
				}
			}
		 }
		 //Authenticating rest details
		if (!ob.passwordAuth(password)) {
			return false;
		}

		if (!ob.otpAuth(email, true)) {
			System.out.println("Otp didn't match!");
			return false;
		}
		if (!ob.otpAuth(phone, false)) {
			System.out.println("Otp didn't match!");
			return false;
		}
		if (!ob.panAuth(pan)) {
			System.out.println("invalid PAN credetails!");
			return false;
		}
		if (!ob.adhaarAuth(adhaar)) {
			System.out.println("invalid Adhaar credetails");
			return false;
		}
		if (!ob.bankAuth(IFSC, MICR, acc_no)) {
			return false;
		}

		User user = new User();
		//While implementation I will encrypt the password
		user.setData(username, password, email, pan, phone, adhaar, IFSC, dob, MICR, category, acc_no);
		userDB.put(username, user);

		return true;
	}

	

	public boolean unregister_users(String username) {

		if (userDB.containsKey(username)) {
			User user = userDB.get(username);
			HashMap<Stock,Integer> stockList = user.getStocksOwned();
			//Checking if the user has some stocks held or sold
			if (stockList.isEmpty()) {
				userDB.remove(username);
				System.out.println("Account removed successfully!");
				return true;
			} else {
				System.out.println("Cannot delete account. Please sell off your stocks first.");
			}
		} else {
			System.out.println("User doesn't exist!");
		}
		return false;
	}

	public int placeOrder(String username,long acc_no,String symbol, boolean isBuyorder, int quantity,String timeInforce, String orderType, HashMap<Stock, Integer> stocksTocheck) {
		int result = -1;
		Order order;
		//Setting type of order
		if(orderType.equals("limit")) {
			order = new LimitOrder();
		}else if(orderType.equals("stoploss")) {
			order = new StopLossOrder();
		}else if(orderType.equals("stoplimit")) {
			order = new StopLimitOrder();
		}else {
			order = new Marketorder();
		}
		
		if(isBuyorder) {
			//Verify is the stock exists in the stockList map
			if(stockList.containsKey(symbol)) {
					Stock stock = stockList.get(symbol);
					//Place order
//					result = order.placeOrder(acc_no,stock, isBuyorder,quantity,timeInforce);
					
					
					result = order.placeOrder(acc_no,stock, isBuyorder,quantity,timeInforce);
					double finalprice = result*(stock.getPrice());
			        stmt.createStatement(acc_no, stock, isBuyorder, result, timeInforce, finalprice);
				}else {
					System.out.println("Stock is not listed!");
				}
			}
//		else {
//			Stock stock = stockList.get(symbol);
//			        if (stocksTocheck.containsKey(stock)) {
//			        	result = order.placeOrder(acc_no,stock, isBuyorder,quantity,timeInforce);
//			        }else{
//			            System.out.println("You don't have the stocks to sell.");
//			        }
//			    }
		return result;
			}

	public boolean setnewpasswrd(String username,String newPass) {
		Authentication auth = new Authentication();
		boolean result = auth.passwordAuth(newPass);
		//Implementation details
		//Encrypt the new password and then store it
		if(result) {
		User user = this.userDB.get(username);
		user.setPassword(newPass);
		System.out.println("Password changed successfully!");
		}
		return result;
	}

	public double getBrokerage(String stockName) {
		//Verify is the stock exists
		if (stockList.containsKey(stockName)) {
	        Stock stDetails = stockList.get(stockName);
	        double brokerageCharges = stDetails.getBrokerageCharge();
	        return brokerageCharges;
	    } else {
	        System.out.println("Stock not found in the stock list.");
	        return 0.0; 
	    }
	}

	public HashMap<String,AccountStatement> getTransaction(String name, boolean istaxStmt) {
		HashMap<String,AccountStatement> accstmt = new HashMap<>();
		//Calling function to get the account Statement
		accstmt = stmt.getStatement(name,istaxStmt);
		return accstmt;
	}

	public Stock getStockInfo(String name) {
		//Extracts data from stockList map
		Stock stockDetails = stockList.get(name);
		return stockDetails;
	}

	public User getUserDetails(String username) {
		//Extracts data from user map
		User userDetails = userDB.get(username);
		return userDetails;
	}

	public boolean Login() {
		System.out.println("Enter username");
		String username = sc.next();
		System.out.println("Enter password");
		String password = sc.next();
		//Checking if the username exists
		if(this.userDB.containsKey(username)) {
			User user = this.userDB.get(username);
			if(password.equals(user.getPassword()))
				return true;
			else
				System.out.println("Wrong password!");
		}else {
			System.out.println("Username doesn't exist,please register first.");
		}
		return false;
	}

}
