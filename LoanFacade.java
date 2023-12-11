package eShopSimulator;

public class LoanFacade {
    public static void processLoanPayment(double totalAmount, int numberOfMonths) {
        LoanPayment loanPayment = new LoanPayment(numberOfMonths);
        loanPayment.processPayment(totalAmount);
    }
}
