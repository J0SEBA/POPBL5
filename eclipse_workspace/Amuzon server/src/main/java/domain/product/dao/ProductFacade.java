package main.java.domain.product.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.java.config.HibernateUtil;
import main.java.domain.product.model.Product;

@Service
public class ProductFacade {
	
	@Autowired
	ProductDao daoProduct;
	
	
	public ProductFacade() {
		daoProduct = new ProductDaoMySQL();
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateUtil.class);
	}
	
	@Transactional(readOnly=true)
	public List<Product> getProducts(){
		return daoProduct.getProducts();
	}
	
	@Transactional(readOnly = true)
	 public List<Product> getProductsByCategory(String category){
		 return daoProduct.getProductsByCategory(category);
	 }
	
	 @Transactional(readOnly = true)
	 public Product getProductById(int productId) {
		 return daoProduct.getProductById(productId);
	 }
	 
	 @Transactional(readOnly = true)
	 public List<Product> getProductsDescription(){
		 return daoProduct.getProducts();
	 }
	 
	 @Transactional(readOnly = true)
	 public String getProductDescription(int productId) {
		 return daoProduct.getProductDescription(productId);
	 }
}
