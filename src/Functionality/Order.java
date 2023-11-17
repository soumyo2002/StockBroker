package Functionality;

import java.util.HashMap;

// Class representing an Order
public class Order {
    String status; // Status of the order

    // Default constructor
    public Order() {
        this.status = ""; // Initialize the status
    }

    // Method to place an order based on the provided parameters
    public int placeOrder(long acc_no, Stock ob, boolean isBuyOrder, int quantity, String timeInforce, HashMap<Stock, Integer> stocksTocheck) {
        double finalprice = 0.0;

        // Check if it's a buy order
        if (isBuyOrder) {
            OrderAuth auth = new OrderAuth(); // Create an OrderAuth object for order validation
            double price = ob.getPrice();
            finalprice = quantity * price;

            // Validate the order using OrderAuth class
            boolean result = auth.orderValidator(acc_no, finalprice);

            // If the order is not valid, return -1
            if (!result)
                return -1;
        }

        // Create an ExchangeConnection object to interact with the exchange
        ExchangeConnection connector = new ExchangeConnection();

        // Place the order with the exchange
        int quantityPlaced = connector.placeOrder(acc_no, ob, isBuyOrder, quantity, timeInforce, finalprice);

        // Update the order status based on the quantity placed
        if (quantityPlaced == quantity) {
            this.status = "filled";
        } else {
            this.status = "working";
        }

        return quantityPlaced;
    }

    // Method to get the status of the order
    public String getStatus() {
        return this.status;
    }
}
