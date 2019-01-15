package main.java.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.java.config.HibernateUtil;
import main.java.model.Login;
import main.java.model.Operator;
import main.java.model.OrdersProduct;
import main.java.model.Product;
import main.java.model.User;


@Service
public class UserFacade {
	@Autowired
	UserDao daoUser;
	
	
	public UserFacade(){
		daoUser = new UserDaoMySQL();
		AnnotationConfigApplicationContext context = 
	            new AnnotationConfigApplicationContext(HibernateUtil.class);
	}
	
	 @Transactional
	 public void register(User user) {
		 daoUser.register(user);
	 }
	
	 @Transactional(readOnly = true)
	 public User validateUser(Login login){
		 return daoUser.validateUser(login);
	 }
	
	 @Transactional(readOnly = true)
	 public Operator validateOperator(Login login){
		 return daoUser.validateOperator(login);
	 }

	 @Transactional(readOnly = true)
	 public List<Product> getProductsByCategory(String category){
		 return daoUser.getProductsByCategory(category);
	 }
	 
	 @Transactional(readOnly = true)
	 public List<OrdersProduct> getCurrentOrder(int userId) {
		 return daoUser.getCurrentOrder(userId);
	 }
	 
	 @Transactional(readOnly = true)
	 public String getProductDescription(int productId) {
		 return daoUser.getProductDescription(productId);
	 }
}