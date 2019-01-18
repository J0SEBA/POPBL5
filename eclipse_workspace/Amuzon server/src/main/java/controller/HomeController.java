package main.java.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import main.java.domain.operator.model.Operator;
import main.java.domain.order.dao.OrderFacade;
import main.java.domain.ordersProduct.dao.OrdersProductFacade;
import main.java.domain.product.dao.ProductFacade;
import main.java.domain.user.dao.UserFacade;
import main.java.domain.vehicle.dao.VehicleFacade;


@Controller
public class HomeController {

	
	UserFacade uf;
	VehicleFacade vf;
	OrderFacade of;
	ProductFacade pf;
	OrdersProductFacade opf;

	
	public HomeController() {
		uf=new UserFacade();
		vf=new VehicleFacade();
		of=new OrderFacade();
		pf=new ProductFacade();
		opf = new OrdersProductFacade();
	}

	  
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView showHome(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav;
		HttpSession session=request.getSession(false);
		
		if(session!=null && session.getAttribute("operator")!=null) {
			Operator aux = (Operator) session.getAttribute("operator");
			if(aux.isManager()) {
				mav = new ModelAndView("managerHome");
			}else {
				mav = new ModelAndView("operatorHome");
			}
			
			
		}else mav = new ModelAndView("home");
		return mav;
	}
	
	@RequestMapping(value = "/categoryView", method = RequestMethod.GET)
	public String searchForProduct(HttpServletRequest request, HttpServletResponse response,@RequestParam(value="category") String category) {
	    
		HttpSession session=request.getSession(false);
		request.setAttribute("products", pf.getProductsByCategory(category));
		return "categoryView";
	}
	
	
	
	

	
}
