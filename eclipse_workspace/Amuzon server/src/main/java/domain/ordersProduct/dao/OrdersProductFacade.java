package main.java.domain.ordersProduct.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.java.config.HibernateUtil;
import main.java.domain.ordersProduct.model.OrdersProduct;

@Service
public class OrdersProductFacade {
	
	@Autowired
	OrdersProductDao daoOrdersPorduct;
	
	public OrdersProductFacade() {
		daoOrdersPorduct = new OrdersProductDaoMySQL();
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateUtil.class);
	}
	
	@Transactional(readOnly=true)
	public ArrayList<OrdersProduct> getOrdersProducts(){
		return daoOrdersPorduct.getOrdersProducts();
	}
	
	 @Transactional(readOnly = true)
	 public List<OrdersProduct> getOrder(int orderId) {
		 return daoOrdersPorduct.getOrder(orderId);
	 }
}
