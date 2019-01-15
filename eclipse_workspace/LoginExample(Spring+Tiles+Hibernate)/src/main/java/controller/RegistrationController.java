package main.java.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import main.java.dao.UserFacade;
import main.java.model.User;



@Controller
public class RegistrationController {
  
  UserFacade uf;
  
  public RegistrationController() {
		 uf=new UserFacade();
	 }
  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("register");
    mav.addObject("userRegister", new User());
    return mav;
  }
  @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
  public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
  @ModelAttribute("user") User user) {
	  	HttpSession session = request.getSession(true);
  		uf.register(user);
  		session.setAttribute("user", user);
  		ModelAndView mav=new ModelAndView("welcome", "firstname", user.getFirstName());
  	
  	return mav;
  }
}
