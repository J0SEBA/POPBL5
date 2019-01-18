package main.java.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import main.java.domain.operator.model.Operator;
import main.java.domain.order.dao.OrderFacade;
import main.java.domain.order.model.Order;
import main.java.domain.ordersProduct.dao.OrdersProductFacade;
import main.java.domain.product.dao.ProductFacade;
import main.java.domain.product.model.Product;
import main.java.domain.user.dao.UserFacade;
import main.java.domain.vehicle.dao.VehicleFacade;
import main.java.domain.vehicle.model.Vehicle;

@Controller 
public class OperatorController {

	Operator operator;
	
	UserFacade uf;
	VehicleFacade vf;
	OrderFacade of;
	ProductFacade pf;
	OrdersProductFacade opf;

	
	public OperatorController() {
		uf=new UserFacade();
		vf=new VehicleFacade();
		of=new OrderFacade();
		pf=new ProductFacade();
		opf = new OrdersProductFacade();
	}
	
	@RequestMapping(value = "/vehicleList", method = RequestMethod.GET)
	public ModelAndView showVehicles(HttpServletRequest request, HttpServletResponse response) {
	    
		HttpSession session=request.getSession(false);
		if(session.getAttribute("operator")==null) {
			try {
				response.sendRedirect("login.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		ModelAndView mav = new ModelAndView("vehicleList");
		
		return mav;
	}
	
	@RequestMapping(value = "/operatorOrderList", method = RequestMethod.GET)
	public ModelAndView showOrderList(HttpServletRequest request, HttpServletResponse response) {
	    
		HttpSession session=request.getSession(false);
		if(session.getAttribute("operator")==null) {
			try {
				response.sendRedirect("login.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		ModelAndView mav = new ModelAndView("orderList");
		
		return mav;
	}
	
	@RequestMapping(value = "/productList", method = RequestMethod.GET)
	public ModelAndView showProductList(HttpServletRequest request, HttpServletResponse response) {
	    
		HttpSession session=request.getSession(false);
		if(session.getAttribute("operator")==null) {
			try {
				response.sendRedirect("login.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		ModelAndView mav = new ModelAndView("productList");
		
		return mav;
	}
	
	@RequestMapping(value="/getProductList", method = RequestMethod.GET)
	public void returnProductList(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			PrintWriter out = response.getWriter();
			List<Product> list = (List<Product>) pf.getProducts();
			String s = "";
			for(Product p:list) {
				String str = p.getId()+"/"+p.getDescription()+"/"+p.getPrice()+"/"+p.getStock();
				s = s + "," +str;
			}
			StringBuilder sb = new StringBuilder(s);
			sb.deleteCharAt(0);
			s = sb.toString();
			out.println(s);
		}catch(IOException e) {
			e.printStackTrace();
		}
	
	}
	
	@RequestMapping(value = "/orderList", method = RequestMethod.GET)
	public void returnOrderList(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			ArrayList<Order> list = (ArrayList<Order>) of.getOrders();
			String s = "";
			for(Order o:list) {
				String str = o.getId()+"/"+o.getDate().toString()+"/"+o.getTotal();
				s = s+","+str;
			}
			StringBuilder sb = new StringBuilder(s);
			sb.deleteCharAt(0);
			s = sb.toString();
			out.println(s);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/getVehicleList", method = RequestMethod.GET)
	public void returnVehicleList(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("sartu da");
		PrintWriter out;
		try {
			out = response.getWriter();
			ArrayList<Vehicle> list = (ArrayList<Vehicle>) vf.getVehicles();
			String s = "";
			for(Vehicle v:list) {
				String str = v.getId()+"/"+v.getPosition()+"/"+v.getState();
				str = str +"/"+ v.getSegmentCounter()*10;
				s = s+","+str;
			}
			StringBuilder sb = new StringBuilder(s);
			sb.deleteCharAt(0);
			s = sb.toString();
			System.out.println(s);
			out.println(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
