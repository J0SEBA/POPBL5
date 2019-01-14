package main.java.dao;


import main.java.model.Login;
import main.java.model.Operator;
import main.java.model.User;

public interface UserDao {
  public void register(User user);
  public User validateUser(Login login);
  public Operator validateOperator(Login login);
}
