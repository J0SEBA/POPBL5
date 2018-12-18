package com.dao;


import com.model.Login;
import com.model.User;

public interface UserDao {
  public void register(User user);
  public User validateUser(Login login);
}
