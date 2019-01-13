package oangua;

public class Producto {
	String nombre;
	int cantidad;
	int wsID;
	
	public Producto(String nombre, int cantidad, int wsID) {
		super();
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.wsID=wsID;
	}

	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", cantidad=" + cantidad + ", wsID=" + wsID + "]";
	}

	
	
	
}
