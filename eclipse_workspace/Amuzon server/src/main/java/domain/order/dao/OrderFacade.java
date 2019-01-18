package main.java.domain.order.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.java.config.HibernateUtil;
import main.java.domain.order.model.Order;

@Service
public class OrderFacade {
	@Autowired
	OrderDao daoOrder;
	
	
	public OrderFacade() {
		daoOrder = new OrderDaoMySQL();
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateUtil.class);
		
	}
	 
	@Transactional(readOnly=true)
	public List<Order> getOrders(){
		return daoOrder.getOrders();
	}
	
	@Transactional(readOnly = true)
	 public List<Order> getCurrentOrders(int userId) {
		 return daoOrder.getCurrentOrders(userId);
	 }
	 
	 @Transactional(readOnly = true)
	 public List<Order> getPreviousOrders(int userId){
		 return daoOrder.getPreviousOrders(userId);
	 }
	 
	 @Transactional
	 public void registerOrder(Map<Integer, Integer> cart,int userId,float total) {
		 daoOrder.registerOrder(cart,userId,total);;
	 }
}
