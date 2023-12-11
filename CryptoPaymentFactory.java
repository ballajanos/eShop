package eShopSimulator;

public class CryptoPaymentFactory implements PaymentMethodFactory {

	@Override
	public PaymentMethod createPaymentMethod() {
		  return new CryptoPayment();
	}

}
