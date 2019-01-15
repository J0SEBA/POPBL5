package main.java.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity(name="product")
@Table(name = "product")
public class Product {

	@Id
	@Column(name = "productID")
	private int id;
   
	
	@Column(name = "categoryID")
	private String categoryId;

	@Column(name = "description")
	private String description;
   
	@Column(name = "stock")
	private int stock;
   
	@Column(name = "price")
	private float price;
   
	@Column(name = "workstationID")
	private int workstationId;
   
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<OrdersProduct> ordersProduct = new ArrayList<>();
	
	public Product() {}

	public Product(int id, String categoryId, String description, int stock, float price, int workstationId,
			List<OrdersProduct> ordersProduct) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.description = description;
		this.stock = stock;
		this.price = price;
		this.workstationId = workstationId;
		this.ordersProduct = ordersProduct;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	public int getWarehouseId() {
		return workstationId;
	}

	public void setWarehouseId(int warehouseId) {
		this.workstationId = warehouseId;
	} 
}
