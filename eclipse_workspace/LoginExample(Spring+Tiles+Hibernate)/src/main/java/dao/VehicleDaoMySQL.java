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
import main.java.model.Vehicle;

@Repository
public class VehicleDaoMySQL implements VehicleDao{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Vehicle> getVehicles() {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			@SuppressWarnings("unchecked")
			TypedQuery<Vehicle> query=session.createQuery("from vehicle");
			return query.getResultList();
		}catch(NoResultException e) {
			return null;
		}
	}
}
