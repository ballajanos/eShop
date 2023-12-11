package eShopSimulator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Promotion {
    private double discountPercent;
    private String validInterval;

    public double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(double discountPercent) {
		this.discountPercent = discountPercent;
	}

	public String getValidInterval() {
		return validInterval;
	}

	public void setValidInterval(String validInterval) {
		this.validInterval = validInterval;
	}

	public Promotion(double discountPercent, String validInterval) {
        this.discountPercent = discountPercent;
        this.validInterval = validInterval;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "discountPercent=" + discountPercent +
                ", validInterval='" + validInterval + '\'' +
                '}';
    }
    
    public double applyDiscount(double originalPrice) {
        return originalPrice * (1 - discountPercent / 100);
    }

    public boolean isValidNow() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Împărțirea intervalul valid în părți de început și de sfârșit
        String[] intervalParts = validInterval.split(" to ");
        LocalDate startDate = LocalDate.parse(intervalParts[0], formatter);
        LocalDate endDate = LocalDate.parse(intervalParts[1], formatter);

        // Verifică dacă data curentă se află în intervalul valid
        return now.toLocalDate().isAfter(startDate) && now.toLocalDate().isBefore(endDate.plusDays(1)); // Add 1 day to include the end date
    }

}