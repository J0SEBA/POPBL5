package main.java.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import main.java.model.Login;
import main.java.model.User;

@Controller 
public class CustomerController {

	User user;
	
	@RequestMapping(value = "/orderView", method = RequestMethod.GET)
	public ModelAndView showOrder(HttpServletRequest request, HttpServletResponse response) {
	    
		
		ModelAndView mav = new ModelAndView("orderView");
		
		return mav;
	}
	
	@RequestMapping(value = "/customerOrderList", method = RequestMethod.GET)
	public ModelAndView showOrderList(HttpServletRequest request, HttpServletResponse response) {
	    
		
		ModelAndView mav = new ModelAndView("orderList");
		
		return mav;
	}
}
