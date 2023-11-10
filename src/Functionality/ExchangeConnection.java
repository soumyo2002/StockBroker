package Functionality;

public class ExchangeConnection {
	
	public boolean getConnection() {
		// implement logic to form connection to the Exchange servers 
		return true;
	}

	public int placeOrder(long acc_no, Stock stock, boolean isBuyOrder, int quantity, String timeInforce,double finalprice) {
		ExchangeConnection ob = new ExchangeConnection();
		//Forming connection with Exchange server
		boolean result = ob.getConnection();
		if(result) {
			Transaction txn = new Transaction();
			//to transfer amount from bank
			boolean ack = txn.initiateTransfer(acc_no,stock,isBuyOrder,quantity,timeInforce,finalprice);
			//If acknowledgement is recieved then success else failed
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
