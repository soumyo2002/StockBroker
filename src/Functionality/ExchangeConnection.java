package Functionality;

public class ExchangeConnection {
	
	public boolean getConnection() {
		// implement logic to form connection to the Exchange servers 
		return true;
	}

	public int placeOrder(String username, Stock stock, boolean isBuyOrder, int quantity, String timeInforce) {
		ExchangeConnection ob = new ExchangeConnection();
		boolean result = ob.getConnection();
		if(result) {
			Transaction txn = new Transaction();
			boolean ack = txn.initiateTransfer(username,stock,isBuyOrder,quantity,timeInforce);
			if(ack) {
				System.out.println("Money transfered successfully!");
			}else {
				System.out.println("Transaction failed!");
			}
		}else {
			System.out.println("Connection failed!Try again Later");
		}
		return quantity;
		
	}

}
