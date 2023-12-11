package eShopSimulator;

public class CartItem {

	private Product product;
    private int quantity;
    private double originalPrice;
    private double discountedPrice;
    
    public CartItem(Product product, int quantity, double originalPrice, double discountedPrice) {
        this.product = product;
        this.quantity = quantity;
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
    }
    
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
    

    
    public void setDiscountedPrice(double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public double calculateDiscountedPrice() {
        Promotion promotion = product.getPromotion();
        if (promotion != null && promotion.isValidNow()) {
            double discountFactor = 1.0 - (promotion.getDiscountPercent() / 100.0);
            discountedPrice = originalPrice * discountFactor;
        } else {
            discountedPrice = originalPrice; // No promotion, use original price
        }
		return discountedPrice;
    }
}
