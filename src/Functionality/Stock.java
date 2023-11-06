package Functionality;

public class Stock {
	private String symbol;
	private double price;
	private double brokerageCharge;
	private String exchange;
	private int quantity;
	private String position;
	
	public Stock() {
		symbol = "";
		price = 0.0;
		brokerageCharge = 0.0;
		exchange = "";
		quantity = 0;
		position = "";
	}

	public void setData(String symbol, double price, double brokerageCharge, String exchange, int quantity) {
		this.symbol = symbol;
		this.price = price;
		this.brokerageCharge = brokerageCharge;
		this.exchange = exchange;
		this.quantity = quantity;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getBrokerageCharge() {
		return brokerageCharge;
	}
	public void setBrokerageCharge(double brokerageCharge) {
		this.brokerageCharge = brokerageCharge;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	
	
	public void renderGraph() {
		//Intention of this function is to show the real time price movement of stocks using some API in graph format
	}
}
