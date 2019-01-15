package main.java.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.java.config.HibernateUtil;
import main.java.model.Vehicle;

@Service
public class VehicleFacade {

	@Autowired
	VehicleDao daoVehicle;
	
	public VehicleFacade() {
		daoVehicle=new VehicleDaoMySQL();
		AnnotationConfigApplicationContext context = 
	            new AnnotationConfigApplicationContext(HibernateUtil.class);
	}
	
	@Transactional(readOnly=true)
	public List<Vehicle> getVehicles(){
		return daoVehicle.getVehicles();
	}
}
