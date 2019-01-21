package simulator;
import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

/**
* This is the main class which has the list of vehicles and the list of segments.
* Both lists objects are initialized in certain points.
*
* @author Joseba Carnicero, Aitor Eizmendi, Jon Mugica and Marcos Azkarate
*/

public class Principal {
	ListaSegmento listaSegmentos = new ListaSegmento();
	ArrayList<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
	Semaphore sql = new Semaphore(1);
	Manager manager = new Manager(listaVehiculos, sql, listaSegmentos.getListaSegmentos());
	Semaphore selectParking = new Semaphore(1);
	
	private final static Logger LOGGER = Logger.getLogger("PriorityFail.simulator.Principal");
	
	public Principal() {
		Vehiculo vehiculo = new Vehiculo(new Point(3,0), new Point(5,0), listaSegmentos.getListaSegmentos(), 1, selectParking, sql, manager);
		Vehiculo vehiculo2 = new Vehiculo(new Point(1,0), new Point(7,0), listaSegmentos.getListaSegmentos(),  2, selectParking, sql, manager);
		Vehiculo vehiculo3 = new Vehiculo(new Point(7,0), new Point(1,0), listaSegmentos.getListaSegmentos(),  3, selectParking, sql, manager);
		Vehiculo vehiculo4 = new Vehiculo(new Point(7,2), new Point(3,2), listaSegmentos.getListaSegmentos(),  4, selectParking, sql, manager);
		Vehiculo vehiculo5 = new Vehiculo(new Point(3,2), new Point(7,2), listaSegmentos.getListaSegmentos(),  5, selectParking, sql, manager);
		
		listaVehiculos.add(vehiculo);
		listaVehiculos.add(vehiculo2);
		listaVehiculos.add(vehiculo3);
		listaVehiculos.add(vehiculo4);
		listaVehiculos.add(vehiculo5);
		
		vehiculo.start();
		vehiculo2.start();
		vehiculo3.start();
		vehiculo4.start();
		vehiculo5.start();
		
		manager.start();
		try {
			vehiculo.join();
			vehiculo2.join();
			vehiculo3.join();
			vehiculo4.join();
			vehiculo5.join();
			System.out.println("Todo hecho");
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			LOGGER.log(LOGGER.getLevel(),e.toString());
		}
	}
	
	
	public static void main(String[] args) {
		Principal principal = new  Principal();

	}
}
