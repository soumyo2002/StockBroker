package Functionality;

import java.util.*;

// Class representing a Stop Limit Order, extending the Order class
public class StopLimitOrder extends Order {
    double priceLimit, lossLimit; // Price and loss limits for the Stop Limit Order
    Scanner sc = new Scanner(System.in); // Scanner for user input

    // Default constructor to initialize priceLimit, lossLimit, and invoke the superclass constructor
    public StopLimitOrder() {
        super();
        this.priceLimit = 0.0;
        this.lossLimit = 0.0;
    }

    // Method to place a Stop Limit Order
    public int placeOrder(long acc_no, Stock ob, boolean isBuyOrder, int quantity, String timeInforce) {
        System.out.println("Enter Stop Loss");
        int quantityPlaced = 0;

        // Get the stop loss limit from the user
        priceLimit = sc.nextDouble();

        // Check if the current stock price triggers the stop loss
        if ((isBuyOrder && ob.getPrice() >= priceLimit) || (!isBuyOrder && ob.getPrice() <= priceLimit)) {
            // When stop loss is triggered, it becomes a Limit order
            Order orderObj = new LimitOrder();
            
            // Place a Limit Order with the stop loss trigger
            quantityPlaced = orderObj.placeOrder(acc_no, ob, isBuyOrder, quantity, timeInforce);

            // Update the status based on the quantity placed
            if (quantityPlaced == quantity) {
                this.status = "filled";
            } else {
                this.status = "working";
            }
        }
        return quantityPlaced;
    }

    // Method to get the status of the Stop Limit Order
    public String getStatus() {
        return (this.status);
    }
}
