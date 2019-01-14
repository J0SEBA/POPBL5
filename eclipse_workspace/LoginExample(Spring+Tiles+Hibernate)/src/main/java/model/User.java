package main.java.model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "userID")
   private int id;
   
   @Column(name = "name")
   private String firstName;

   @Column(name = "surname")
   private String secondName;
   
   @Column(name = "userName")
   private String username;

   @Column(name = "email")
   private String email;
   
   @Column(name = "password")
   private String password;
   
   @Column(name = "gender")
   private String gender;
   
   @Column(name = "born_date")
   private Date birthDate;
      /*@Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "userID")
      private int id
      ;
      
      @Column(name = "name")
      private String firstName;

      @Column(name = "surname")
      private String secondname;
      
      @Column(name = "userName")
      private String username;
      
      @Column(name = "email")
      private String email;
      
      @Column(name = "password")
      private String password;
      
      public User(String firstname, String secondname, String username, String email, String password) {
      this.id=0;
      this.firstName = firstname;
      this.secondname = secondname;
      this.username=username;
      this.email=email;
      this.password=password;
   }*/

   public User() {}


   
  public User(String username, String password, String firstName, String lastName,String email,String gender,String birthDate) {
      //this.id=0;
      this.username=username;
      this.password=password;
	  this.firstName = firstName;
      this.secondName = lastName;
      this.email=email;
      this.gender=gender;
      SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyyy"); 
      try {
		this.birthDate=new java.sql.Date(format.parse(birthDate).getTime());
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }



public int getId() {
	return id;
}



public void setId(int id) {
	this.id = id;
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



public String getUsername() {
	return username;
}



public void setUsername(String username) {
	this.username = username;
}



public String getEmail() {
	return email;
}



public void setEmail(String email) {
	this.email = email;
}



public String getPassword() {
	return password;
}



public void setPassword(String password) {
	this.password = password;
}



public String getGender() {
	return gender;
}



public void setGender(String gender) {
	this.gender = gender;
}



public Date getBirthDate() {
	return birthDate;
}



public void setBirthDate(String birthDate) {
	SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyyy"); 
    try {
		this.birthDate=new java.sql.Date(format.parse(birthDate).getTime());
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

   
   



   
}
