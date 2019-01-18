package main.java.domain.product.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import main.java.config.HibernateUtil;
import main.java.domain.product.model.Product;

@Repository
public class ProductDaoMySQL implements ProductDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Product> getProducts(){
		List<Product> products=new ArrayList<>();
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			@SuppressWarnings("unchecked")
			TypedQuery<Product> query = session.createQuery("from product");
			return (List<Product>) query.getResultList();
		}catch(NoResultException e) {
			return products;
		}
	}
	
	@Override
	public List<Product> getProductsByCategory(String category){
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			@SuppressWarnings("unchecked")
			TypedQuery<Product> query=session.createQuery("from product where categoryID='"+category+"'");
			return query.getResultList();
		}catch(NoResultException e) {
			return null;
		}
	}
	
	@Override
	public Product getProductById(int productId){
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			@SuppressWarnings("unchecked")
			TypedQuery<Product> query=session.createQuery("from product where productID='"+productId+"'");
			return query.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}
	
	@Override
	public String getProductDescription(int productId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			@SuppressWarnings("unchecked")
			TypedQuery<Product> query=session.createQuery("from product where productID='"+productId+"'");
			Product p=query.getSingleResult();
			
			return p.getDescription();
		}catch(NoResultException e) {
			return null;
		}
	}
}
