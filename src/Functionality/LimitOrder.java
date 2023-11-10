package Functionality;

import java.util.*;

public class LimitOrder extends Order {
	double priceLimit;
	Scanner sc = new Scanner(System.in);
	
	public LimitOrder() {
		super();
		this.priceLimit = 0.0;
	}

	public int placeOrder(long acc_no,Stock ob,boolean isBuyOrder, int quantity, String timeInforce) {
		double price = 0.0,finalprice = 0.0;
		System.out.println("Enter price limit");
		priceLimit = sc.nextDouble();
		if(isBuyOrder && ob.getPrice()<=priceLimit) {
			price = ob.getPrice();
			double brokerageCharge = ob.getBrokerageCharge();
			finalprice = (quantity*price)+brokerageCharge;
			OrderAuth auth = new OrderAuth();
			boolean result = auth.orderValidator(acc_no,finalprice);
			if(result == false)
				return -1;
		}else if(!isBuyOrder && ob.getPrice()<priceLimit) {
			return -1;
		}
		ExchangeConnection connector = new ExchangeConnection();
		//Order will be placed at priceLimit or better price
		int quantityPlaced = connector.placeOrder(acc_no,ob,isBuyOrder,quantity,timeInforce,finalprice);
		
		if(quantityPlaced == quantity) {
			this.status = "filled";
		}else {
			this.status = "working";
		}
		return quantityPlaced;
	}

	public String getStatus() {
		return (this.status);
	}

}
