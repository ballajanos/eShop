package eShopSimulator;

import java.util.ArrayList;
import java.util.List;

public class EShop {

	 private static EShop instance;
	    private List<Client> clients = new ArrayList<>();
	    private List<Seller> sellers = new ArrayList<>();
	    private List<Product> products = new ArrayList<>();

	    private EShop() {}

	    public static EShop getInstance() {
	        if (instance == null) {
	            instance = new EShop();
	        }
	        return instance;
	    }

	    public void registerClient(Client client) {
	        clients.add(client);
	    }

	    public void registerSeller(Seller seller) {
	        sellers.add(seller);
	    }

	    public void addProduct(Product product) {
	        products.add(product);
	    }

	    public void showProductDetails() {
	        for (Product product : products) {
	            System.out.println(product);
	        }
	    }

	    public void updateProductDetails(Product product, String name, String category, int stock, double price) {
	        product.updateDetails(name, category, stock, price);
	    }

	    public void configurePromotion(Product product, double discountPercent, String validInterval) {
	        product.configurePromotion(discountPercent, validInterval);
	    }
	    
	    public void choosePaymentMethod(PaymentMethod paymentMethod, double amount) {
	        paymentMethod.processPayment(amount);
	    }

	    public List<Promotion> getPromotions() {
	        return List.of(
	                new Promotion(10, "2023-01-01 to 2024-02-01"),
	                new Promotion(5, "2023-02-15 to 2023-03-15")
	        );
	    }
	    
	    public List<CartItem> applyPromotions(List<CartItem> cartItems) {
	        List<CartItem> discountedItems = new ArrayList<>();
	        for (CartItem cartItem : cartItems) {
	            Product product = cartItem.getProduct();
	            Promotion promotion = product.getPromotion();

	            if (promotion != null && promotion.isValidNow()) {
	                // Apply promotion discount to the price
	                double discountedPrice = promotion.applyDiscount(product.getPrice());
	                cartItem.setDiscountedPrice(discountedPrice);
	                discountedItems.add(cartItem);
	            }
	        }
	        return discountedItems;
	    }
}
