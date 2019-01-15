package main.java.dao;


import java.util.List;

import main.java.model.Login;
import main.java.model.Operator;
import main.java.model.Order;
import main.java.model.OrdersProduct;
import main.java.model.Product;
import main.java.model.User;

public interface UserDao {
  public void register(User user);
  public User validateUser(Login login);
  public Operator validateOperator(Login login);
  public List<Product> getProductsByCategory(String category);
  public List<OrdersProduct> getCurrentOrder(int userId);
  public String getProductDescription(int productId);
}
