package Functionality;

import java.util.*;

public class StockBroker {
	private HashMap<User, Integer> userDB;
	private HashMap<Stock, Integer> stockList;

	public HashMap<User, Integer> getUserDB() {
		return userDB;
	}

	public void setUserDB(HashMap<User, Integer> userDB) {
		this.userDB = userDB;
	}

	public HashMap<Stock, Integer> getStockList() {
		return stockList;
	}

	public void setStockList(HashMap<Stock, Integer> stockList) {
		this.stockList = stockList;
	}

	public boolean register_users(String username, String password, String email, String pan, String phone,
			String adhaar, String IFSC, String dob, String MICR, String category, long acc_no) {
		Authentication ob = new Authentication();

		for (User user : userDB.keySet()) {
			if (user.getUsername().equals(username)) {
				System.out.println("Username already exists!");
				return false;
			} else if (user.getEmail().equals(pan)) {
				System.out.println("Sorry!only one trading account can be opened with one PAN with one broker");
				return false;
			} else if (user.getPan().equals(email)) {
				System.out.println("Email ID already registered!Please log in");
				return false;
			} else if (user.getPhone().equals(phone)) {
				System.out.println("Phone number already registered!Please log in");
				return false;
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
		user.setData(username, password, email, pan, phone, adhaar, IFSC, dob, MICR, category, acc_no);
		userDB.put(user, 1);

		return true;
	}

	public boolean unregister_users(User user) {

		if (userDB.containsKey(user)) {
			HashMap<Stock, Integer> stockList = user.getStocksOwned();
			if (stockList.isEmpty()) {
				userDB.remove(user);
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

	public int placeOrder(User user,Stock stock, boolean isBuyorder, int quantity,String timeInforce) {
		
		int result = -1;
		Order order = new Order();
		if(isBuyorder) {
			if(stockList.containsKey(stock)) {
					result = order.placeOrder(stock, isBuyorder,quantity,timeInforce);
				}else {
					System.out.println("Stock is not listed!");
				}
			return result;
			}else {
			HashMap<Stock,Integer> stocksTocheck = user.getStocksOwned();
			        if (stocksTocheck.containsKey(stock)) {
			        	result = order.placeOrder(stock, isBuyorder,quantity,timeInforce);
			        }else{
			            System.out.println("You don't have the stocks to sell.");
			        }
			    }
			}

	public boolean setnewpasswrd(String newPass) {
		// TODO Auto-generated method stub
		Authentication auth = new Authentication();
		boolean result = auth.passwordAuth(newPass)
		return result;
	}

	public double getBrokerage(Stock stock) {
		// TODO Auto-generated method stub
		stockList.get
		return 0;
	}

	public List<AccountStatement> getTransaction(String name, boolean istaxStmt) {
		// TODO Auto-generated method stub
		return null;
	}

	public Stock getStockInfo(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
