package main.java.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import main.java.domain.operator.model.Operator;
import main.java.domain.order.dao.OrderFacade;
import main.java.domain.order.model.Order;
import main.java.domain.ordersProduct.dao.OrdersProductFacade;
import main.java.domain.ordersProduct.model.OrdersProduct;
import main.java.domain.product.dao.ProductFacade;
import main.java.domain.product.model.Product;
import main.java.domain.user.dao.UserFacade;
import main.java.domain.user.model.User;
import main.java.domain.vehicle.dao.VehicleFacade;
import main.java.domain.vehicle.model.Vehicle;

@Controller
public class ManagerController {

	Operator operator;
	
	UserFacade uf;
	VehicleFacade vf;
	OrderFacade of;
	ProductFacade pf;
	OrdersProductFacade opf;

	
	public ManagerController() {
		uf=new UserFacade();
		vf=new VehicleFacade();
		of=new OrderFacade();
		pf=new ProductFacade();
		opf = new OrdersProductFacade();
	}
		
	@RequestMapping(value = "/pieChart", method = RequestMethod.GET)
	public void test(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("pieChart");
				PrintWriter out = response.getWriter();
				ArrayList<Vehicle> list = (ArrayList<Vehicle>) vf.getVehicles();
				String data = list.get(0).getSegmentCounter()*10+","
				+list.get(1).getSegmentCounter()*10+","
				+list.get(2).getSegmentCounter()*10+","
				+list.get(3).getSegmentCounter()*10+","
				+list.get(4).getSegmentCounter()*10;
				System.out.println(data);
				out.println(data);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/getNumberOfProductsPerCategory", method = RequestMethod.GET)
	public void returnNumberOfProductsPerCategory(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("workstation");
			PrintWriter out = response.getWriter();
			ArrayList<Product> listaProductos = (ArrayList<Product>) pf.getProducts();
			ArrayList<Order> listaOrders = (ArrayList<Order>) of.getOrders();
			ArrayList<OrdersProduct> listaOrdersProducts = opf.getOrdersProducts();
			Set<String> proba = new LinkedHashSet<String>();
			String [] a = new String[60];
			int[] array = new int[60];
			
			for(Product p:listaProductos) {
				proba.add(p.getCategoryId());
			}
			for(String s:proba) {
			}
			
			for(int i=0;i<60;i++) {
				array[i]=0;
			}
			for(int i=0;i<12;i++) {
				for(Order o:listaOrders) {
					Calendar c = Calendar.getInstance();
					c.setTimeInMillis(o.getDate().getTime());
					if(i==c.get(Calendar.MONTH)) {
						for(OrdersProduct op:listaOrdersProducts) {
							if(op.getOrder().getId()==o.getId()) {
								for(Product p:listaProductos) {
									if(p.getId()==op.getProduct().getId()) {
										array[(i*5)+p.getWarehouseId()-1] = array[(i*5)+p.getWarehouseId()-1] + op.getQuantity();
									}
								}
							}
						}
					}
				}
			}
			String s = String.valueOf(array[0]);
			for(int i=1;i<60;i++) {
				
				//System.out.println(array[i]);
				s = s + "/" + String.valueOf(array[i]);
			}
			System.out.println("ws"+s);
			out.println(s);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}
	
	@RequestMapping(value="/getOrdersProductsList", method= RequestMethod.GET)
	public void returnHistoricalEarnings(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("getOrdersProduct");
		PrintWriter out=null;
		
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Order> list = (ArrayList<Order>) of.getOrders();
		String s = "";
		int[] aux = new int[12];
		int contador=0;
		for(Order o:list) {
			for(int i = 0;i<12;i++) {
				Calendar c = Calendar.getInstance();
				c.setTimeInMillis(o.getDate().getTime());
				int year = c.get(Calendar.YEAR);
				int month = c.get(Calendar.MONTH);
				if(year==2019) {
					if(month==i) {
						aux[i]=(int) (aux[i]+o.getTotal());
						System.out.println(o.getTotal());
					}
				}
					
			
				
			}
		}
		s=String.valueOf(aux[0]);
		for(int i=1;i<12;i++) {
			s = s + "/" +String.valueOf(aux[i]);
		}
		out.println(s);
		System.out.println("ordersProduct"+s);
	}
	
	@RequestMapping(value="/getUsersInfo", method=RequestMethod.GET)
	public void returnUsersInfo(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("userInfo");
			PrintWriter out = response.getWriter();
			ArrayList<User> userList = uf.getUsers();
			ArrayList<OrdersProduct> ordersProductList = opf.getOrdersProducts();
			ArrayList<Order> orderList = (ArrayList<Order>) of.getOrders();
			String aux = "";
			String fin = "";
			int totalPrice =0;
			int totalOrders =0;
			for(User u:userList) {
				for(OrdersProduct op:ordersProductList) {
					if(u.getId()==op.getOrder().getUserId()) {
						totalPrice=(int) (totalPrice+op.getProduct().getPrice()*op.getQuantity());
					}
				}
				for(Order o:orderList) {
					if(o.getUserId()==u.getId()) {
						System.out.println("order: "+o.getId()+" "+o.getUserId());
						totalOrders++;
					}
				}
				aux = aux + String.valueOf(u.getId())+"/"+u.getFirstName()+" "+u.getSecondName()+"/"+u.getGender()+"/"+String.valueOf(totalOrders)+"/"+String.valueOf(totalPrice);
				fin = fin + aux+",";
				aux="";
				totalPrice=0;
				totalOrders=0;
				
			}
			out.print(fin);
			System.out.println("userInfo"+fin);
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
