import java.util.concurrent.Semaphore;

public class Workstation {
	String nombre;
	Semaphore exit= new Semaphore(0);
	boolean ocupado= false;
	
	public Workstation(String nombre) {
		this.nombre=nombre;
	}
}