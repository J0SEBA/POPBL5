package main.java.domain.product.dao;

import java.util.List;

import main.java.domain.product.model.Product;

public interface ProductDao {
	public List<Product> getProducts();
	public List<Product> getProductsByCategory(String category);
	public Product getProductById(int productId);
	public String getProductDescription(int productId);
}
