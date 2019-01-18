package main.java.domain.vehicle.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "vehicle")
@Table(name = "vehicles")
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vehicleID")
	private int id;
	
	@Column(name = "statee")
	private String state;
	
	@Column(name = "position")
	private String position;
	
	@Column(name = "velocity")
	private float velocity;
	
	@Column(name = "timesUsed")
	private float timesUsed;
	
	@Column(name = "segmentCounter")
	private int segmentCounter;
	
	public int getSegmentCounter() {
		return segmentCounter;
	}

	public void setSegmentCounter(int segmentCounter) {
		this.segmentCounter = segmentCounter;
	}

	public Vehicle() {}
	
	public Vehicle(int id, String state, String position, float velocity, int timesUsed) {
		this.id=id;
		this.state=state;
		this.position=position;
		this.velocity=velocity;
		this.timesUsed=timesUsed;
	}

	public float getTimesUsed() {
		return timesUsed;
	}

	public void setTimesUsed(float timesUsed) {
		this.timesUsed = timesUsed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
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
