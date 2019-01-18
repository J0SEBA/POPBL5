package main.java.domain.operator.dao;



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
public class OperatorDaoMySQL implements OperatorDao {

	@Autowired
	private SessionFactory sessionFactory;
	   
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
	
}
