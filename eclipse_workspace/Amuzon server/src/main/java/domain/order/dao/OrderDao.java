package main.java.domain.order.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.java.domain.order.model.Order;

public interface OrderDao {
	public ArrayList<Order> getOrders();
	  public List<Order> getCurrentOrders(int userId);
	  public List<Order> getPreviousOrders(int userId);
	  public void registerOrder(Map<Integer, Integer> cart,int userId,float total);
	  
}
