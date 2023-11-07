package Functionality;

import java.util.*;

public class LimitOrder extends Order {
	double priceLimit;
	Scanner sc = new Scanner(System.in);
	
	public int placeOrder(String username,Stock ob,boolean isBuyOrder, int quantity, String timeInforce) {
		double price = 0.0,finalprice = 0.0;
		System.out.println("Enter price limit");
		priceLimit = sc.nextDouble();
		if(isBuyOrder && ob.getPrice()<=priceLimit) {
			price = ob.getPrice();
			finalprice = quantity*price;
			OrderAuth auth = new OrderAuth();
			boolean result = auth.orderValidator(username,finalprice);
			if(result == false)
				return -1;
		}else if(!isBuyOrder && ob.getPrice()<priceLimit) {
			return -1;
		}
		ExchangeConnection connector = new ExchangeConnection();
		//Order will be placed at priceLimit or better price
		int quantityPlaced = connector.placeOrder(username,ob,isBuyOrder,quantity,timeInforce,finalprice);
		
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
