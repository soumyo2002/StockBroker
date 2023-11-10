package Functionality;
import java.util.*;

//Class representing a user in the stock broker app
	public class User {
		
		private String username; // User's username
	    private String password; // User's password
	    private String admin_password = "admin"; // Admin password
	    private String phone; // User's phone number
	    private String pan; // User's PAN number
	    private String email; // User's email address
	    private String adhaar; // User's Aadhaar number
	    private String IFSC; // User's IFSC code
	    private String dob; // User's date of birth
	    private String MICR; // User's MICR code
	    private String category; // User's category
	    private long acc_no; // User's account number
	 // HashMap to store stocks owned by the user along with their quantities
	    private HashMap<Stock, Integer> stocksOwned;

	    // Flag to track the login status of the user
	    private boolean LoginStatus;
	    
	    Scanner sc = new Scanner(System.in);
	    StockBroker broker = new StockBroker(); // Stock broker associated with the user
	 // Default constructor to initialize the user attributes
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
			this.LoginStatus = false;
		}
		
		//Getters and Setters function

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
		
		public boolean Login() {
			LoginStatus = broker.Login();
			return LoginStatus;
		}
		
		public void Logout() {
			LoginStatus = false;
		}
		
		public void addStock() {
			broker.addStock();
		}
		
		public void removeStock() {
			broker.removeStock();
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
	        //Storing the stocks owned by the user
	        HashMap<Stock,Integer> stockOwned = this.getStocksOwned();
	        int result = broker.placeOrder(username,acc_no,stock,true,quantity,timeInforce,orderType,stockOwned);
	        //Storing the Stock information 
	        Stock stockDetails = broker.getStockInfo(stock);
	        //Based on type of order placed,updating the details!
	        if(result == quantity) {
	        	this.setStocksOwned(stockDetails,quantity);
	        	System.out.println("Order Fully filled");
	        }
	        else if(result == -1) {
	        	System.out.println("Order Failed!");
	        }else {
	        	this.setStocksOwned(stockDetails,result);
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
	      //Storing the stocks owned by the user
	        HashMap<Stock,Integer> stockOwned = this.getStocksOwned();
	        int result = broker.placeOrder(username,acc_no,stock,false,quantity,timeInforce,orderType,stockOwned);
	        //Storing the Stock information 
			Stock stockDetails = broker.getStockInfo(stock);
			//Based on type of order placed,updating the details!
	        if(result == quantity) {
	        	this.stocksOwned.remove(stockDetails);
	        	System.out.println("Order Fully filled");
	        }
	        else if(result == -1) {
	        	System.out.println("Order Failed!");
	        }else {
	        	int remaining = quantity-result;
	        	this.stocksOwned.put(stockDetails, remaining);
	        	System.out.println("Order partially filled");
	        }
		}
		
		public void resetPassword() {
			String oldPass = "",newPass = "",username="";
			//Verifying credentials
			System.out.println("Enter username");
			username = sc.next();
			if(this.getUsername() == username) {
				System.out.println("Wrong username!");
			}else {
			System.out.println("Enter old password");
			oldPass = sc.next();
			if(oldPass.equals(this.getPassword())) {
			System.out.println("Enter new password");
			newPass = sc.next();
			//Setting new password
			boolean result = broker.setnewpasswrd(username, newPass);
			if(result) {
				setPassword(newPass);
				System.out.println("Password changed successfully!");
			}
			else {
				System.out.println("Password change unsuccessful!");
			}
				
			}else {
				System.out.println("Wrong password");
			}
			}
		}
		
		public void queryBrokerageCharges(String name) {
			double result = broker.getBrokerage(name);
			System.out.println("Stock "+name+" has Rs."+result+" as brokerage charge");
			
		}
		public void getTransactionInfo(String name,boolean istaxStmt) {
			HashMap<String,AccountStatement> result = broker.getTransaction(name,istaxStmt);
			System.out.println(result);
		}
		public void searchStock(String name) {
			Stock result = new Stock();
			result = broker.getStockInfo(name);
			System.out.println("Stock Details:");
			System.out.println("Symbol:"+ result.getSymbol());
			System.out.println("Price:"+ result.getPrice());
			System.out.println("Quantity available:"+ result.getQuantity());
			System.out.println("Exchange:"+ result.getExchange());
			System.out.println("Brokerage Charge:"+ result.getBrokerageCharge());
		}
		public void ProfileDetails(String username) {
//			User userDetails = new User();
//			userDetails = broker.getUserDetails(username);
			System.out.println("Username:"+this.getUsername());
			System.out.println("Phone number:"+this.getPhone());
			System.out.println("Email ID:"+this.getEmail());
			System.out.println("Category:"+this.getCategory());
			System.out.println("Stocks owned:");
			HashMap<Stock,Integer> map = this.getStocksOwned();
				System.out.println(map);
		}

		public String getAdmin_password() {
			return admin_password;
		}
		
	}


