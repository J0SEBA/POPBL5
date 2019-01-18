package main.java.domain.order.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import main.java.config.HibernateUtil;
import main.java.domain.order.model.Order;
import main.java.domain.ordersProduct.model.OrdersProduct;
import main.java.domain.product.dao.ProductFacade;
import main.java.domain.product.model.Product;

@Repository
public class OrderDaoMySQL implements OrderDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public ArrayList<Order> getOrders() {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			@SuppressWarnings("unchecked")
			TypedQuery<Order> query = session.createQuery("from order");
			return (ArrayList<Order>) query.getResultList();
		}catch(NoResultException e) {
			return null;
		}
	}
	
	@Override
	public List<Order> getCurrentOrders(int userId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			@SuppressWarnings("unchecked")
			TypedQuery<Order> query=session.createQuery("from order where userID='"+userId+"' and not statee='Finished'");
			return query.getResultList();
			
		}catch(NoResultException e) {
			return null;
		}	
	}
	
	@Override
	public List<Order> getPreviousOrders(int userId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			@SuppressWarnings("unchecked")
			TypedQuery<Order> query=session.createQuery("from order where userID='"+userId+"' and statee='Finished'");
			return query.getResultList();
			
		}catch(NoResultException e) {
			return null;
		}	
	}
	
	@Override
	public void registerOrder(Map<Integer,Integer> cart,int userId,float total) {
		Order order=new Order();
		int orderId;
		
		order.setUserId(userId);
		order.setDate(new java.sql.Date(System.currentTimeMillis()));
		order.setState("Unfinished");
		order.setTotal(total);
		
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.save(order);
			// commit transaction           
			transaction.commit();
			
			
		} catch (Exception e) {
			if (transaction != null) {
	                transaction.rollback();
			}
			e.printStackTrace();
		}
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			@SuppressWarnings("unchecked")
			TypedQuery<List<Order>> query=session.createQuery("from order where userID='"+userId+"' and not statee='Finished' order by orderID desc");
			
			orderId=((Order) query.getResultList().get(0)).getId();
			
			
		}catch(NoResultException e) {
		}    	
		
		for(Entry<Integer,Integer> e : cart.entrySet()) {
			ProductFacade pf=new ProductFacade();
			
			Product p=pf.getProductById(e.getKey());
			OrdersProduct op=new OrdersProduct(order, p, e.getValue(), p.getPrice());
			
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				// start a transaction
				transaction = session.beginTransaction();
				// save the student object
				session.save(op);
				
				Query query = session.createQuery("update product set stock = :stock" +
	    				" where productID = '"+e.getKey()+"'");
				query.setParameter("stock", p.getStock()-e.getValue());
				query.executeUpdate();
				// commit transaction           
				transaction.commit();
			} catch (Exception e1) {
				if (transaction != null) {
		                transaction.rollback();
				}
				e1.printStackTrace();
			}
		}
	}

}
