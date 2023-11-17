package Functionality;

import java.util.*;

// Class representing a Stop Limit Order, extending the Order class
public class StopLimitOrder extends Order {
    double stopPrice, limitPrice; // Price and loss limits for the Stop Limit Order
    Scanner sc = new Scanner(System.in); // Scanner for user input

    // Default constructor to initialize priceLimit, lossLimit, and invoke the superclass constructor
    public StopLimitOrder() {
        super();
        this.stopPrice = 0.0;
        this.limitPrice = 0.0;
    }

    // Method to place a Stop Limit Order
    public int placeOrder(long acc_no, Stock ob, boolean isBuyOrder, int quantity, String timeInforce,HashMap<Stock, Integer>stocksTocheck) {
    	int quantityPlaced = 0;
    	// Get the stop loss limit from the user
        System.out.println("Enter stopPrice");
        stopPrice = sc.nextDouble();
        System.out.println("Enter limitPrice");
        limitPrice = sc.nextDouble();
       if(isBuyOrder) {
        if (ob.getPrice() <= stopPrice) {
            if (ob.getPrice() <= limitPrice) {
                System.out.println("Buying " + quantity + " shares of " + ob.getSymbol() +
                        " at limit price Rs." + limitPrice + " as it reached the stop price Rs." + stopPrice);
             // When stop loss is triggered, it becomes a Limit order
                Order orderObj = new Marketorder();
                
                // Place a Limit Order with the stop loss trigger
                quantityPlaced = orderObj.placeOrder(acc_no, ob, isBuyOrder, quantity, timeInforce,stocksTocheck);

                // Update the status based on the quantity placed
                if (quantityPlaced == quantity) {
                    this.status = "filled";
                } else {
                    this.status = "working";
                }
            } else {
                System.out.println("Stop price Rs." + stopPrice + " reached, but limit price Rs." + limitPrice + " not met yet for buying.");
            }
        } else {
            System.out.println("Stop price Rs." + stopPrice + " not reached for buying.");
            return -1;
        }
       }else {
           if (ob.getPrice() >= stopPrice) {
               if (ob.getPrice() >= limitPrice) {
                   System.out.println("Buying " + quantity + " shares of " + ob.getSymbol() +
                           " at limit price Rs." + limitPrice + " as it reached the stop price Rs." + stopPrice);
                // When stop loss is triggered, it becomes a Limit order
                   Order orderObj = new LimitOrder();
                   
                   // Place a Limit Order with the stop loss trigger
                   quantityPlaced = orderObj.placeOrder(acc_no, ob, isBuyOrder, quantity, timeInforce,stocksTocheck);

                   // Update the status based on the quantity placed
                   if (quantityPlaced == quantity) {
                       this.status = "filled";
                   } else {
                       this.status = "working";
                   }
               } else {
                   System.out.println("Stop price Rs." + stopPrice + " reached, but limit price Rs." + limitPrice + " not met yet for buying.");
               }
           } else {
               System.out.println("Stop price Rs." + stopPrice + " not reached for buying.");
               return -1;
           }
       }
        return quantityPlaced;
    }

    // Method to get the status of the Stop Limit Order
    public String getStatus() {
        return (this.status);
    }
}
