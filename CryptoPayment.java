package eShopSimulator;

public class CryptoPayment implements PaymentMethod {

	@Override
	   public void processPayment(double amount) {
        System.out.println("Paid with crypto: $" + amount);
    }

}
