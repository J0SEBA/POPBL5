package main.java.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderID")
	private int id;
   
	@Column(name = "categoryID")
	private String categoryId;

	@Column(name = "description")
	private String description;
   
	@Column(name = "stock")
	private int stock;
   
	@Column(name = "price")
	private float price;
   
	@Column(name = "warehouseID")
	private int warehouseId;
   
	public Product(int id, String categoryId, String description, int stock, float price, int warehouseId) {
		this.id=id;
		this.categoryId=categoryId;
		this.description=description;
		this.stock=stock;
		this.price=price;
		this.warehouseId=warehouseId;
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
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}
	
	
}
