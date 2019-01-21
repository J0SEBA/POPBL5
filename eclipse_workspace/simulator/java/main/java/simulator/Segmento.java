package simulator;
import java.awt.Point;
import java.util.concurrent.Semaphore;

/**
* This is the Segment class.
* Segment class contains the actual position, next segment, alt segment, a semaphore and workstation
*
* @author Joseba Carnicero, Aitor Eizmendi, Jon Mugica and Marcos Azkarate
*/

public class Segmento {
	Point self;
	public Segmento alt;
	public Segmento next;
	boolean ocupado=false;
	Semaphore entry=new Semaphore(1);
	Workstation ws;
	
	public Segmento(Point self, Workstation ws) {
		this.ws=ws;
		this.self=self;
	}

	public Point getSelf() {
		return self;
	}

	public void setSelf(Point self) {
		this.self = self;
	}

	public Segmento getAlt() {
		return alt;
	}

	public void setAlt(Segmento alt) {
		this.alt = alt;
	}

	public Segmento getNext() {
		return next;
	}

	public void setNext(Segmento next) {
		this.next = next;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	public Semaphore getEntry() {
		return entry;
	}

	public void setEntry(Semaphore entry) {
		this.entry = entry;
	}

	public Workstation getWs() {
		return ws;
	}

	public void setWs(Workstation ws) {
		this.ws = ws;
	}
	
}
