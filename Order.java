package eShopSimulator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
	private Product product;
    private int quantity;
    private LocalDateTime orderTime;

    public Order(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.orderTime = LocalDateTime.now();
    }

    
    public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public LocalDateTime getOrderTime() {
		return orderTime;
	}


	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}


	public void cancel() {
        product.setStock(product.getStock() + quantity);
        System.out.println("Order canceled: " + this);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = orderTime.format(formatter);

        return "Order{" +
                "product=" + product.getName() +
                ", quantity=" + quantity +
                ", orderTime=" + formattedDate +
                '}';
    }
}
