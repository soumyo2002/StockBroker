package Functionality;

public class Transaction {
	String date;
	double amount;
	long userAccount;
	long exchangeAccount;
	

	public Transaction() {
		super();
		date = "";
		amount = 0.0;
		userAccount = 0L;
		exchangeAccount = 345708110;
	}

	public boolean initiateTransfer(String username, Stock stock, boolean isBuyOrder, int quantity, String timeInforce) {
		StockBroker broker = new StockBroker();
		User user = broker.getUserDetails(username);
		userAccount = user.getAcc_no();
		//initiate transfer using bank services
		AccountStatement stmt = new AccountStatement();
		stmt.createStatement(username,stock,isBuyOrder,quantity,timeInforce);
		return true;
	}
}
