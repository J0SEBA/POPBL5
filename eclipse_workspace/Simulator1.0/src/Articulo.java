
public class Articulo {
	int articuloId;
	int workstation;
	int cantidad;
	String nombre;
	boolean hecho=false;
	
	public boolean isHecho() {
		return hecho;
	}

	public void setHecho(boolean hecho) {
		this.hecho = hecho;
	}

	public Articulo(int articuloId, int workstation, int cantidad, String nombre) {
		super();
		this.articuloId = articuloId;
		this.workstation = workstation;
		this.cantidad = cantidad;
		this.nombre = nombre;
	}

	public int getArticuloId() {
		return articuloId;
	}

	public void setArticuloId(int articuloId) {
		this.articuloId = articuloId;
	}

	public int getWorkstation() {
		return workstation;
	}

	public void setWorkstation(int workstation) {
		this.workstation = workstation;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
