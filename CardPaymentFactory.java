package eShopSimulator;

public class CardPaymentFactory implements PaymentMethodFactory {

	@Override
	public PaymentMethod createPaymentMethod() {
		 return new CardPayment();
	}

}
