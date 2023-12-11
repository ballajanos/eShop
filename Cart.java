package eShopSimulator;

import java.util.List;

public class Cart {
    private List<CartItem> items;
    private DeliveryStrategy deliveryStrategy;
	private CartItem[] cartItems;

    public Cart(List<CartItem> items, DeliveryStrategy deliveryStrategy) {
        this.items = items;
        this.deliveryStrategy = deliveryStrategy;
    }
    
    public void addToCart(Product product, int quantity) throws OutOfStockException {
        if (product.getStock() >= quantity) {
            product.setReservedStock(product.getReservedStock() + quantity);
            this.items.add(new CartItem(product, quantity));
            System.out.println("Product added to the cart: " + product.getName() + " - Quantity: " + quantity);
        } else {
            throw new OutOfStockException("Insufficient stock for: " + product.getName());
        }
    }

    public void completeOrder(PaymentMethod paymentMethod, List<Promotion> promotions) {
        long now = System.currentTimeMillis();

        double total = calculateTotal();

        for (CartItem item : items) {
            Product product = item.getProduct();
            product.setStock(product.getStock() - item.getQuantity());
            product.setReservedStock(0); // Resetarea stocului rezervat după finalizarea comenzii
        }

        System.out.println("Total price after discount: $" + total);

        if (paymentMethod instanceof LoanPayment) {
            LoanFacade.processLoanPayment(total, ((LoanPayment) paymentMethod).getNumberOfMonths());
        }
        
        deliveryStrategy.deliver();
        
        System.out.println("Order completed successfully!");
        items.clear();
    }

    public void setDeliveryStrategy(DeliveryStrategy deliveryStrategy) {
        this.deliveryStrategy = deliveryStrategy;
    }
    
    private double calculateTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    private List<Promotion> getActivePromotions(Product product, List<Promotion> promotions, long currentTime) {
        return promotions.stream()
                .filter(promotion -> promotion.isValidNow())
                .filter(promotion -> promotionMatchesProduct(promotion, product))
                .toList();
    }
    
    private boolean promotionMatchesProduct(Promotion promotion, Product product) {
    	   String promotionProductName = getProductNameFromValidInterval(promotion.getValidInterval());
    	    return promotionProductName.equals(product.getName());
    }
    
    private String getProductNameFromValidInterval(String validInterval) {
        String[] intervalParts = validInterval.split(" to ");
        return intervalParts[0];
    }
    
    private double applyDiscount(Product product, Promotion promotion, int quantity) {
        double originalPrice = product.getPrice() * quantity;
        return promotion.applyDiscount(originalPrice);
    }
    
    public List<CartItem> getItems() {
        return items;
    }
    
    
    public void displayCart() {
        System.out.println("Shopping Cart Contents:");
        for (CartItem item : items) {
            System.out.println(item.getProduct().getName() +
                    " - Quantity: " + item.getQuantity() +
                    " - Price: $" + item.getTotalPrice());
        }
        System.out.println("Total Price: $" + calculateTotal());
    }
    
    public void displayCartWithPromotions(List<CartItem> discountedItems) {
        System.out.println("Cart:");
        for (CartItem cartItem : discountedItems) {
            Product product = cartItem.getProduct();
            double price = cartItem.calculateDiscountedPrice(); // Display discounted price
            int quantity = cartItem.getQuantity();
            System.out.println("  " + product.getName() + " x" + quantity + " - $" + price);
        }
        System.out.println("Total Amount: $" + calculateTotal());
    }
  

	public void cancelOrder(Order order) {
        order.cancel();
        items.removeIf(item -> item.getProduct().equals(order.getProduct()));
        System.out.println("Order canceled: " + order);
    }
}
