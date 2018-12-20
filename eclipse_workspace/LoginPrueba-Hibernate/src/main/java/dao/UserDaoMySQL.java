package dao;



import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import config.HibernateUtil;
import model.Login;
import model.User;

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
			TypedQuery<User> query=session.createQuery("from User where username='"+login.getUsername()+"' and password='"+login.getPassword()+"'");
			return query.getSingleResult();
		}
	}
	
}
