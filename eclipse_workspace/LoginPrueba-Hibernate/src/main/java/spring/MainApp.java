package spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.HibernateUtil;
//import config.AppConfig;
import dao.UserFacade;
import model.Login;
import model.User;

public class MainApp {

	public static void main(String[] args) {
	      AnnotationConfigApplicationContext context = 
	            new AnnotationConfigApplicationContext(/*"hibernate.cfg.xml"*/HibernateUtil.class);

	      //UserFacade uf = context.getBean(UserFacade.class);
	      UserFacade uf = new UserFacade();
	      
	      //uf.register(new User("admin","admin","Joseba","Carnicero"));
	      //uf.register(new User("root","root","Jon","Mugica"));
	      //uf.register(new User("123","123","Pepito","Pepon"));
	      
	      Login login=new Login();
	      login.setUsername("admin");
	      login.setPassword("admin");
	      
	      User user=uf.validateUser(login);
	      System.out.println(user.getFirstName()+" "+user.getSecondName());
	      
	      login.setUsername("123");
	      login.setPassword("123");
	      
	      user=uf.validateUser(login);
	      System.out.println(user.getFirstName()+" "+user.getSecondName());
	      
	      
	      context.close();
	   }
	      
}
