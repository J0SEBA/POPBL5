import java.awt.Point;
import java.util.ArrayList;

public class Principal {

	ArrayList<Segmento> listaSegmentos =new ArrayList<Segmento>();
	int i=0;
	public Principal() throws InterruptedException {
		init();
		Vehiculo vehiculo = new Vehiculo(new Point(5,0), new Point(1,2), new Point(5,0), listaSegmentos, 1);
		Vehiculo vehiculo2 = new Vehiculo(new Point(3,0), new Point(1,2), new Point(3,0), listaSegmentos, 2);
		Vehiculo vehiculo3 = new Vehiculo(new Point(7,0), new Point(1,2), new Point(7,0), listaSegmentos, 3);
		Vehiculo vehiculo4 = new Vehiculo(new Point(9,0), new Point(1,2), new Point(9,0), listaSegmentos, 4);
		Vehiculo vehiculo5 = new Vehiculo(new Point(9,2), new Point(1,2), new Point(9,2), listaSegmentos, 5);
		
		vehiculo.start();
		vehiculo2.start();
		vehiculo3.start();
		vehiculo4.start();
		vehiculo5.start();
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
		listaSegmentos.add(new Segmento(new Point(3,2),new Workstation("Parking")));
		listaSegmentos.add(new Segmento(new Point(5,2), new Workstation("WS5")));
		listaSegmentos.add(new Segmento(new Point(7,2), new Workstation("Parking")));
		listaSegmentos.add(new Segmento(new Point(9,2), new Workstation("WS6")));
		
		
		listaSegmentos.add(new Segmento(new Point(9,0), new Workstation("WS1")));
		listaSegmentos.add(new Segmento(new Point(7,0),new Workstation("Parking")));
		listaSegmentos.add(new Segmento(new Point(5,0),new Workstation("WS2")));
		listaSegmentos.add(new Segmento(new Point(3,0),new Workstation("Parking")));
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
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		Principal principal = new Principal();
	}

}
