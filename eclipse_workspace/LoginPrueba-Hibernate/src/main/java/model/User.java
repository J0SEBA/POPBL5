package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "user")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "user_id")
   private int id;

   @Column(name = "username")
   private String username;

   @Column(name = "password")
   private String password;
   
   @Column(name = "firstname")
   private String firstName;

   @Column(name = "secondname")
   private String secondName;


   public User() {}
   
   public User(String username, String password, String firstName, String lastName) {
      this.id=0;
      this.username=username;
      this.password=password;
	  this.firstName = firstName;
      this.secondName = lastName;
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

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getSecondName() {
	return secondName;
}

public void setSecondName(String secondName) {
	this.secondName = secondName;
}

   
}
