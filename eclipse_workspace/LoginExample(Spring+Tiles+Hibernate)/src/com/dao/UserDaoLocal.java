package com.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.model.Login;
import com.model.User;

public class UserDaoLocal implements UserDao{

	List<User> users;
	final String filename="users.dat";
	
	
	public UserDaoLocal() {
		
		this.users = new ArrayList<>();
		/*User user=new User();
		user.setUsername("admin");
		user.setPassword("admin");
		user.setFirstname("joseba");
		user.setSecondname("carnicero");
		users.add(user);*/
		try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream(new File(filename)))){
			
			users=(ArrayList<User>) oi.readObject();
			oi.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void register(User user) {
		users.add(user);
		for(User u:users) System.out.println(u.getFirstname());
		
		try (ObjectOutputStream  o = new ObjectOutputStream (new FileOutputStream (new File(filename)))){
			
			o.writeObject(users);
			o.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public User validateUser(Login login) {
		try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream(new File(filename)))){
			
			users=(ArrayList<User>) oi.readObject();
			oi.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(User u:users) {
			if(login.getUsername().equals(u.getUsername())&&login.getPassword().equals(u.getPassword())) {
				return u;
			}
		}
		return null;
	}

}
