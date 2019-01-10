package main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Principal {

	public class Parking{
		String nombre;
		boolean ocupado;
	}
	

	
	ArrayList<Segmento> listaSegmentos =new ArrayList<Segmento>();
	ArrayList<Segmento> listaSegmentosEscape = new ArrayList<Segmento>();
	ArrayList<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
	Semaphore sql = new Semaphore(1);
	Manager manager = new Manager(listaVehiculos, sql, listaSegmentos);
	Producer producer = new Producer(sql);
	Semaphore selectParking = new Semaphore(1);
	static int i=0;
	public Principal() throws InterruptedException {
		init();
		Vehiculo vehiculo = new Vehiculo(new Point(3,0), new Point(3,0), new Point(5,0), listaSegmentos, listaSegmentosEscape, 1, selectParking);
		Vehiculo vehiculo2 = new Vehiculo(new Point(1,0), new Point(1,0), new Point(3,0), listaSegmentos, listaSegmentosEscape, 2, selectParking);
		Vehiculo vehiculo3 = new Vehiculo(new Point(7,0), new Point(7,0), new Point(7,0), listaSegmentos, listaSegmentosEscape, 3, selectParking);
		Vehiculo vehiculo4 = new Vehiculo(new Point(7,2), new Point(7,2), new Point(9,0), listaSegmentos, listaSegmentosEscape, 4, selectParking);
		Vehiculo vehiculo5 = new Vehiculo(new Point(9,2), new Point(9,2), new Point(9,2), listaSegmentos, listaSegmentosEscape, 5, selectParking);
		
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
		
		producer.start();
		manager.start();
		try {
			vehiculo.join();
			vehiculo2.join();
			vehiculo3.join();
			vehiculo4.join();
			vehiculo5.join();
			System.out.println("Todo hecho");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void init() {
		listaSegmentos.add(new Segmento(new Point(1,2), new Workstation("WS4")));
		listaSegmentos.add(new Segmento(new Point(3,2),new Workstation("Parking1")));
		listaSegmentos.add(new Segmento(new Point(5,2), new Workstation("WS5")));
		listaSegmentos.add(new Segmento(new Point(7,2), new Workstation("Parking2")));
		listaSegmentos.add(new Segmento(new Point(9,2), new Workstation("WS6")));
		
		
		listaSegmentos.add(new Segmento(new Point(9,0), new Workstation("WS1")));
		listaSegmentos.add(new Segmento(new Point(7,0),new Workstation("Parking3")));
		listaSegmentos.add(new Segmento(new Point(5,0),new Workstation("WS2")));
		listaSegmentos.add(new Segmento(new Point(3,0),new Workstation("Parking4")));
		listaSegmentos.add(new Segmento(new Point(1,0),new Workstation("WS3")));
		
		
		listaSegmentos.add(new Segmento(new Point(0,1),new Workstation("Abajo1")));
		listaSegmentos.add(new Segmento(new Point(2,1),new Workstation("Arriba1")));
		listaSegmentos.add(new Segmento(new Point(4,1),new Workstation("Abajo2")));
		listaSegmentos.add(new Segmento(new Point(6,1),new Workstation("Arriba2")));
		listaSegmentos.add(new Segmento(new Point(8,1),new Workstation("Abajo3")));
		listaSegmentos.add(new Segmento(new Point(10,1),new Workstation("Arriba3")));
		
		
		listaSegmentos.get(0).next=listaSegmentos.get(1);
		listaSegmentos.get(1).next=listaSegmentos.get(2);
		listaSegmentos.get(2).next=listaSegmentos.get(3);
		listaSegmentos.get(3).next=listaSegmentos.get(4);
		listaSegmentos.get(4).next=listaSegmentos.get(15);
		listaSegmentos.get(15).next=listaSegmentos.get(5);
		listaSegmentos.get(5).next=listaSegmentos.get(6);
		listaSegmentos.get(6).next=listaSegmentos.get(7);
		listaSegmentos.get(7).next=listaSegmentos.get(8);
		listaSegmentos.get(8).next=listaSegmentos.get(9);
		listaSegmentos.get(9).next=listaSegmentos.get(10);
		listaSegmentos.get(10).next=listaSegmentos.get(0);
		listaSegmentos.get(11).next=listaSegmentos.get(9);
		listaSegmentos.get(12).next=listaSegmentos.get(2);
		listaSegmentos.get(13).next=listaSegmentos.get(7);
		listaSegmentos.get(14).next=listaSegmentos.get(4);
		
		
		listaSegmentos.get(0).alt=listaSegmentos.get(11);
		listaSegmentos.get(2).alt=listaSegmentos.get(13);
		listaSegmentos.get(5).alt=listaSegmentos.get(14);
		listaSegmentos.get(7).alt=listaSegmentos.get(12);
		
		listaSegmentosEscape.add(new Segmento(new Point(), new Workstation("EscapeWS4")));
		listaSegmentosEscape.add(new Segmento(null, new Workstation("EscapeParking1")));
		listaSegmentosEscape.add(new Segmento(null, new Workstation("EscapeWS5")));
		listaSegmentosEscape.add(new Segmento(null, new Workstation("EscapeParking2")));
		listaSegmentosEscape.add(new Segmento(null, new Workstation("EscapeWS6")));
		listaSegmentosEscape.add(new Segmento(null, new Workstation("EscapeWS1")));
		listaSegmentosEscape.add(new Segmento(null, new Workstation("EscapeParking3")));
		listaSegmentosEscape.add(new Segmento(null, new Workstation("EscapeWS2")));
		listaSegmentosEscape.add(new Segmento(null, new Workstation("EscapeParking4")));
		listaSegmentosEscape.add(new Segmento(null, new Workstation("EscapeWS3")));
		
		listaSegmentosEscape.get(0).next=listaSegmentos.get(1);
		listaSegmentosEscape.get(1).next=listaSegmentos.get(2);
		listaSegmentosEscape.get(2).next=listaSegmentos.get(3);
		listaSegmentosEscape.get(3).next=listaSegmentos.get(4);
		listaSegmentosEscape.get(4).next=listaSegmentos.get(15);
		listaSegmentosEscape.get(5).next=listaSegmentos.get(6);
		listaSegmentosEscape.get(6).next=listaSegmentos.get(7);
		listaSegmentosEscape.get(7).next=listaSegmentos.get(8);
		listaSegmentosEscape.get(8).next=listaSegmentos.get(9);
		listaSegmentosEscape.get(9).next=listaSegmentos.get(10);
		
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		Principal principal = new Principal();
	}

}
