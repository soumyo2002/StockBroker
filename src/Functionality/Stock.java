package Functionality;

public class Stock {
	private String symbol;
	private double price;
	private double brokerageCharge;
	private String exchange;
	private int quantity;
	
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
}
