package main.java.domain.operator.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.java.config.HibernateUtil;
import main.java.domain.login.model.Login;
import main.java.domain.operator.model.Operator;


@Service
public class OperatorFacade {
	@Autowired
	OperatorDao daoOperator;
	
	
	public OperatorFacade(){
		daoOperator = new OperatorDaoMySQL();
		AnnotationConfigApplicationContext context = 
	            new AnnotationConfigApplicationContext(HibernateUtil.class);
	}
	
	 @Transactional(readOnly = true)
	 public Operator validateOperator(Login login){
		 return daoOperator.validateOperator(login);
	 }
}