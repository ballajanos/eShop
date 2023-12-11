package eShopSimulator;

import java.util.ArrayList;
import java.util.List;

public class Main_eShop {
    public static void main(String[] args) throws OutOfStockException {
        EShop eShop = EShop.getInstance();

        Seller seller = new Seller("Seller1");
        eShop.registerSeller(seller);

        Product laptop = new Product("Laptop", "Electronics", 10, 999.99);
        Product smartphone = new Product("Smartphone", "Electronics", 15, 699.99);
        Product headphones = new Product("Headphones", "Audio", 8, 149.99);
        
        seller.addProduct(laptop);
        seller.addProduct(smartphone);
        seller.addProduct(headphones);

        eShop.addProduct(laptop);
        eShop.addProduct(smartphone);
        eShop.addProduct(headphones);

        Client client = new Client("Client1");
        eShop.registerClient(client);

        client.addToWatchList(laptop);

        eShop.updateProductDetails(laptop, "Gaming Laptop", "Electronics", 5, 1299.99);

        eShop.configurePromotion(laptop, 10, "2023-01-01 00:00:00 to 2024-02-01 23:59:59");

        eShop.showProductDetails();

        List<CartItem> cartItems = new ArrayList<>();
        Cart cart = new Cart(cartItems, new InStoreDelivery());

        cart.addToCart(laptop, 2);
        cart.addToCart(smartphone, 1);
       
		cart.addToCart(headphones, 5);
 
		cart.displayCart(cartItems);
		List<CartItem> discountedItems = eShop.applyPromotions(cart.getItems());
		cart.displayCartWithPromotions();
		
        double totalAmount = calculateTotalAmount(cart.getItems());
        PaymentMethod paymentMethod = new CardPayment();
        eShop.choosePaymentMethod(paymentMethod, totalAmount);
        
        cart.completeOrder(paymentMethod, eShop.getPromotions());

       
        eShop.showProductDetails();

        /*
        PaymentMethodFactory cardPaymentFactory = new CardPaymentFactory();
        PaymentMethod cardPayment = cardPaymentFactory.createPaymentMethod();
        cardPayment.processPayment(100.0);

        PaymentMethodFactory cashPaymentFactory = new CashPaymentFactory();
        PaymentMethod cashPayment = cashPaymentFactory.createPaymentMethod();
        cashPayment.processPayment(50.0);

        PaymentMethodFactory loanPaymentFactory = new LoanPaymentFactory(6); // Specify the number of months
        PaymentMethod loanPayment = loanPaymentFactory.createPaymentMethod();
        loanPayment.processPayment(600.0);

        PaymentMethodFactory cryptoPaymentFactory = new CryptoPaymentFactory();
        PaymentMethod cryptoPayment = cryptoPaymentFactory.createPaymentMethod();
        cryptoPayment.processPayment(0.01); */

      //  cart.addToCart(headphones, 5);
    //    cart.displayCart(discountedItems);
        
        List<Order> clientOrders = client.getOrders();
        if (!clientOrders.isEmpty()) {
            Order lastOrder = clientOrders.get(clientOrders.size() - 1);
            cart.cancelOrder(lastOrder);
        }

        client.displayClientDetails();
        
        Product discountedProduct = new Product("Discounted Product", "Electronics", 5, 149.99);
        eShop.configurePromotion(discountedProduct, 15, "2023-01-01 to 2023-12-14");
        cart.addToCart(discountedProduct, 3);
        

		cart.addToCart(discountedProduct, 10);
		
        eShop.showProductDetails();
        
    }

    private static double calculateTotalAmount(List<CartItem> cartItems) {
        double totalAmount = 0.0;

        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();
            double price = product.getPrice();

            // Calculate subtotal for each product
            double subtotal = quantity * price;

            // Add the subtotal to the total amount
            totalAmount += subtotal;
        }

        return totalAmount;
    }
}