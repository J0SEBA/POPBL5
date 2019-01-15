package main.java.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import main.java.dao.UserFacade;
import main.java.model.OrdersProduct;
import main.java.model.User;

@Controller 
public class CustomerController {

	User user;
	
	UserFacade uf;
	public CustomerController() {
		uf=new UserFacade();
	}
	
	@RequestMapping(value = "/orderView", method = RequestMethod.GET)
	public ModelAndView showOrder(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("orderView");
		HttpSession session=request.getSession(false);
		User user=(User) session.getAttribute("user");
		System.out.println(user);
		List<OrdersProduct> list=uf.getCurrentOrder(user.getId());
		if(list!=null) {
			List<String> productList=new ArrayList<>();
			for(OrdersProduct o:list) {
				productList.add(uf.getProductDescription(o.getProduct().getId()));
				
			}
			
			
			
			request.setAttribute("descriptions", productList);
		}
		request.setAttribute("prices", list);
		return mav;
	}
	
	@RequestMapping(value = "/customerOrderList", method = RequestMethod.GET)
	public ModelAndView showOrderList(HttpServletRequest request, HttpServletResponse response) {
	    
		
		ModelAndView mav = new ModelAndView("orderList");
		
		return mav;
	}
	
	
}
