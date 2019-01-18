package main.java.domain.user.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.java.config.HibernateUtil;
import main.java.domain.login.model.Login;
import main.java.domain.operator.model.Operator;
import main.java.domain.ordersProduct.model.OrdersProduct;
import main.java.domain.user.model.User;


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
	
	 @Transactional
	 public ArrayList<User> getUsers() {
		 return daoUser.getUsers();
	 }
	 
	 @Transactional(readOnly = true)
	 public User validateUser(Login login){
		 return daoUser.validateUser(login);
	 }
	
	
	 
	 @Transactional(readOnly = true)
	 public List<String> getUserUsernames() {
		 return daoUser.getUserUsernames();
	 }
	 
	 
	 
	
	 

}