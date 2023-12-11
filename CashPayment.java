package eShopSimulator;

public class CashPayment implements PaymentMethod {

	@Override
    public void processPayment(double amount) {
        System.out.println("Paid with cash: $" + amount);
    }

}
