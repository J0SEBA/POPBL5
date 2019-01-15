package main.java.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import main.java.dao.UserFacade;


@Controller
public class HomeController {

	
	UserFacade uf;
	public HomeController() {
		uf=new UserFacade();
	}
	  
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
public ModelAndView showHome(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav;
		HttpSession session=request.getSession(false);
		
		if(session!=null && session.getAttribute("operator")!=null)
			mav = new ModelAndView("operatorHome");
		else mav = new ModelAndView("home");
		return mav;
	}
	
	@RequestMapping(value = "/categoryView", method = RequestMethod.GET)
	public String searchForProduct(HttpServletRequest request, HttpServletResponse response,@RequestParam(value="category") String category) {
	    
		
		request.setAttribute("products", uf.getProductsByCategory(category));
		
		return "categoryView";
	}
}
