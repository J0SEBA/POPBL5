package main.java.dao;



import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import main.java.config.HibernateUtil;
import main.java.model.Login;
import main.java.model.Operator;
import main.java.model.OrdersProduct;
import main.java.model.Product;
import main.java.model.User;



@Repository
public class UserDaoMySQL implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	   
	
	@Override
	public void register(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(user);
            // commit transaction
            System.out.println(user.getId()+"-"+user.getFirstName()+"-"+user.getSecondName()+"-"+user.getEmail()+"-"+user.getUsername()+"-"+user.getPassword());
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

	
	
	@Override
	public User validateUser(Login login) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			@SuppressWarnings("unchecked")
			TypedQuery<User> query=session.createQuery("from user where username='"+login.getUsername()+"' and password='"+login.getPassword()+"'");
			return query.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}
	
	@Override
	public Operator validateOperator(Login login) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			@SuppressWarnings("unchecked")
			TypedQuery<Operator> query=session.createQuery("from operator where username='"+login.getUsername()+"' and password='"+login.getPassword()+"'");
			return query.getSingleResult();
		}catch(NoResultException e) {
			return null;
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
	public List<OrdersProduct> getCurrentOrder(int userId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			@SuppressWarnings("unchecked")
			TypedQuery<Integer> query=session.createQuery("from order where userID='"+userId+"' and statee='Done'");
			int orderId=query.getSingleResult();
			@SuppressWarnings("unchecked")
			TypedQuery<OrdersProduct> result=session.createQuery("from OrdersProduct where orderID='"+orderId+"'");
			return result.getResultList();
		}catch(NoResultException e) {
			return null;
		}	
	}
	
	@Override
	public String getProductDescription(int productId){
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			@SuppressWarnings("unchecked")
			TypedQuery<Product> query=session.createQuery("from Product P where productID='"+productId+"'");
			return query.getSingleResult().getDescription();
		}catch(NoResultException e) {
			return null;
		}
	}
	
}
