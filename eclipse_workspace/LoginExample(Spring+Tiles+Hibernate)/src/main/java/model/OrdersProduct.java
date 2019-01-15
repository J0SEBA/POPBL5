package main.java.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;


	@Entity(name = "orders_product")
	@Table(name = "orders_product")
	public class OrdersProduct{

		@EmbeddedId
		private OrdersProductId id;

		@ManyToOne(fetch=FetchType.LAZY)
		@MapsId("orderId")
		private Order order;
		
		@ManyToOne(fetch=FetchType.LAZY)
		@MapsId("productId")
		private Product product;
		
		@Column(name="quantity")
		private int quantity;
		 	   
		@Column(name="price")
		private float price;
		  
		@Column(name="remaining")
		private int remaining;

		public OrdersProduct() {}

		public OrdersProduct(OrdersProductId id, Order order, Product product, int quantity, float price,
				int remaining) {
			this.id = id;
			this.order = order;
			this.product = product;
			this.quantity = quantity;
			this.price = price;
			this.remaining = remaining;
		}

		public OrdersProductId getId() {
			return id;
		}

		public void setId(OrdersProductId id) {
			this.id = id;
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

		public int getRemaining() {
			return remaining;
		}

		public void setRemaining(int remaining) {
			this.remaining = remaining;
		}
		   
	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	 
	        if (o == null || getClass() != o.getClass())
	            return false;
	 
	        OrdersProduct that = (OrdersProduct) o;
	        return Objects.equals(order, that.order) &&
	               Objects.equals(product, that.product);
	    }
	 
	    @Override
	    public int hashCode() {
	        return Objects.hash(order, product);
	    }
		
}
