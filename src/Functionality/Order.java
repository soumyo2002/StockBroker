package Functionality;

public class Order {
	String status;
	
	public int placeOrder(String username,Stock ob,boolean isBuyOrder, int quantity, String timeInforce) {
		double finalprice = 0.0;
		if(isBuyOrder) {
			OrderAuth auth = new OrderAuth();
			double price = ob.getPrice();
			finalprice = quantity*price;
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
