package com.dao;



import com.model.Login;

import com.model.User;

public class UserFacade {
	UserDao daoUser = null;
	
	public UserFacade(){
		//daoUser = new UserDaoMySQL();
		daoUser = new UserDaoLocal();
	}
	public void register(User user) {
		daoUser.register(user);
	}
	
	public User validateUser(Login login){
		return daoUser.validateUser(login);
	}
	

}