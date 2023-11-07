package Functionality;
import java.util.*;

	public class User {
		
	    private String username;
	    private String password;
	    private String admin_password = "admin";
	    private String phone;
	    private String pan;
	    private String email;
	    private String adhaar;
	    private String IFSC;
	    private String dob;
	    private String MICR;
	    private String category;
	    private long acc_no;
	    private HashMap<Stock,Integer> stocksOwned;
	    
	    Scanner sc = new Scanner(System.in);
	    StockBroker broker = new StockBroker();

		public User() {
			this.username = "";
			this.password = "";
			this.phone = "";
			this.pan = "";
			this.email = "";
			this.adhaar = "";
			IFSC = "";
			this.dob = "";
			MICR = "";
			this.category = "";
			this.acc_no = 0;
			this.stocksOwned = new HashMap<>();
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}



		public String getPassword() {
			return password;
		}



		public void setPassword(String password) {
			this.password = password;
		}



		public String getPhone() {
			return phone;
		}



		public void setPhone(String phone) {
			this.phone = phone;
		}



		public String getPan() {
			return pan;
		}



		public void setPan(String pan) {
			this.pan = pan;
		}



		public String getEmail() {
			return email;
		}



		public void setEmail(String email) {
			this.email = email;
		}



		public String getAdhaar() {
			return adhaar;
		}



		public void setAdhaar(String adhaar) {
			this.adhaar = adhaar;
		}



		public String getIFSC() {
			return IFSC;
		}



		public void setIFSC(String IFSC) {
			this.IFSC = IFSC;
		}



		public String getDob() {
			return dob;
		}



		public void setDob(String dob) {
			this.dob = dob;
		}



		public String getMICR() {
			return MICR;
		}



		public void setMICR(String mICR) {
			MICR = mICR;
		}



		public String getCategory() {
			return category;
		}



		public void setCategory(String category) {
			this.category = category;
		}



		public long getAcc_no() {
			return acc_no;
		}



		public void setAcc_no(long acc_no) {
			this.acc_no = acc_no;
		}



		public HashMap<Stock,Integer> getStocksOwned() {
			return stocksOwned;
		}



		public void setStocksOwned(Stock ob,int quantity) {
			this.stocksOwned.put(ob, quantity);
		}

		public void setData(String username, String password, String email, String pan, String phone, String adhaar, String IFSC, String dob, String MICR, String category, long acc_no) {
	        this.username = username;
	      //While implementation I will encrypt the password
	        this.password = password;
	        this.email = email;
	        this.pan = pan;
	        this.phone = phone;
	        this.adhaar = adhaar;
	        this.IFSC = IFSC;
	        this.dob = dob;
	        this.MICR = MICR;
	        this.category = category;
	        this.acc_no = acc_no;
	}

		public void register() {
	        boolean result = broker.register_users(username,password,email, pan, phone, adhaar, IFSC, dob, MICR, category, acc_no);
	        
	        if(result == true)
	        	System.out.println("Registration successful");
	        else
	        	System.out.println("Registration unsuccessful!");
	        
	    }
		
		public void unregister(String user) {
			
	        boolean result = broker.unregister_users(user);
	        
	        if(result == true)
	        	System.out.println("Unregistered successful");
	        else
	        	System.out.println("Unregistration failed!");
		}
		
		public void buyStock(String user,String stock,int quantity,String timeInforce) {
	        System.out.println("Enter what type of order you want to place.");
	        System.out.println("Enter 1 for limit order,2 for stoploss order,3 for stoplimit order and 4 for market order");
	        int input = sc.nextInt();
	        String orderType = "";
	        
	        switch (input){
	        	case 1:
	        		orderType = "limit";
	        		break;
	        	case 2:
	        		orderType = "stoploss";
	        		break;
	        	case 3:
	        		orderType = "stoplimit";
	        		break;
	        	case 4:
	        		orderType = "market";
	        		break;
	        	default:
	        		System.out.print("Wrong input!");
	        		
		}
	        int result = broker.placeOrder(user,stock,true,quantity,timeInforce,orderType);
	        Stock stockDetails = broker.getStockInfo(stock);
	        if(result == quantity) {
	        	setStocksOwned(stockDetails,quantity);
	        	System.out.println("Order Fully filled");
	        }
	        else if(result == -1) {
	        	System.out.println("Order Failed!");
	        }else {
	        	setStocksOwned(stockDetails,result);
	        	System.out.println("Order partially filled");
	        }
	        	
		}
		
		public void sellStock(String user,String stock,int quantity,String timeInforce) {
			System.out.println("Enter what type of order you want to place.");
	        System.out.println("Enter 1 for limit order,2 for stoploss order,3 for stoplimit order and 4 for market order");
	        int input = sc.nextInt();
	        String orderType = "";
	        
	        switch (input){
	        	case 1:
	        		orderType = "limit";
	        		break;
	        	case 2:
	        		orderType = "stoploss";
	        		break;
	        	case 3:
	        		orderType = "stoplimit";
	        		break;
	        	case 4:
	        		orderType = "market";
	        		break;
	        	default:
	        		System.out.print("Wrong input!");
	        		
		}
	        int result = broker.placeOrder(user,stock,true,quantity,timeInforce,orderType);
			Stock stockDetails = broker.getStockInfo(stock);
	        if(result == quantity) {
	        	stocksOwned.remove(stockDetails);
	        	System.out.println("Order Fully filled");
	        }
	        else if(result == -1) {
	        	System.out.println("Order Failed!");
	        }else {
	        	int remaining = quantity-result;
	        	stocksOwned.put(stockDetails, remaining);
	        	System.out.println("Order partially filled");
	        }
		}
		
		public void resetPassword() {
			String oldPass = "",newPass = "",username="";
			System.out.println("Enter username");
			username = sc.next();
			System.out.println("Enter old password");
			oldPass = sc.next();
			if(oldPass.equals(getPassword())) {
			System.out.println("Enter new password");
			newPass = sc.next();
			boolean result = broker.setnewpasswrd(username, newPass);
			if(result == true) {
				setPassword(newPass);
				System.out.println("Password changed successfully!");
			}
			else {
				System.out.println("Password change unsuccessful!");
			}
				
			}
		}
		
		public void queryBrokerageCharges(String name) {
			double result = broker.getBrokerage(name);
			System.out.println("Stock "+name+" has Rs."+result+"as brokerage charge");
			
		}
		public void getTransactionInfo(String name,boolean istaxStmt) {
			HashMap<String,AccountStatement> result = broker.getTransaction(name,istaxStmt);
			System.out.println(result);
		}
		public void searchStock(String name) {
			Stock result = new Stock();
			result = broker.getStockInfo(name);
			System.out.println(result);
		}
		public void ProfileDetails(String username) {
			User userDetails = new User();
			userDetails = broker.getUserDetails(username);
			System.out.println("Username:"+userDetails.getUsername());
			System.out.println("Phone number:"+userDetails.getPhone());
			System.out.println("Email ID:"+userDetails.getEmail());
			System.out.println("Category:"+userDetails.getCategory());
			System.out.println("Stocks owned:");
			HashMap<String,Stock> map = new HashMap<>();
			for(String stock : map.keySet()) {
				System.out.println(stock);
			}
		}

		public String getAdmin_password() {
			return admin_password;
		}
		
	}


