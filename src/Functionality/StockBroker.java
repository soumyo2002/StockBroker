package Functionality;
import java.util.*;

public class StockBroker {
	private HashMap<String,User> userDB;
	private HashMap<String,Stock> stockList;
	Scanner sc;

	public StockBroker() {
		userDB = new HashMap<>();
		stockList = new HashMap<>();
		sc = new Scanner(System.in);
	}
	
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

	public void addStock() {
		Authentication auth = new Authentication();
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
			
			Stock ob = new Stock();
			ob.setBrokerageCharge(brokerageCharge);
			ob.setExchange(exchange);
			ob.setPrice(price);
			ob.setQuantity(quantity);
			ob.setSymbol(symbol);
			stockList.put(symbol,ob);
			System.out.println("Stock added successfully!");
		}else {
			System.out.println("Wrong password!");
		}
		
	}
	
	
	public void removeStock() {
	    Authentication auth = new Authentication();
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

	
	
	public boolean register_users(String username, String password, String email, String pan, String phone,
			String adhaar, String IFSC, String dob, String MICR, String category, long acc_no) {
		Authentication ob = new Authentication();
		 if(!userDB.isEmpty()) {
			if(userDB.containsKey("username")) {
				System.out.println("Username already exists!");
				return false;
			}	
			
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
			if(stockList.containsKey(symbol)) {
					Stock stock = stockList.get(symbol);
					result = order.placeOrder(acc_no,stock, isBuyorder,quantity,timeInforce);
				}else {
					System.out.println("Stock is not listed!");
				}
			}else {
			Stock stock = stockList.get(symbol);
			        if (stocksTocheck.containsKey(stock)) {
			        	result = order.placeOrder(acc_no,stock, isBuyorder,quantity,timeInforce);
			        }else{
			            System.out.println("You don't have the stocks to sell.");
			        }
			    }
		return result;
			}

	public boolean setnewpasswrd(String username,String newPass) {
		// TODO Auto-generated method stub
		Authentication auth = new Authentication();
		boolean result = auth.passwordAuth(newPass);
		//Implementation details
		//Encrypt the new password and then store it
		if(result) {
		User user = this.userDB.get(username);
		user.setPassword(newPass);
		}
		return result;
	}

	public double getBrokerage(String stockName) {
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
		// TODO Auto-generated method stub
		AccountStatement accstmt = new AccountStatement();
		HashMap<String,AccountStatement> stmt = new HashMap<>();
		stmt = accstmt.getStatement(name,istaxStmt);
		return stmt;
	}

	public Stock getStockInfo(String name) {
		Stock stockDetails = stockList.get(name);
		return stockDetails;
	}

	public User getUserDetails(String username) {
		User userDetails = userDB.get(username);
		return userDetails;
	}

	public boolean Login() {
		System.out.println("Enter username");
		String username = sc.next();
		System.out.println("Enter password");
		String password = sc.next();
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
