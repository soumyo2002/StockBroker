package Functionality;

public class Order {
	String status;
	public Order() {
		this.status = "";
	}
	
	public int placeOrder(long acc_no,Stock ob,boolean isBuyOrder, int quantity, String timeInforce) {
		double finalprice = 0.0;
		if(isBuyOrder) {
			OrderAuth auth = new OrderAuth();
			double price = ob.getPrice();
			finalprice = quantity*price;
			boolean result = auth.orderValidator(acc_no,finalprice);
			if(result == false)
				return -1;
		}
		ExchangeConnection connector = new ExchangeConnection();
		int quantityPlaced = connector.placeOrder(acc_no,ob,isBuyOrder,quantity,timeInforce,finalprice);
		
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
