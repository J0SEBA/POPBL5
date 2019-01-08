import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Vehiculo extends Thread{
	Point init;
	Point fin;
	Point extra;
	ArrayList<Segmento> listaSegmentos;
	ArrayList<Segmento> listaSegmentosEscape;
	Segmento actual;
	int id;
	Semaphore selectParking;
	String estado = "Working";
	
	public Vehiculo(Point init, Point fin, Point extra, ArrayList<Segmento> listaSegmentos, ArrayList<Segmento> listaSegmentosEscape, int id, Semaphore selectParking) throws InterruptedException {
		super();
		this.init = init;
		this.fin = fin;
		this.listaSegmentos = listaSegmentos;
		this.id=id;
		this.extra=extra;
		this.selectParking = selectParking;
		this.listaSegmentosEscape=listaSegmentosEscape;
		buscarActual();
	}
	
	public void buscarActual() throws InterruptedException {
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
	
	public void buscarParking() {
		try {
			selectParking.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean buscado=false;
		for(int i = 0; i<listaSegmentos.size() && buscado==false;i++) {
			if(listaSegmentos.get(i).ws.nombre.contains("Parking")) {
				if(!listaSegmentos.get(i).ws.ocupado) {
					listaSegmentos.get(i).ws.ocupado=true;
					fin=listaSegmentos.get(i).self;
					buscado=true;
				}
			}
		}
		selectParking.release();
	}
	
	@Override
	public void run() {
		boolean bool=true;
		int contador=0;
		System.out.println(id+" Empiezo en "+actual.self);
		while(bool) {
			if(actual.self.equals(fin)) {
				
				System.out.println(id+"Voy por la "+(++contador));
				System.out.println(id+" He llegado ha "+ actual.ws.nombre+"--->"+ actual.self);
				if(actual.ws.nombre.contains("Parking")) {
					entrarParking();
				}
				else {
					if(!actual.ws.ocupado) {
						entrarNoOcupado();
					} else {
						entrarOcupado();
					}
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
		System.out.println("He entrado ha "+actual.ws.nombre);
		actual.entry.release();
		try {
			estado="Sleeping";
			actual.ws.exit.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void entrarWs(String s) {
		try {
			actual.ws.entry.acquire();
			actual.ws.ocupado=true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(s.equals("NoOcupado")) {
			actual.entry.release();
		}
		//actual.entry.release();
		try {
			estado="Sleeping";
			actual.ws.exit.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actual.ws.ocupado=false;
		
		buscarParking();
		actual.ws.entry.release();
		
	}
	
	public void entrarNoOcupado() {
		System.out.println(id+" Voy a entrar a "+actual.ws.nombre);
		entrarWs(new String("NoOcupado"));
	}
	
	public void entrarOcupado() {
		System.out.println(id+" Voy a hechar al que esta y voy a entrat a "+actual.ws.nombre);
		actual.ws.exit.release();
		entrarWs("");
	}
	
	public boolean tryEnterAlt() {
		boolean done=false;
		try {
			done=actual.alt.entry.tryAcquire(1, TimeUnit.NANOSECONDS);
			System.out.println(done);
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
