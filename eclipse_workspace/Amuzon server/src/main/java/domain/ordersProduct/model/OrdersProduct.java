package main.java.domain.ordersProduct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import main.java.domain.order.model.Order;
import main.java.domain.product.model.Product;


	@Entity(name = "orders_product")
	@Table(name = "orders_product")
	public class OrdersProduct implements Serializable{

		@Id
		@ManyToOne
		@JoinColumn(name="orderID",nullable=false)
		private Order order;
		
		@Id
		@ManyToOne
		@JoinColumn(name="productID",nullable=false)
		private Product product;
		
		@Column(name="quantity")
		private int quantity;
		 	   
		@Column(name="price")
		private float price;

		public OrdersProduct() {}

		public OrdersProduct(Order order, Product product, int quantity, float price) {
			this.order = order;
			this.product = product;
			this.quantity = quantity;
			this.price = price;
		}

		

		public Order getOrder() {
			return order;
		}

		public void setOrder(Order order) {
			this.order = order;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public float getPrice() {
			return price;
		}

		public void setPrice(float price) {
			this.price = price;
		}
		   
	  
		
}
