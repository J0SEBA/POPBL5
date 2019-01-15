package main.java.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="order")
@Table(name = "orders")
public class Order {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "orderID")
		private int id;
		
		@Column(name = "userID")
		private int userId;
		
		@Column(name = "datee")
		private Date date;
		
		@Column(name = "statee")
		private String state;
		
		@Column(name = "total")
		private float total;
		
		@OneToMany(mappedBy="order",cascade=CascadeType.ALL,orphanRemoval=true)
		private List<OrdersProduct> ordersProduct = new ArrayList<>();
		
		public Order() {}
		
		
		public Order(int id, int userId, Date date, String state, float total) {
				   this.id=id;
				   this.userId=userId;
				   this.date=date;
				   this.state=state;
				   this.total=total;
		}
		
		
		public int getId() {
			   return id;
		}
		
		public void setId(int id) {
			   this.id = id;
		}
		
		public int getUserId() {
			return userId;
		}
		
		public void setUserId(int userId) {
			this.userId = userId;
		}
		
		public Date getDate() {
			return date;
		}
		
		public void setDate(Date date) {
			this.date = date;
		}
		
		public String getState() {
			return state;
		}
		
		public void setState(String state) {
			this.state = state;
		}
		
		public float getTotal() {
			return total;
		}
		
		public void setTotal(float total) {
			this.total = total;
		}
   

}
