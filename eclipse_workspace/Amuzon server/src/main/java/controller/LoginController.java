package main.java.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import main.java.domain.login.model.Login;
import main.java.domain.operator.dao.OperatorFacade;
import main.java.domain.operator.model.Operator;
import main.java.domain.user.dao.UserFacade;
import main.java.domain.user.model.User;



@Controller 
public class LoginController {

	User user;
	Operator operator;
	
	UserFacade uf;
	OperatorFacade of;
	
	public LoginController() {
		uf=new UserFacade();
		of=new OperatorFacade();
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
	    
		HttpSession session=request.getSession();
		ModelAndView mav = new ModelAndView("home");
		session.setAttribute("user", null);
		session.setAttribute("operator", null);
		session.setAttribute("shoppingCart", new LinkedHashMap<Integer,Integer>());
		return mav;
	}


	  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
	  @ModelAttribute("login") Login login) {
		  ModelAndView mav = null;
	    
		  	user = uf.validateUser(login);
		  	
	   
	    
		    if (user!=null) {
		    	
			    	
			    	mav = new ModelAndView("home");
			    	request.getSession().setAttribute("user", user);
			    	Map<Integer,Integer> shoppingCart=new LinkedHashMap<>();
			    	request.getSession().setAttribute("shoppingCart", shoppingCart);
			    	
		    	
	  		}else if((operator=of.validateOperator(login))!=null){
	  			if(operator.isManager()) {
	  				mav= new ModelAndView("managerHome");
	  			}else {
	  				mav = new ModelAndView("operatorHome");
	  			}
	  			
	  			request.getSession().setAttribute("operator", operator);
	  			request.getSession().setAttribute("manager", operator.isManager());
	  			
		    	
		    } else {
		    	mav = new ModelAndView("login");
		    	mav.addObject("message", "Username or Password is wrong!!");
		    }
		    return mav;
	  }
}
