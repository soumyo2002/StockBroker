package Functionality;

import java.util.*;

//Class representing a Limit Order, which is a type of Order
public class LimitOrder extends Order {
	double priceLimit;// Price limit for the limit order
	Scanner sc = new Scanner(System.in);
	
	// Constructor to initialize a LimitOrder object
    public LimitOrder() {
        super(); // Call the constructor of the parent class (Order)
        this.priceLimit = 0.0;
    }
    // Method to place a limit order based on the provided parameters
	public int placeOrder(long acc_no,Stock ob,boolean isBuyOrder, int quantity, String timeInforce,HashMap<Stock, Integer>stocksTocheck) {
		double price = 0.0,finalprice = 0.0;
		System.out.println("Enter price limit");
		priceLimit = sc.nextDouble();
		
		if(stocksTocheck.containsKey(ob)) {
			int quantityHeld = stocksTocheck.get(ob);
		// Check if it's a buy order and if the stock price is below or equal to the price limit
		if(isBuyOrder && ob.getPrice()<=priceLimit) {
			price = ob.getPrice();
			double brokerageCharge = ob.getBrokerageCharge();
			OrderAuth auth = new OrderAuth();
			quantity+=quantityHeld;
			finalprice = (quantity*price)+brokerageCharge;
			// Validate the order using OrderAuth class
			boolean result = auth.orderValidator(acc_no,finalprice);
			// If the order is not valid, return -1
			if (!result)
				return -1;
			} else if (!isBuyOrder && ob.getPrice() >= priceLimit) {
            // If it's a sell order and the stock price is above or equal to the price limit
				price = ob.getPrice();
				finalprice = price + ob.getBrokerageCharge();
				if(quantityHeld < quantity) {
					System.out.println("You don't have enough stocks to sell!");
					return -1;
				}
        }else {
        	return -1;
        }
		}
		 // Create an ExchangeConnection object to interact with the exchange
        ExchangeConnection connector = new ExchangeConnection();

        // Place the order at the price limit or a better price
        int quantityPlaced = connector.placeOrder(acc_no, ob, isBuyOrder, quantity, timeInforce, finalprice);
        // Update the order status based on the quantity placed
		if(quantityPlaced == quantity) {
			this.status = "filled";
		}else {
			this.status = "working";
		}
		return quantityPlaced;
	}
	// Method to get the status of the limit order
	public String getStatus() {
		return (this.status);
	}

}
