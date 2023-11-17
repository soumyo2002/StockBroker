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
	    Authentication auth = new Authentication();

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
			this.IFSC = "";
			this.dob = "";
			this.MICR = "";
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
			System.out.println("Enter details for registration. Enter -1 at any point to discontinue registration");
			
			while(true) {
			System.out.println("Enter username");
			String username = sc.next();
			if(username == "-1") {
				return;
			}
			//Check if username already exists!
			if(broker.check_Username(username)) {
				this.setUsername(username);
				break;
			}else {
				System.out.println("Try again!Else press -1 to exit");
			}
			}
			
			while(true) {
			System.out.println("Enter password");
			String password = sc.next();
			if(password == "-1") {
				return;
			}
			//authenticating Details
			if(auth.passwordAuth(password)) {
				this.setPassword(password);
				break;
			}else {
				System.out.println("Try again!Else press -1 to exit");
			}
			}
			
			while(true) {
			System.out.println("Enter email ID");
			String email = sc.next();
			if(email == "-1") {
				return;
			}
			if(auth.otpAuth(email, true)) {
				this.setEmail(email);
				break;
			}else {
				System.out.println("Try again!Else press -1 to exit");
			}
		}
			
			while(true){
			System.out.println("Enter PAN number");
			String pan = sc.next();
			if(pan == "-1") {
				return;
			}
			if(auth.panAuth(pan)) {
			this.setPan(pan);
			break;
			}else {
				System.out.println("Try again!Else press -1 to exit");
			}
		}
			while(true) {
			System.out.println("Enter phone number");
			String phone = sc.next();
			if(phone == "-1") {
				return;
			}
			if(auth.otpAuth(phone, false)) {
			this.setPhone(phone);
			break;
			}else {
				System.out.println("Try again!Else press -1 to exit");
				}
			}
			
			while(true) {
			System.out.println("Enter adhaar number");
			String adhaar = sc.next();
			if(adhaar == "-1") {
				return;
			}
			if(auth.adhaarAuth(adhaar)) {
			this.setAdhaar(adhaar);
			break;
			}else {
				System.out.println("Try again!Else press -1 to exit");
			}
		}
			
			System.out.println("Enter date of birth");
			String dob = sc.next();
			if(dob == "-1") {
				return;
			}else {
			this.setDob(dob);
			}
			
			String category = "";
			while(true) {
			System.out.println("Enter category.Enter 1 for individual and 2 for institution");
			int input = sc.nextInt();
			if(input ==	-1) 
				return;
			else if(input == 1) {
				category = "individual";
				break;
			}
			else if(input == 2) {
				category = "institution";
				break;
			}
			else {
				System.out.println("Try again!Else press -1 to exit");
			}
			}
			this.setCategory(category);
			
			while(true) {
			System.out.println("Enter IFSC");
			String IFSC = sc.next();
			
			if(IFSC == "-1")
				return;
			
			System.out.println("Enter MICR");
			String MICR = sc.next();
			System.out.println("Enter acc_no");
			long acc_no = sc.nextLong();
			
			if(auth.bankAuth(IFSC, MICR, acc_no)) {
			
			this.setIFSC(IFSC);
			this.setMICR(MICR);
			this.setAcc_no(acc_no);
			break;
			}else {
				System.out.println("Try again!Else press -1 to exit");
			}
			}
			
			
			
			
			//After authentication,registering the user
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
	        //Setting the type of order
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
	        Stock ob = broker.getStockInfo(stock);
	        int quantityHeld =0;
	        if(stockOwned.containsKey(ob)) {
	        	quantityHeld = stockOwned.get(ob);
	        }
	        else {
	        	System.out.println("You don't have stocks to sell!");
	        	return;
	        }
	        int result = broker.placeOrder(username,acc_no,stock,false,quantity,timeInforce,orderType,stockOwned);
	        //Storing the Stock information 
			Stock stockDetails = broker.getStockInfo(stock);
			//Based on type of order placed,updating the details!
	        if(result == quantity) {
	        	System.out.println("Order Fully filled");
	        }
	        else if(result == -1) {
	        	System.out.println("Order Failed!");
	        }else {
	        	System.out.println("Order partially filled");
	        }
	        if(quantityHeld == result) {
	        this.stocksOwned.remove(stockDetails);
	        }else {
	        	int remaining = quantityHeld-result;
	        	this.stocksOwned.put(stockDetails, remaining);
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
			//Calling function to get transaction details
			HashMap<String,AccountStatement> result = broker.getTransaction(name,istaxStmt);
			int count = 1;
			for(AccountStatement stmt : result.values()) {
				System.out.println(count+".");
				System.out.println("Symbol: "+stmt.symbol);
				System.out.println("Account no: "+stmt.acc_no);
				System.out.println("Average Price: "+stmt.avgPrice);
				System.out.println("Transaction Date: "+stmt.date);
				System.out.println("Profit/Loss: "+stmt.pL);
				System.out.println("Position : "+stmt.position);
				System.out.println("Current value: "+stmt.presentValue);
				System.out.println("Quantity Held: "+stmt.quantity);
				count+=1;
				
			}
		}
		public void searchStock(String name) {
			Stock result = new Stock();
			result = broker.getStockInfo(name);
			if(result == null) {
				System.out.println("Stock is not listed!");
				return;
			}
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
			for(Stock stock : map.keySet()) {
				System.out.println("Symbol: "+stock.getSymbol()+" Quantity held: "+map.get(stock));
			}
		}

		public String getAdmin_password() {
			return admin_password;
		}
		
	}


