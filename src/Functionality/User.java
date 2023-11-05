package Functionality;
import java.util.*;

	public class User {
		
	    private String username;
	    private String password;
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
			//While implementation,before setting the password I'll encrypt it
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
	        boolean result = broker.register_users(username,password,email, pan, phone, adhaar, IFSC, dob, MICR, category, acc_no);
	        
	        if(result == true)
	        	System.out.println("Registration successful");
	        else
	        	System.out.println("Registration unsuccessful!");
	        
	    }
		
		public void unregister(User user) {
			
	        boolean result = broker.unregister_users(user);
	        
	        if(result == true)
	        	System.out.println("Unregistered successful");
	        else
	        	System.out.println("Unregistration failed!");
		}
		
		public void buyStock(User user,Stock stock,int quantity,String timeInforce) {
	        int result = broker.placeOrder(user,stock,true,quantity,timeInforce);
	        if(result == quantity) {
	        	setStocksOwned(stock,quantity);
	        	System.out.println("Order Fully filled");
	        }
	        else if(result == -1) {
	        	System.out.println("Order Failed!");
	        }else {
	        	setStocksOwned(stock,result);
	        	System.out.println("Order partially filled");
	        }
	        	
		}
		
		public void sellStock(User user,Stock stock,int quantity,String timeInforce) {
			int result = broker.placeOrder(user,stock,false,quantity,timeInforce);
	        if(result == quantity) {
	        	stocksOwned.remove(stock);
	        	System.out.println("Order Fully filled");
	        }
	        else if(result == -1) {
	        	System.out.println("Order Failed!");
	        }else {
	        	int remaining = quantity-result;
	        	stocksOwned.put(stock,remaining);
	        	System.out.println("Order partially filled");
	        }
		}
		
		public void resetPassword() {
			String oldPass = "",newPass = "";
			System.out.println("Enter old password");
			oldPass = sc.next();
			if(oldPass.equals(getPassword())) {
			System.out.println("Enter new password");
			newPass = sc.next();
			boolean result = broker.setnewpasswrd(newPass);
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
			List<AccountStatement> result = new ArrayList<>();
			result = broker.getTransaction(name,istaxStmt);
			System.out.println(result);
		}
		public void searchStock(String name) {
			Stock result = new Stock();
			result = broker.getStockInfo(name);
			System.out.println(result);
		}

		
		
	}


