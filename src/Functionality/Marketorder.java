package Functionality;

public class Marketorder extends Order {
String status;
public Marketorder() {
	super();
}

	
	public int placeOrder(long acc_no,Stock ob,boolean isBuyOrder, int quantity, String timeInforce) {
		double price = 0.0,finalprice = 0.0;
		if(isBuyOrder) {
			price = ob.getPrice();
			double brokerageCharge = ob.getBrokerageCharge();
			finalprice = (quantity*price)+brokerageCharge;
			OrderAuth auth = new OrderAuth();
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
