package eShopSimulator;

import java.util.ArrayList;
import java.util.List;

public class Seller {
    private String name;
    private List<Product> products = new ArrayList<>();

    public Seller(String name) {
        this.name = name;
    }

    public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}


	public void addProduct(Product product) {
        products.add(product);
        product.setSeller(this); 
    }

    public void updateProduct(Product product, String name, String category, int stock, double price) {
        if (products.contains(product)) {
            product.updateDetails(name, category, stock, price);
        } else {
            System.out.println("Seller does not manage this product.");
        }
    }

    public void configurePromotion(Product product, double discountPercent, String validInterval) {
        if (products.contains(product)) {
            product.setPromotion(new Promotion(discountPercent, validInterval));
        } else {
            System.out.println("Seller does not manage this product.");
        }
    }

    public void showProducts() {
        System.out.println("Products managed by " + name + ":");
        for (Product product : products) {
            System.out.println(product);
        }
    }
}