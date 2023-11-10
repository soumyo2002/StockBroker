package Functionality;
import java.util.*;

public class StopLossOrder extends Order {
	double priceLimit;
	Scanner sc = new Scanner(System.in);
	public StopLossOrder() {
		super();
	}
	
	public int placeOrder(long acc_no,Stock ob,boolean isBuyOrder, int quantity, String timeInforce) {
		System.out.println("Enter Stop Loss");
		priceLimit = sc.nextDouble();
		if((isBuyOrder && ob.getPrice()<priceLimit) || (!isBuyOrder && ob.getPrice()>priceLimit))  {
				return -1;
		}
		//When stoploss is triggered,it becomes market order
		Order orderObj = new Marketorder();
		int quantityPlaced = orderObj.placeOrder(acc_no, ob, isBuyOrder, quantity, timeInforce);
		
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
