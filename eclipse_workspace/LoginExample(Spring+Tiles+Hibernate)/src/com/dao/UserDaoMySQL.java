package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;


import com.model.Login;
import com.model.User;

public class UserDaoMySQL implements UserDao{

	@Autowired
	public SessionFactory sessionFactory;

	
    
    
	@Override
	public void register(User user) {
		sessionFactory.openSession();
		sessionFactory.getCurrentSession().save(user);
		sessionFactory.close();
	     
	}

	@Override
	public User validateUser(Login login) {
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		User u = (User) session.load(User.class, login.getUsername());
		return u;
	}

	
}
