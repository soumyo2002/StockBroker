package Functionality;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		User user = new User();
		Scanner sc = new Scanner(System.in);
		int input = -1;
		boolean loggedIn = false;
		while(input!=0) {
		System.out.println("Enter 1 to register users");
		System.out.println("Enter 2 to unregister users");
		System.out.println("Enter 3 to Login");
		System.out.println("Enter 4 to add stock");
		System.out.println("Enter 5 to remove stock");
		System.out.println("Enter 6 to buy stock");
		System.out.println("Enter 7 to sell stock");
		System.out.println("Enter 8 to reset password");
		System.out.println("Enter 9 to query brokerage charge");
		System.out.println("Enter 10 to get transaction info");
		System.out.println("Enter 11 to search stock");
		System.out.println("Enter 12 to view profile details");
		System.out.println("Enter 13 to Logout");
		System.out.println("Enter 14 to end session");
		input = sc.nextInt();
		
		if(input == 1) {
			user.register();
			continue;
		}else if(input == 3) {
			loggedIn = user.Login();
			if(loggedIn) 
				System.out.println("Logged In successfully!");
			else
				System.out.println("Log In failed!");
			continue;
		}else if(input == 14){
			break;
		}
		if(loggedIn) {
		if(input == 2) {
			System.out.println("Enter username");
			String username = sc.next();
			user.unregister(username);
			loggedIn = false;
			continue;
		}else if(input == 4) {
				user.addStock();
				continue;
		}else if(input == 5) {
				user.removeStock();
				continue;
		}else if(input == 6) {
				System.out.println("Enter username:");
				String name = sc.next();
				System.out.println("Enter stock symbol and quantity required:");
				String symbol = sc.next();
				int qnt = sc.nextInt();
				System.out.println("Enter time in enforcement:DAY or GTC");
				String time = sc.next();
				user.buyStock(name,symbol,qnt,time);
				continue;
		}else if(input == 7) {
				System.out.println("Enter username:");
				String name = sc.next();
				System.out.println("Enter stock symbol and quantity required:");
				String symbol = sc.next();
				int qnt = sc.nextInt();
				System.out.println("Enter time in enforcement:DAY or GTC");
				String time = sc.next();
				user.sellStock(name,symbol,qnt,time);
				continue;
		}else if(input == 8) {
				user.resetPassword();
				continue;
		}else if(input == 9) {
				System.out.println("Enter Stock Symbol");
				String symbol = sc.next();
				user.queryBrokerageCharges(symbol);
				continue;
		}else if(input == 10) {
				System.out.println("Enter username");
				String username = sc.next();
				System.out.println("Enter true if you want to get the statement for tax year else enter false");
				boolean isTax = sc.nextBoolean();
				user.getTransactionInfo(username, isTax);
				continue;
		}else if(input == 11) {
				System.out.println("Enter Stock Symbol");
				String symbol = sc.next();
				user.searchStock(symbol);
				continue;
		}else if(input == 12) {
				System.out.println("Enter username");
				String name = sc.next();
				user.ProfileDetails(name);
				continue;
		}else if(input == 13){
			user.Logout();
			loggedIn = false;
			continue;
		}
		}else {
			System.out.println("Please Log in first.");
		}
		}
		
	}
}
