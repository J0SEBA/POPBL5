package main.java.domain.user.dao;


import java.util.ArrayList;
import java.util.List;

import main.java.domain.login.model.Login;
import main.java.domain.user.model.User;

public interface UserDao {
	public void register(User user);
	public User validateUser(Login login);
	public ArrayList<User> getUsers();
	public List<String> getUserUsernames();
  
}

