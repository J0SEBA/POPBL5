package dao;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Login;

import model.User;

@Service
public class UserFacade {
	@Autowired
	UserDao daoUser;
	
	
	public UserFacade(){
		//daoUser = new UserDaoMySQL();
		daoUser = new UserDaoMySQL();
	}
	
	 @Transactional
	public void register(User user) {
		daoUser.register(user);
	}
	
	 @Transactional(readOnly = true)
	public User validateUser(Login login){
		return daoUser.validateUser(login);
	}
	

}