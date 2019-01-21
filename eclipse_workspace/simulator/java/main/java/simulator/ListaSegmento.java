package simulator;

import java.awt.Point;
import java.util.ArrayList;

/**
* This is the ListaSegmento class which has an arrayList of segments
* The list initialized all the parkings and workstations
*
* @author Joseba Carnicero, Aitor Eizmendi, Jon Mugica and Marcos Azkarate
*/

public class ListaSegmento {
	ArrayList<Segmento> listaSegmentos;
	
	
	public ListaSegmento() {
		listaSegmentos = new ArrayList<Segmento>();
		init();
	}
	
	/**
	* This method contains an ArrayList of segments
	* 15 segments are initialized and after added to this ArrayList
	*/
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
	}

	public ArrayList<Segmento> getListaSegmentos() {
		return listaSegmentos;
	}

	public void setListaSegmentos(ArrayList<Segmento> listaSegmentos) {
		this.listaSegmentos = listaSegmentos;
	}

	
}
