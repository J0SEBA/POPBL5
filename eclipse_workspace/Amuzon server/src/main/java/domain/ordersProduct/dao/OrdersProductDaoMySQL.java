package main.java.domain.ordersProduct.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import main.java.config.HibernateUtil;
import main.java.domain.ordersProduct.model.OrdersProduct;

@Repository
public class OrdersProductDaoMySQL implements OrdersProductDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public ArrayList<OrdersProduct> getOrdersProducts() {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			@SuppressWarnings("unchecked")
			TypedQuery<OrdersProduct> query = session.createQuery("from orders_product");
			return (ArrayList<OrdersProduct>) query.getResultList();
		}catch(NoResultException e) {
			return null;
		}
	}

	@Override
	public List<OrdersProduct> getOrder(int orderId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			@SuppressWarnings("unchecked")
			TypedQuery<OrdersProduct> query=session.createQuery("from orders_product where orderID='"+orderId+"'");
			return query.getResultList();
		}catch(NoResultException e) {
			return null;
		}
	}
}
