package eShopSimulator;

import java.util.ArrayList;
import java.util.List;

class Product implements Subject {
    private String name;
    private String category;
    private int stock;
    private double price;
    private Promotion promotion;
    private List<Observer> observers = new ArrayList<>();
    private Seller seller;
    private int reservedStock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
    
    public int getReservedStock() {
        return reservedStock;
    }
    
    public void setReservedStock(int reservedStock) {
        this.reservedStock = reservedStock;
    }
    
    public Product(String name, String category, int stock, double price) {
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.price = price;
    }

    public void updateDetails(String name, String category, int stock, double price) {
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.price = price;
        notifyObservers("Product details updated");
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
        notifyObservers("Promotion configured");
    }
    
    public void configurePromotion(double discountPercent, String validInterval) {
        this.promotion = new Promotion(discountPercent, validInterval);
        notifyObservers("Promotion configured");
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(this, message);
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                ", promotion=" + (promotion != null ? promotion : "No promotion") +
                ", seller=" + (seller != null ? seller.getName() : "None") +
                '}';
    }


}
