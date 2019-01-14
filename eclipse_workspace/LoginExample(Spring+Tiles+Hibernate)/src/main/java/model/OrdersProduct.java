package main.java.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


	@Entity
	@Table(name = "product")
	public class OrdersProduct {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "orderID")
		private int orderId;
	   
		@Id
		@Column(name = "productID")
		private int productId;

		@Column(name = "quantity")
		private int description;
	   
		@Column(name = "isAssigned")
		private boolean isAssigned;
	   
		@Column(name = "prize")
		private float prize;
	   
		@Column(name = "total")
		private float total;

		public OrdersProduct(int orderId, int productId, int description, boolean isAssigned, float prize, float total) {
			this.orderId = orderId;
			this.productId = productId;
			this.description = description;
			this.isAssigned = isAssigned;
			this.prize = prize;
			this.total = total;
		}

		public int getOrderId() {
			return orderId;
		}

		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}

		public int getProductId() {
			return productId;
		}

		public void setProductId(int productId) {
			this.productId = productId;
		}

		public int getDescription() {
			return description;
		}

		public void setDescription(int description) {
			this.description = description;
		}

		public boolean isAssigned() {
			return isAssigned;
		}

		public void setAssigned(boolean isAssigned) {
			this.isAssigned = isAssigned;
		}

		public float getPrize() {
			return prize;
		}

		public void setPrize(float prize) {
			this.prize = prize;
		}

		public float getTotal() {
			return total;
		}

		public void setTotal(float total) {
			this.total = total;
		}
	   
		
}
