package Functionality;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		User user = new User();
		user.setData("Soumyo123", "passw0rd", "soumyo@gmail.com", "xyz", "9874563124", "zyx", "273kit901", "17-01-1978", "wxyz", "individual", 8762349012L);
		Stock ob = new Stock();
		ob.setSymbol("MRF");
		ob.setBrokerageCharge(110.0);
		ob.setExchange("NSE");
		ob.setPrice(110485.50);
		user.buyStock(ob,10,"GTC");
		user.resetPassword();
	}
}
