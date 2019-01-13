package oangua;

import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import oangua.Segmento;

public class Vehiculo extends Thread{
	
	Point init;
	Point fin;
	Point objetivo;
	Point parking;
	ArrayList<Segmento> listaSegmentos;
	Segmento actual;
	int id;
	Semaphore selectParking;
	String estado= "Working";
	Segmento salida;
	
	public Vehiculo(Point init, Point fin, ArrayList<Segmento> listaSegmentos, int id, Semaphore selectParking) {
		super();
		this.init = init;
		this.fin = fin;
		objetivo= new  Point(0,0);
		this.listaSegmentos = listaSegmentos;
		this.id=id;
		this.selectParking = selectParking;
		salida=listaSegmentos.get(4);
		buscarActual();
		
	}
	
	public void buscarActual() {
		for(int i=0;i<listaSegmentos.size();i++) {
			if(listaSegmentos.get(i).self.equals(init)) {
				actual=listaSegmentos.get(i);
				parking=listaSegmentos.get(i).self;
				fin=parking;
				actual.ws.ocupado=true;
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
					parking=listaSegmentos.get(i).self;
					fin=parking;
					System.out.println(id+"----Buscado parking"+listaSegmentos.get(i).ws.nombre+fin);
					buscado=true;
				}
			}
		}
		if(buscado==false) {
			System.out.println("Hay un problema");
		}
		selectParking.release();
	}
	
	public void mover() {
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
	
	public void entrarParking() {
		actual.entry.release();
		System.out.println(id+" Voy a dormir en "+actual.ws.nombre+actual.self);
		try {
			actual.ws.exit.acquire();
			System.out.println(id+" Me han despertado para hacer un trabajo"+actual.self);
			actual.entry.acquire();
			actual.ws.ocupado=false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean llegada() {
		if(actual.self.equals(objetivo)) {
			
			try {
				actual.ws.entry.acquire();
				System.out.println(id+": He llegado al almacen---->"+objetivo);
				actual.ws.entry.release();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			fin=salida.self;
		} else if(actual.self.equals(salida.self)) {
			System.out.println(id+": He llegado ha salida----->"+salida.self);
			if(salida.ws.ocupado) {
				salida.ws.exit.release();
				try {
					salida.ws.entry.acquire();
					salida.ws.ocupado=true;
					System.out.println(id+" He entregado el producto");
					salida.ws.exit.acquire();
					System.out.println("################"+id+"Me han hecho salir");
					buscarParking();
					salida.ws.ocupado=false;
					salida.ws.entry.release();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
				try {
					salida.ws.entry.acquire();
					salida.entry.release();
					salida.ws.ocupado=true;
					System.out.println(id+" He entregado el producto");
					salida.ws.exit.acquire();
					System.out.println("################"+id+"Me han hecho salir");
					buscarParking();
					salida.ws.ocupado=false;
					salida.ws.entry.release();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} else if(actual.self.equals(parking)) {
			entrarParking();
		}
		
		return true;
	}
	
	@Override
	public void run() {
		boolean bool = true;
		while(bool) {
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(actual.self.equals(fin)) {
				//System.out.println(id+" He llegado ha "+ actual.ws.nombre+"--->"+ actual.self);
				llegada();
				//fin = salida.self;
				//break;
				
			} else {
				mover();
			}
		}
	}
	

	public boolean tryEnterAlt() {
		boolean done=false;
		try {
			done=actual.alt.entry.tryAcquire(1, TimeUnit.NANOSECONDS);
			//System.out.println(done);
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
