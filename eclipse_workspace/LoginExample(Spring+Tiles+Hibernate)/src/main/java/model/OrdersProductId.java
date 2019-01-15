package main.java.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class OrdersProductId  implements Serializable{

	@Column(name="orderID")
	private int orderId;
	
	@Column(name="productID")
	private int productId;
	
	
	public OrdersProductId() {}
	
	public OrdersProductId(int orderId, int productId) {
		this.orderId = orderId;
		this.productId = productId;
	}


	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderid) {
		this.orderId = orderid;
	}

	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	 
	        if (o == null || getClass() != o.getClass())
	            return false;
	 
	        OrdersProductId that = (OrdersProductId) o;
	        return Objects.equals(orderId, that.orderId) &&
	               Objects.equals(productId, that.productId);
	    }
	 
	    @Override
	    public int hashCode() {
	        return Objects.hash(orderId, productId);
	    }
}
