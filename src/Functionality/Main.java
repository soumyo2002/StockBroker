package Functionality;


public class Main {
	public static void main(String[] args) {
		User user = new User();
		user.setData("Soumyo123", "Passw0rd#", "soumyo@gmail.com", "xyz", "9874563124", "zyx", "273kit901", "17-01-1978", "wxyz", "individual", 8762349012L);
		user.register();
		StockBroker broker = new StockBroker();
		broker.addStock();
//		broker.addStock();
		broker.removeStock();
		user.searchStock("MRF");
		user.queryBrokerageCharges("MRF");
		user.buyStock("Soumyo123","MRF",10,"GTC");
		System.out.println("PROFILE:");
		user.ProfileDetails("Soumyo123");
		user.sellStock("Soumyo123","MRF",10,"GTC");
		user.resetPassword();
		user.getTransactionInfo("Soumyo123", false);
		user.unregister("Soumyo123");
	}
}
