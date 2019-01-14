package main.java.dao;



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
import main.java.model.User;



@Repository
public class UserDaoMySQL implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	   
	/*@Override
	public void register(User user) {
		sessionFactory.getCurrentSession().save(user);
		
	}*/
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

	/*@Override
	public User validateUser(Login login) {
		@SuppressWarnings("unchecked")
		TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User where username="+login.getUsername()+" & "+login.getPassword());
		return query.getSingleResult();
	}*/
	
	@Override
	public User validateUser(Login login) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			@SuppressWarnings("unchecked")
			TypedQuery<User> query=session.createQuery("from User U where U.username='"+login.getUsername()+"' and U.password='"+login.getPassword()+"'");
			return query.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}
	
	@Override
	public Operator validateOperator(Login login) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			@SuppressWarnings("unchecked")
			TypedQuery<Operator> query=session.createQuery("from Operator O where username='"+login.getUsername()+"' and password='"+login.getPassword()+"'");
			return query.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}
}
