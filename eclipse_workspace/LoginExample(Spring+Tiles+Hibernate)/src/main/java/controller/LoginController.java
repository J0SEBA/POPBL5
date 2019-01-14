package main.java.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import main.java.dao.UserFacade;
import main.java.model.Login;
import main.java.model.Operator;
import main.java.model.User;



@Controller 
public class LoginController {

	User user;
	Operator operator;
	
	UserFacade uf;
	public LoginController() {
		uf=new UserFacade();
	}
	  
	  
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
	    
		
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("login", new Login());
		mav.addObject("user",null);
		return mav;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
	    
		
		ModelAndView mav = new ModelAndView("home");
		request.getSession().setAttribute("user", null);
		request.getSession().setAttribute("operator", null);
		return mav;
	}


	  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
	  @ModelAttribute("login") Login login) {
		  ModelAndView mav = null;
	    
		  	user = uf.validateUser(login);
		  	//operator=uf.validateOperator(login);
	   
	    
		    if (user!=null) {
		    	
			    	
			    	mav = new ModelAndView("welcome");
			    	request.getSession().setAttribute("user", user);
			    	mav.addObject("user", user);
		    	
	  		}else if((operator=uf.validateOperator(login))!=null){
	  			mav = new ModelAndView("operatorHome");
	  			request.getSession().setAttribute("operator", operator);
	  			request.getSession().setAttribute("manager", operator.isManager());
	  			
		    	
		    } else {
		    	mav = new ModelAndView("login");
		    	mav.addObject("message", "Username or Password is wrong!!");
		    }
		    return mav;
	  }
}
