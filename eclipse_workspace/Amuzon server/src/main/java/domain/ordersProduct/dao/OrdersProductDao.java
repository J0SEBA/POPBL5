package main.java.domain.ordersProduct.dao;

import java.util.ArrayList;
import java.util.List;

import main.java.domain.ordersProduct.model.OrdersProduct;

public interface OrdersProductDao {
	public ArrayList<OrdersProduct> getOrdersProducts();
	public List<OrdersProduct> getOrder(int orderId);
}
