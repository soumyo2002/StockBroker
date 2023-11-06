package Functionality;

import java.util.*;

public class AccountStatement {
	Stock stock;
	double avgPrice;
	double presentValue;
	double pL;
	String position;
	String date;

	public AccountStatement() {
		stock = new Stock();
		avgPrice = 0.0;
		presentValue = 0.0;
		pL = 0.0;
		position = "";
		date = "";
	}

	public HashMap<String, AccountStatement> getStatement(String name, boolean istaxStmt) {
		return null;

		
	}

	public void createStatement(String username, Stock stock, boolean isBuyOrder, int quantity, String timeInforce) {
		
		
	}

}
