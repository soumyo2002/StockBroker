package Functionality;

public class Marketorder extends Order {
String status;
	
	public int placeOrder(String username,Stock ob,boolean isBuyOrder, int quantity, String timeInforce) {
		if(isBuyOrder) {
			double price = ob.getPrice();
			double finalprice = quantity*price;
			OrderAuth auth = new OrderAuth();
			boolean result = auth.orderValidator(username,finalprice);
			if(result == false)
				return -1;
		}
		ExchangeConnection connector = new ExchangeConnection();
		int quantityPlaced = connector.placeOrder(username,ob,isBuyOrder,quantity,timeInforce);
		
		if(quantityPlaced == quantity) {
			this.status = "filled";
		}else {
			this.status = "working";
		}
		return quantityPlaced;
	}
	
	public String getStatus() {
		return(this.status);
	}

}
