package main.java.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "warehouseOperator")
public class Operator {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "operatorID")
	private int id;
	
	@Column(name = "name")
	private String firstname;
	
	@Column(name = "surname")
	private String secondname;
	
	@Column(name = "userName")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "isManager")
	private boolean isManager;
	
	public Operator() {}
	
	public Operator(String firstname, String secondname, String username, String password, Boolean isManager) {
		
		this.firstname = firstname;
		this.secondname = secondname;
		this.username=username;
		this.password=password;
		this.isManager=isManager;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSecondname() {
		return secondname;
	}

	public void setSecondname(String secondname) {
		this.secondname = secondname;
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

	public boolean isManager() {
		return isManager;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	
	
}
