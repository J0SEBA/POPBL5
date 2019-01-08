import java.awt.Point;
import java.util.concurrent.Semaphore;

public class Segmento {
	Point self;
	Segmento alt;
	Segmento next;
	boolean ocupado = false;
	Semaphore entry = new Semaphore(1);
	Sitio sitio;
	
	public Segmento(Point self, Sitio sitio) {
		this.self=self;
		this.sitio=sitio;
	}
}
