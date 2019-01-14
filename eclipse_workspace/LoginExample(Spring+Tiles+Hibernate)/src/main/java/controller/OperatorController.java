package main.java.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import main.java.model.Operator;

@Controller 
public class OperatorController {

	Operator operator;
	
	@RequestMapping(value = "/vehicleList", method = RequestMethod.GET)
	public ModelAndView showVehicles(HttpServletRequest request, HttpServletResponse response) {
	    
		
		ModelAndView mav = new ModelAndView("vehicleList");
		
		return mav;
	}
	
	@RequestMapping(value = "/vehicleView", method = RequestMethod.GET)
	public ModelAndView showVehicle(HttpServletRequest request, HttpServletResponse response) {
	    
		
		ModelAndView mav = new ModelAndView("vehicleView");
		
		return mav;
	}
	
	@RequestMapping(value = "/operatorOrderList", method = RequestMethod.GET)
	public ModelAndView showOrderList(HttpServletRequest request, HttpServletResponse response) {
	    
		
		ModelAndView mav = new ModelAndView("orderList");
		
		return mav;
	}
	
	@RequestMapping(value = "/productList", method = RequestMethod.GET)
	public ModelAndView showProductList(HttpServletRequest request, HttpServletResponse response) {
	    
		
		ModelAndView mav = new ModelAndView("productList");
		
		return mav;
	}
}
