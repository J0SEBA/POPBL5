import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Vehiculo extends Thread{
	Point init;
	Point fin;
	ArrayList<Segmento> listaSegmentos;
	ArrayList<Segmento> listaSegmentosEscape;
	Segmento actual;
	int id;
	Semaphore selectParking;
	Semaphore wakeUp = new Semaphore(0);
	String estado = "Working";
	
	public Vehiculo(Point init, Point fin, 
			ArrayList<Segmento> listaSegmentos, ArrayList<Segmento> listaSegmentosEscape,
			int id, Semaphore selectParking){
		super();
		this.init = init;
		this.fin=fin;
		this.listaSegmentos=listaSegmentos;
		this.listaSegmentosEscape=listaSegmentosEscape;
		this.selectParking=selectParking;
		this.id=id;
		buscarActual();
	}
	
	public void buscarActual(){
		for(int i=0;i<listaSegmentos.size();i++) {
			if(listaSegmentos.get(i).self.equals(init)) {
				actual=listaSegmentos.get(i);
				try {
					actual.entry.acquire();
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void run() {
		boolean bool=true;
		int contador=0;
		System.out.println(id+" Empiezo en "+actual.self);
		while(bool) {
			if(actual.self.equals(fin)) {
				System.out.println(id+"Voy por la "+(++contador));
				System.out.println(id+" He llegado ha "+ actual.sitio.nombre+"--->"+ actual.self);
				if(!actual.sitio.ocupado) {
					entrarNoOcupado();
				} else {
					entrarOcupado();
				}
			}
			if(actual.self.getY()==2) {
				if(actual.self.getX()>=fin.getX()) {
					if(actual.alt!=null) {
						if(tryEnterAlt()) {
							enterAlt();
						} else {
							System.out.println(id+" No he posido entrar a "+actual.alt.self);
							enterNext();
						}
					} else {
						enterNext();
					}
				} else {
					enterNext();
				}
			} else if(actual.self.getY()==0) {
				if(actual.self.getX()<=fin.getX()) {
					if(actual.alt!=null) {
						if(tryEnterAlt()) {
							enterAlt();
						} else {
							System.out.println(id+" No he posido entrar a "+actual.alt.self);
							enterNext();
						}
					} else {
						enterNext();
					}
				} else {
					enterNext();
				}
			} else {
				enterNext();
			}
		}
	}
	
	public void entrarParking() {
		System.out.println("He entrado ha "+actual.sitio.nombre);
		actual.entry.release();
		try {
			actual.sitio.exit.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void entrarsitio() {
		try {
			actual.sitio.entry.acquire();
			actual.sitio.ocupado=true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actual.entry.release();
		try {
			actual.sitio.vehiculo=this;
			this.estado="sleeping";
			this.wakeUp.acquire();
			//actual.sitio.exit.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actual.sitio.ocupado=false;
		actual=actual.sitio.escape;
		fin = init;
		actual.sitio.entry.release();
		
	}
	
	
	
	public void entrarNoOcupado() {
		System.out.println(id+" Voy a entrar a "+actual.sitio.nombre);
		entrarsitio();
	}
	
	public void entrarOcupado() {
		System.out.println(id+" Voy a hechar al que esta y voy a entrat a "+actual.sitio.nombre);
		actual.sitio.vehiculo.wakeUp.release();
		entrarsitio();
	}
	
	public boolean tryEnterAlt() {
		boolean done=false;
		try {
			done=actual.alt.entry.tryAcquire(1, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return done;
	}
	
	public void enterAlt() {
		Segmento aux=actual;
		System.out.println(id+" Estoy en "+ actual.self);
		actual=actual.alt;
		aux.entry.release();
	}
	
	public void enterNext() {
		Segmento aux= actual;
		System.out.println(id+" Estoy en "+ actual.self);
		try {
			actual.next.entry.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actual=actual.next;
		aux.entry.release();
	}
}
