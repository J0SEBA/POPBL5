import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Principal {
	
	ArrayList<Segmento> listaSegmentos=new ArrayList<Segmento>();
	Segmento actual;
	Objetivo objetivo=new Objetivo(new Point(1,2), new Point(1,0));
	
	public Principal() {
		init();
		go();
	}
	
	public void go() {
		boolean bool=true;
		System.out.println("Empiezo en "+actual.self);
		while(bool) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(objetivo.fin.equals(actual.self)) {
				bool=false;
				System.out.println("He llegado, estoy en "+actual.item);
				System.out.println(actual.self);
				break;
			}
			if(actual.self.getY()==2) {
				if(actual.self.getX()>=objetivo.fin.getX()) {
					if(actual.alt!=null) {
						actual=actual.alt;
						System.out.println("Estoy en "+ actual.self);
					} else {
						actual=actual.next;
						System.out.println("Estoy en "+ actual.self);
					}
				} else {
					actual=actual.next;
					System.out.println("Estoy en "+ actual.self);
				}
			} else if(actual.self.getY()==0) {
				if(actual.self.getX()<=objetivo.fin.getX()) {
					if(actual.alt!=null) {
						actual=actual.alt;
						System.out.println("Estoy en "+ actual.self);
					} else {
						actual=actual.next;
						System.out.println("Estoy en "+ actual.self);
					}
				} else {
					actual=actual.next;
					System.out.println("Estoy en "+ actual.self);
				}
			} else {
				actual=actual.next;
				System.out.println("Estoy en "+ actual.self);
			}
			
		}
	}
	
	public void init() {
		listaSegmentos.add(new Segmento(new Point(1,2), "WS4"));
		listaSegmentos.add(new Segmento(new Point(3,2),"Parking"));
		listaSegmentos.add(new Segmento(new Point(5,2), "WS5"));
		listaSegmentos.add(new Segmento(new Point(7,2), "Parking"));
		listaSegmentos.add(new Segmento(new Point(9,2), "WS6"));
		
		
		listaSegmentos.add(new Segmento(new Point(9,0),"WS1"));
		listaSegmentos.add(new Segmento(new Point(7,0),"Parking"));
		listaSegmentos.add(new Segmento(new Point(5,0),"WS2"));
		listaSegmentos.add(new Segmento(new Point(3,0),"Parking"));
		listaSegmentos.add(new Segmento(new Point(1,0),"WS3"));
		
		
		listaSegmentos.add(new Segmento(new Point(0,1),null));
		listaSegmentos.add(new Segmento(new Point(2,1),null));
		listaSegmentos.add(new Segmento(new Point(4,1),null));
		listaSegmentos.add(new Segmento(new Point(6,1),null));
		listaSegmentos.add(new Segmento(new Point(8,1),null));
		listaSegmentos.add(new Segmento(new Point(10,1),null));
		
		
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
		
		
		for(int i=0;i<listaSegmentos.size();i++) {
			if(listaSegmentos.get(i).self.equals(objetivo.init)) {
				actual=listaSegmentos.get(i);
			}
		}
		
	}
	
	public class Objetivo extends Thread{
		Point init;
		Point fin;
		
		public Objetivo(Point init, Point fin) {
			super();
			this.init = init;
			this.fin = fin;
		}
	}
	
	public class Segmento{
		Point self;
		Segmento next;
		Segmento alt=null;
		String item;
		
		public Segmento(Point self, String item) {
			this.self=self;
			this.item=item;
		}

		public Point getSelf() {
			return self;
		}

		public void setSelf(Point self) {
			this.self = self;
		}

		public Segmento getNext() {
			return next;
		}

		public void setNext(Segmento next) {
			this.next = next;
		}

		public Segmento getAlt() {
			return alt;
		}

		public void setAlt(Segmento alt) {
			this.alt = alt;
		}
	}
	
	public class Workstation{
		Semaphore exit=new Semaphore(0);
		boolean ocupado=false;
		public Workstation() {
			
		}
	}

	public static void main(String[] args) {
		Principal principal=new Principal();

	}

}
