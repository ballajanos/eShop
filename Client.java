package eShopSimulator;

import java.util.ArrayList;
import java.util.List;

public class Client implements Observer {
    private String name;
    private List<Product> watchList = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public Client(String name) {
        this.name = name;
    }

    public void addToWatchList(Product product) {
        watchList.add(product);
        product.addObserver(this);
    }

    public void buyProduct(Product product, int quantity) throws OutOfStockException {
        // Verifică dacă produsul este în stoc
        if (product.getStock() >= quantity) {
            Order order = new Order(product, quantity);
            orders.add(order);
            product.setStock(product.getStock() - quantity);
            System.out.println(name + " bought " + quantity + " of " + product.getName());
        } else {
            // Aruncă o excepție dacă produsul nu mai este în stoc
            throw new OutOfStockException(product.getName() + " is out of stock.");
        }
    }

    public void addToFavorites(Product product) {
        watchList.add(product);
        System.out.println(name + " added " + product.getName() + " to favorites.");
    }

    public void cancelOrder(Order order) {
        orders.remove(order);
        order.cancel();
        System.out.println(name + "'s order canceled: " + order);
    }

    @Override
    public void update(Product product, String message) {
        System.out.println(name + " received notification: " + message);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void displayOrders() {
        System.out.println(name + "'s Orders:");
        for (Order order : orders) {
            System.out.println(order);
        }
    }
    
    public void displayClientDetails() {
        System.out.println("Client Details:");
        System.out.println("Name: " + name);
        System.out.println("Watch List:");

        for (Product product : watchList) {
            System.out.println(product);
        }
    }
}
