package main.java.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.java.config.HibernateUtil;
import main.java.model.Login;
import main.java.model.Operator;
import main.java.model.User;


@Service
public class UserFacade {
	@Autowired
	UserDao daoUser;
	
	
	public UserFacade(){
		//daoUser = new UserDaoMySQL();
		daoUser = new UserDaoMySQL();
		AnnotationConfigApplicationContext context = 
	            new AnnotationConfigApplicationContext(/*"hibernate.cfg.xml"*/HibernateUtil.class);
	}
	
	 @Transactional
	public void register(User user) {
		daoUser.register(user);
	}
	
	 @Transactional(readOnly = true)
	public User validateUser(Login login){
		return daoUser.validateUser(login);
	}
	
	 @Transactional(readOnly = true)
		public Operator validateOperator(Login login){
			return daoUser.validateOperator(login);
		}

}