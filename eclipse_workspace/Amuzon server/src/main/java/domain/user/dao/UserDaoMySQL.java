package main.java.domain.user.dao;



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
import main.java.domain.login.model.Login;
import main.java.domain.operator.model.Operator;
import main.java.domain.order.model.Order;
import main.java.domain.ordersProduct.model.OrdersProduct;
import main.java.domain.product.dao.ProductFacade;
import main.java.domain.product.model.Product;
import main.java.domain.user.model.User;



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
            //System.out.println(user.getId()+"-"+user.getFirstName()+"-"+user.getSecondName()+"-"+user.getEmail()+"-"+user.getUsername()+"-"+user.getPassword());
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

	public ArrayList<User> getUsers(){
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			@SuppressWarnings("unchecked")
			TypedQuery<User> query=session.createQuery("from user");
			return (ArrayList<User>) query.getResultList();
		}catch(NoResultException e) {
			return null;
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
	public List<String> getUserUsernames() {
		List<String> usernames=new ArrayList<>();
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			@SuppressWarnings("unchecked")
			TypedQuery<User> query=session.createQuery("from user");
			
			for(User u:query.getResultList()) {
				usernames.add(u.getUsername().toLowerCase());
			}
			return usernames;
			
			
		}catch(NoResultException e) {
			return usernames;
		}
	}	
	
}
