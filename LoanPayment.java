package eShopSimulator;

public class LoanPayment implements PaymentMethod {
    private int numberOfMonths;

    public LoanPayment(int numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }

    @Override
    public void processPayment(double amount) {
        double monthlyPayment = amount / numberOfMonths;
        System.out.println("Paid in " + numberOfMonths + " monthly installments of $" + monthlyPayment);
    }

    public int getNumberOfMonths() {
        return numberOfMonths;
    }
}