
public class Cliente {
	String nombre;
	int clienteId;
	public Cliente(String nombre, int clienteId) {
		super();
		this.nombre = nombre;
		this.clienteId = clienteId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getClienteId() {
		return clienteId;
	}
	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}
	
	
}
