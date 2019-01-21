package simulator;
import java.util.concurrent.Semaphore;

/**
* This is the Worksation class
* The class contains the name, two Semaphores, a state of occupation and a vehicle
*
* @author Joseba Carnicero, Aitor Eizmendi, Jon Mugica and Marcos Azkarate
*/

public class Workstation {
	String nombre;
	Semaphore exit= new Semaphore(0);
	Semaphore entry=new Semaphore(1);
	boolean ocupado= false;
	Vehiculo actual;
	
	public Workstation(String nombre) {
		this.nombre=nombre;
	}


	public Semaphore getExit() {
		return exit;
	}

	public void setExit(Semaphore exit) {
		this.exit = exit;
	}

	public Semaphore getEntry() {
		return entry;
	}

	public void setEntry(Semaphore entry) {
		this.entry = entry;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	public Vehiculo getActual() {
		return actual;
	}

	public void setActual(Vehiculo actual) {
		this.actual = actual;
	}
	
}
