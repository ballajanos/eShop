package eShopSimulator;

public class CardPayment implements PaymentMethod {

	@Override
	public void processPayment(double amount) {
		 System.out.println("Card payment processed for amount: $" + amount);
	}

}