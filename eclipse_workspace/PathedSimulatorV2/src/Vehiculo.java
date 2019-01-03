import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Vehiculo extends Thread{
	Point init;
	Point fin;
	Point extra;
	ArrayList<Segmento> listaSegmentos;
	Segmento actual;
	int id;
	
	public Vehiculo(Point init, Point fin, Point extra, ArrayList<Segmento> listaSegmentos, int id) throws InterruptedException {
		super();
		this.init = init;
		this.fin = fin;
		this.listaSegmentos = listaSegmentos;
		this.id=id;
		this.extra=extra;
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
	
	@Override
	public void run() {
		boolean bool=true;
		int contador=0;
		System.out.println(id+" Empiezo en "+actual.self);
		while(bool) {
			if(actual.self.equals(fin)) {
				System.out.println(id+"Voy por la "+(++contador));
				System.out.println(id+" He llegado ha "+ actual.ws.nombre+"--->"+ actual.self);
				if(!actual.ws.ocupado) {
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
	
	public void entrarWs() {
		try {
			actual.ws.entry.acquire();
			actual.ws.ocupado=true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actual.entry.release();
		try {
			actual.ws.exit.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actual.ws.ocupado=false;
		actual.ws.entry.release();
		fin=extra;
	}
	
	public void entrarNoOcupado() {
		System.out.println(id+" Voy a entrar a "+actual.ws.nombre);
		entrarWs();
	}
	
	public void entrarOcupado() {
		System.out.println(id+" Voy a hechar al que esta y voy a entrat a "+actual.ws.nombre);
		actual.ws.exit.release();
		entrarWs();
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
