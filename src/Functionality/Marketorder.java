package Functionality;

public class Marketorder extends Order {
String status;

	
	public int placeOrder(String username,Stock ob,boolean isBuyOrder, int quantity, String timeInforce) {
		double price = 0.0,finalprice = 0.0;
		if(isBuyOrder) {
			price = ob.getPrice();
			finalprice = quantity*price;
			OrderAuth auth = new OrderAuth();
			boolean result = auth.orderValidator(username,finalprice);
			if(result == false)
				return -1;
		}
		ExchangeConnection connector = new ExchangeConnection();
		int quantityPlaced = connector.placeOrder(username,ob,isBuyOrder,quantity,timeInforce,finalprice);
		
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
