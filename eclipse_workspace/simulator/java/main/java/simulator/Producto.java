package simulator;

/**
* This is the main class which has the list of vehicles and the list of segments.
* Both lists objects are initialized in certain points.
*
* @author Joseba Carnicero, Aitor Eizmendi, Jon Mugica and Marcos Azkarate
*/

public class Producto {
	String nombre;
	int cantidad;
	int wsID;
	int productID;
	int remaining=0;
	
	public Producto(String nombre, int cantidad, int wsID, int productID) {
		super();
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.wsID=wsID;
		this.productID = productID;
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getWsID() {
		return wsID;
	}

	public void setWsID(int wsID) {
		this.wsID = wsID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getRemaining() {
		return remaining;
	}

	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}

	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", cantidad=" + cantidad + ", wsID=" + wsID + "]";
	}
}
