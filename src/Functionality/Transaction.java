package Functionality;

public class Transaction {
	String date;
	double amount;
	long userAccount;
	long exchangeAccount;
	
	public Transaction() {
		date = "";
		amount = 0.0;
		userAccount = 0L;
		exchangeAccount = 345708110;
	}


	public boolean initiateTransfer(long acc_no, Stock stock, boolean isBuyOrder, int quantity, String timeInforce, double finalprice) {
		//After transaction is successful
		AccountStatement stmt = new AccountStatement();
		stmt.createStatement(acc_no,stock,isBuyOrder,quantity,timeInforce,finalprice);
		return true;
	}
}
