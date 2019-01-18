package main.java.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import main.java.domain.order.dao.OrderFacade;
import main.java.domain.order.model.Order;
import main.java.domain.ordersProduct.dao.OrdersProductFacade;
import main.java.domain.ordersProduct.model.OrdersProduct;
import main.java.domain.product.dao.ProductFacade;
import main.java.domain.product.model.Product;
import main.java.domain.user.dao.UserFacade;
import main.java.domain.user.model.User;
import main.java.domain.vehicle.dao.VehicleFacade;

@Controller 
public class CustomerController {

	UserFacade uf;
	VehicleFacade vf;
	OrderFacade of;
	ProductFacade pf;
	OrdersProductFacade opf;

	
	public CustomerController() {
		uf=new UserFacade();
		vf=new VehicleFacade();
		of=new OrderFacade();
		pf=new ProductFacade();
		opf = new OrdersProductFacade();
	}
	
	@RequestMapping(value = "/customerActualOrders", method = RequestMethod.GET)
	public ModelAndView showOrders(HttpServletRequest request, HttpServletResponse response) {
		
		
		HttpSession session=request.getSession(false);
		if(session.getAttribute("user")==null) {
			try {
				response.sendRedirect("login.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		
		
		
		User user=(User) session.getAttribute("user");
		List<Order> list=of.getCurrentOrders(user.getId());
		request.setAttribute("orders", list);
		return new ModelAndView("customerOrderList");
	}
	
	@RequestMapping(value = "/customerPreviousOrders", method = RequestMethod.GET)
	public ModelAndView showOrderList(HttpServletRequest request, HttpServletResponse response) {
		
		
		HttpSession session=request.getSession(false);
		if(session.getAttribute("user")==null) {
			try {
				response.sendRedirect("login.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		
		
		User user=(User) session.getAttribute("user");
		System.out.println(user);
		List<Order> list=of.getPreviousOrders(user.getId());
		request.setAttribute("orders", list);
		return new ModelAndView("customerOrderList");
	}
	
	@RequestMapping(value = "/customerOrderView", method = RequestMethod.GET)
	public String showOrder(HttpServletRequest request, HttpServletResponse response,@RequestParam(value="orderId") int orderId) {
	    
		HttpSession session=request.getSession(false);
		if(session.getAttribute("user")==null) {
			try {
				response.sendRedirect("login.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		List<OrdersProduct> op=opf.getOrder(orderId);
		List<String> descriptions = new ArrayList<>();
		
		for(OrdersProduct o:op) {
			descriptions.add(pf.getProductDescription(o.getProduct().getId()));
		}
		request.setAttribute("ordersProduct", op);
		request.setAttribute("descriptions", descriptions);
		return "orderView";
	}
	
	@RequestMapping(value = "/shoppingCart", method = RequestMethod.GET)
	public ModelAndView showShoppingCart(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session=request.getSession(false);
		if(session.getAttribute("user")==null) {
			try {
				response.sendRedirect("login.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		@SuppressWarnings("unchecked")
		Map<Integer,Integer> cart=(Map<Integer, Integer>) session.getAttribute("shoppingCart");
		List<Integer> quantities = new ArrayList<>();
		List<Product> products=new ArrayList<>();
		float total=0;
		for(Entry<Integer,Integer> e : cart.entrySet()) {
			Product p=pf.getProductById(e.getKey());
			products.add(p);
			quantities.add(e.getValue());
			total+=p.getPrice()*e.getValue();
		}

		request.setAttribute("quantities", quantities);
		request.setAttribute("products", products);
		total=(float) (Math.round(total*100) / 100.0);
		session.setAttribute("total", total);
		
		return new ModelAndView("shoppingCart");
	}
	
	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public ModelAndView seePayment(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session=request.getSession(false);
		if(session.getAttribute("user")==null) {
			try {
				response.sendRedirect("login.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		return new ModelAndView("payment");
	}
	
	@RequestMapping(value = "/getSelectedProducts", method = RequestMethod.POST)
	public String addProductsToOrder(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session=request.getSession(false);
		if(session.getAttribute("user")==null) {
			try {
				response.sendRedirect("login.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		@SuppressWarnings("unchecked")
		Map<Integer,Integer> cart=(Map<Integer, Integer>) session.getAttribute("shoppingCart");
		
		
		for(int i=0;i<Integer.valueOf(request.getParameter("count"));i++){
			boolean cb=request.getParameter("cb"+i) != null;
			if(cb){
				
				int q=0;
				if(cart.get(Integer.valueOf(request.getParameter("cb"+i)))!=null)
					q=cart.get(Integer.valueOf(request.getParameter("cb"+i)));
				cart.put(Integer.valueOf(request.getParameter("cb"+i)),
					q+Integer.valueOf(request.getParameter("quantity"+i)));
			}
		}
		
		session.setAttribute("shoppingCart", cart);
		
		if(request.getParameter("action").equals("keepBuying"))
			return "home";
		else return "redirect:shoppingCart.html";
	}
	
	@RequestMapping(value = "/paymentProcess", method = RequestMethod.POST)
	public String makePayment(HttpServletRequest request, HttpServletResponse response) {
		
		
		
		HttpSession session=request.getSession(false);
		if(session.getAttribute("user")==null) {
			try {
				response.sendRedirect("login.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		User user=(User)session.getAttribute("user");
		float total=(float) session.getAttribute("total");
		
		of.registerOrder((Map<Integer, Integer>) session.getAttribute("shoppingCart"),user.getId(),total);
		
		session.setAttribute("shoppingCart", new LinkedHashMap<Integer,Integer>());
		session.removeAttribute("total");
		
			
		
		return "redirect:home.html";
		
	}
}
