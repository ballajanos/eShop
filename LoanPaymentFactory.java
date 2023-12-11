package eShopSimulator;

public class LoanPaymentFactory implements PaymentMethodFactory {

    private int numberOfMonths;

    public LoanPaymentFactory(int numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }

    @Override
    public PaymentMethod createPaymentMethod() {
        return new LoanPayment(numberOfMonths);
    }

}
