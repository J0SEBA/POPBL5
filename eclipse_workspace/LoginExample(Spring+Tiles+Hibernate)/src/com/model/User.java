package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * This is a Java Bean.
 * These are the unique characteristics they must have:
 *     -Default, no-argumented constructor.
 *     -It should be serializable and implement Serializable interface.
 *     -It may have a number of properties which can be read or written.
 *     -"getters" and "setters" for those properties.
 */

@Entity
@Table(name="User")
public class User implements java.io.Serializable /*2nd characteristic*/{
	private static final long serialVersionUID = 3834633934831160740L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	public int user_id = 0;
	@Column(name = "username")
	public String username = null;
	@Column(name = "password")
	public String password = null;
	@Column(name = "firstname")
	public String firstname = null;
	@Column(name = "secondname")
	public String secondname = null;
	
	public User(){}// 1st characteristic

	// 4th characteristic: 'getters' and 'setters'.
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer userId) {
		this.user_id = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstName) {
		this.firstname = firstName;
	}
	public String getSecondname() {
		return secondname;
	}
	public void setSecondname(String secondName) {
		this.secondname = secondName;
	}

	@Override
	public String toString() {
		return "User [userId=" + user_id + "username=" + username + ", password=" + password
			    + ", firstName=" + firstname + ", secondName=" + secondname
			    +"]";
	}
	
	
}