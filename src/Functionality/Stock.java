package Functionality;

// Class representing a Stock
public class Stock {
    private String symbol;          // Symbol of the stock
    private double price;           // Current price of the stock
    private double brokerageCharge; // Brokerage charge associated with the stock
    private String exchange;        // Exchange where the stock is traded
    private int quantity;           // Quantity of the stock
    private String position;        // Position (e.g., long or short)

    // Default constructor to initialize stock attributes
    public Stock() {
        symbol = "";
        price = 0.0;
        brokerageCharge = 0.0;
        exchange = "";
        quantity = 0;
        position = "";
    }

    // Method to set data for the stock
    public void setData(String symbol, double price, double brokerageCharge, String exchange, int quantity) {
        this.symbol = symbol;
        this.price = price;
        this.brokerageCharge = brokerageCharge;
        this.exchange = exchange;
        this.quantity = quantity;
    }

    // Getter and setter methods for position
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    // Getter and setter methods for quantity
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getter and setter methods for symbol
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    // Getter and setter methods for price
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Getter and setter methods for brokerageCharge
    public double getBrokerageCharge() {
        return brokerageCharge;
    }

    public void setBrokerageCharge(double brokerageCharge) {
        this.brokerageCharge = brokerageCharge;
    }

    // Getter and setter methods for exchange
    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    // Method to render a graph showing the real-time price movement of stocks using some API
    public void renderGraph() {
        // Intention of this function is to show the real-time price movement of stocks using some API in graph format
    }
}
