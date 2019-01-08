import java.util.concurrent.Semaphore;

public class Sitio {
	String nombre;
	Semaphore exit= new Semaphore(0);
	Semaphore entry = new Semaphore(1);
	boolean ocupado = false;
	Vehiculo vehiculo;
	Segmento escape;
	
	public Sitio(String nombre) {
		this.nombre=nombre;
	}
}
