package Functionality;

import java.util.*;

// Class representing a Stop Loss Order, extending the Order class
public class StopLossOrder extends Order {
    double stopLoss;
    Scanner sc = new Scanner(System.in); // Scanner for user input

    // Default constructor to initialize the priceLimit and invoke the superclass constructor
    public StopLossOrder() {
        super();
        stopLoss = 0.0;
    }

    // Method to place a Stop Loss Order
    public int placeOrder(long acc_no, Stock ob, boolean isBuyOrder, int quantity, String timeInforce,HashMap<Stock, Integer>stocksTocheck) {
        System.out.println("Enter Stop Loss");
        stopLoss = sc.nextDouble();

        // Check if the current stock price triggers the stop loss
        if ((isBuyOrder && ob.getPrice() < stopLoss) || (!isBuyOrder && ob.getPrice() > stopLoss)) {
            return -1; // Stop Loss condition is met, return -1 indicating failure to place the order
        }

        // When stop loss is triggered, it becomes a Market order
        Order orderObj = new Marketorder();
        int quantityPlaced = orderObj.placeOrder(acc_no, ob, isBuyOrder, quantity, timeInforce,stocksTocheck);

        // Update the status based on the quantity placed
        if (quantityPlaced == quantity) {
            this.status = "filled";
        } else {
            this.status = "working";
        }
        return quantityPlaced;
    }

    // Method to get the status of the Stop Loss Order
    public String getStatus() {
        return (this.status);
    }
}
