package config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import model.User;

public class HibernateUtil {
	 private static SessionFactory sessionFactory;
	 
	 public static SessionFactory getSessionFactory() {
		 if (sessionFactory == null) {
	         try {
	             Configuration configuration = new Configuration();
	             // Hibernate settings equivalent to hibernate.cfg.xml's properties
	             Properties settings = new Properties();
	             settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
	             settings.put(Environment.URL, "jdbc:mysql://localhost:3306/myusers?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
	             settings.put(Environment.USER, "admin");
	             settings.put(Environment.PASS, "admin");
	             settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
	             settings.put(Environment.SHOW_SQL, "true");
	             settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
	             //settings.put(Environment.HBM2DDL_AUTO, "create-drop");
	             configuration.setProperties(settings);
	             configuration.addAnnotatedClass(User.class);
	             ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	                 .applySettings(configuration.getProperties()).build();
	             sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	         } catch (Exception e) {
	             e.printStackTrace();
	         }
	     }
	     return sessionFactory;
	 }
}
