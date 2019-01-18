package main.java.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import main.java.domain.operator.model.Operator;
import main.java.domain.order.model.Order;
import main.java.domain.ordersProduct.model.OrdersProduct;
import main.java.domain.product.model.Product;
import main.java.domain.user.model.User;
import main.java.domain.vehicle.model.Vehicle;

public class HibernateUtil {
	 private static SessionFactory sessionFactory;
	 
	 public static SessionFactory getSessionFactory() {
		 if (sessionFactory == null) {
	         try {
	             Configuration configuration = new Configuration();
	             Properties settings = new Properties();
	             settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
	             settings.put(Environment.URL, "jdbc:mysql://localhost:3306/amuzon?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
	             settings.put(Environment.USER, "admin");
	             settings.put(Environment.PASS, "admin");
	             settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
	             settings.put(Environment.SHOW_SQL, "true");
	             settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
	             configuration.setProperties(settings);
	             configuration.addAnnotatedClass(User.class);
	             configuration.addAnnotatedClass(Operator.class);
	             configuration.addAnnotatedClass(Product.class);
	             configuration.addAnnotatedClass(Order.class);
	             configuration.addAnnotatedClass(OrdersProduct.class);
	             configuration.addAnnotatedClass(Vehicle.class);
	             ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	                 .applySettings(configuration.getProperties()).build();
	             sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	         } catch (Exception e) {
	             e.printStackTrace();
	             System.out.println("Couldn´t connect to database");
	         }
	     }
	     return sessionFactory;
	 }
}
