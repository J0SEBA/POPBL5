package main.java.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import main.java.model.Operator;

@Controller
public class ManagerController {

	Operator operator;
	
	@RequestMapping(value = "/historicalVehicles", method = RequestMethod.GET)
	public ModelAndView view(HttpServletRequest request, HttpServletResponse response) {
	    
		
		ModelAndView mav = new ModelAndView("historicalVehicles");
		
		return mav;
	}
	
	@RequestMapping(value = "/historicalOrders", method = RequestMethod.GET)
	public ModelAndView showHistoricalOrders(HttpServletRequest request, HttpServletResponse response) {
	    
		
		ModelAndView mav = new ModelAndView("historicalOrders");
		
		return mav;
	}
	
	@RequestMapping(value = "/historicalWorkstation", method = RequestMethod.GET)
	public ModelAndView viewHistoricalWorkstation(HttpServletRequest request, HttpServletResponse response) {
	    
		
		ModelAndView mav = new ModelAndView("historicalWorkstation");
		
		return mav;
	}
}
