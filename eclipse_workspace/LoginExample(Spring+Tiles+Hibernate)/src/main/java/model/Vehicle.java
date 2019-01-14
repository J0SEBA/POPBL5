package main.java.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vehicles")
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vehicleID")
	private int id;
	
	@Column(name = "productID")
	private int productIdd;
	
	@Column(name = "statee")
	private boolean state;
	
	@Column(name = "position")
	private String position;
	
	@Column(name = "velocity")
	private float velocity;
	
	public Vehicle(int id, int productId, boolean state, String position, float velocity) {
		this.id=id;
		this.state=state;
		this.position=position;
		this.velocity=velocity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductIdd() {
		return productIdd;
	}

	public void setProductIdd(int productIdd) {
		this.productIdd = productIdd;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public float getVelocity() {
		return velocity;
	}

	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}
	
	
}
