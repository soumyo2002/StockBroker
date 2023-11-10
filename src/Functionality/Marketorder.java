package Functionality;

// Class representing a Market Order, which is a type of Order
public class Marketorder extends Order {
    String status; // Status of the market order (filled or working)

    // Default constructor
    public Marketorder() {
        super(); // Call the constructor of the parent class (Order)
    }

    // Method to place a market order based on the provided parameters
    public int placeOrder(long acc_no, Stock ob, boolean isBuyOrder, int quantity, String timeInforce) {
        double price = 0.0, finalprice = 0.0;

        // Check if it's a buy order
        if (isBuyOrder) {
            price = ob.getPrice();
            double brokerageCharge = ob.getBrokerageCharge();
            finalprice = (quantity * price) + brokerageCharge;

            // Validate the order using OrderAuth class
            OrderAuth auth = new OrderAuth();
            boolean result = auth.orderValidator(acc_no, finalprice);

            // If the order is not valid, return -1
            if (!result)
                return -1;
        }

        // Create an ExchangeConnection object to interact with the exchange
        ExchangeConnection connector = new ExchangeConnection();

        // Place the market order
        int quantityPlaced = connector.placeOrder(acc_no, ob, isBuyOrder, quantity, timeInforce, finalprice);

        // Update the order status based on the quantity placed
        if (quantityPlaced == quantity) {
            this.status = "filled";
        } else {
            this.status = "working";
        }

        return quantityPlaced;
    }

    // Method to get the status of the market order
    public String getStatus() {
        return this.status;
    }
}
