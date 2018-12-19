import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Vehiculo extends Thread{
	Point init;
	Point fin;
	ArrayList<Segmento> listaSegmentos;
	Segmento actual;
	int id;
	
	public Vehiculo(Point init, Point fin, ArrayList<Segmento> listaSegmentos, int id) {
		super();
		this.init = init;
		this.fin = fin;
		this.listaSegmentos = listaSegmentos;
		this.id=id;
		buscarActual();
	}
	
	public void buscarActual() {
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
		System.out.println(id+" Empiezo en "+actual.self);
		while(bool) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(fin.equals(actual.self)) {
				bool=false;
				System.out.println(id+"He llegado, estoy en "+actual.ws.nombre);
				System.out.println(actual.self);
				actual.entry.release();
				break;
			}
			if(actual.self.getY()==2) {
				if(actual.self.getX()>=fin.getX()) {
					if(actual.alt!=null) {
						
						try {
							boolean temp=actual.alt.entry.tryAcquire(3000, TimeUnit.MILLISECONDS);
							if(!temp) {
								actual.next.entry.acquire();
							}
							//actual.alt.entry.acquire();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println(id+"Estoy cruzando de "+actual.ws.nombre+" ha "+actual.alt.ws.nombre);
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Segmento aux=actual;
						actual=actual.alt;
						System.out.println(id+"Estoy en "+ actual.self);
						aux.entry.release();
					} else {
						try {long j = System.currentTimeMillis();
							actual.next.entry.acquire();
							long i=System.currentTimeMillis();
							if(i-j>2000) {
								System.out.println("2000 milli baño gehiago");
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(id+"Estoy cruzando de "+actual.ws.nombre+" ha "+actual.next.ws.nombre);
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Segmento aux=actual;
						actual=actual.next;
						System.out.println(id+"Estoy en "+ actual.self);
						aux.entry.release();
						
					}
				} else {
					try {long j = System.currentTimeMillis();
						actual.next.entry.acquire();
						long i=System.currentTimeMillis();
						if(i-j>2000) {
							System.out.println("2000 milli baño gehiago");
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(id+"Estoy cruzando de "+actual.ws.nombre+" ha "+actual.next.ws.nombre);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Segmento aux=actual;
					actual=actual.next;
					System.out.println(id+"Estoy en "+ actual.self);
					aux.entry.release();
					
				}
			} else if(actual.self.getY()==0) {
				if(actual.self.getX()<=fin.getX()) {
					if(actual.alt!=null) {
						try {
							actual.alt.entry.acquire();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println(id+"Estoy cruzando de "+actual.ws.nombre+" ha "+actual.alt.ws.nombre);
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Segmento aux=actual;
						actual=actual.alt;
						System.out.println(id+"Estoy en "+ actual.self);
						aux.entry.release();
					} else {
						try {long j = System.currentTimeMillis();
							actual.next.entry.acquire();
							long i=System.currentTimeMillis();
							if(i-j>2000) {
								System.out.println("2000 milli baño gehiago");
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(id+"Estoy cruzando de "+actual.ws.nombre+" ha "+actual.next.ws.nombre);
						try {long j = System.currentTimeMillis();
							actual.next.entry.acquire();
							long i=System.currentTimeMillis();
							if(i-j>2000) {
								System.out.println("2000 milli baño gehiago");
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Segmento aux=actual;
						actual=actual.next;
						System.out.println(id+"Estoy en "+ actual.self);
						aux.entry.release();
						
					}
				} else {
					try {long j = System.currentTimeMillis();
						actual.next.entry.acquire();
						long i=System.currentTimeMillis();
						if(i-j>2000) {
							System.out.println("2000 milli baño gehiago");
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(id+"Estoy cruzando de "+actual.ws.nombre+" ha "+actual.next.ws.nombre);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Segmento aux=actual;
					actual=actual.next;
					System.out.println(id+"Estoy en "+ actual.self);
					aux.entry.release();
					
				}
			} else {
				try {long j = System.currentTimeMillis();
					actual.next.entry.acquire();
					long i=System.currentTimeMillis();
					if(i-j>2000) {
						System.out.println("2000 milli baño gehiago");
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(id+"Estoy cruzando de "+actual.ws.nombre+" ha "+actual.next.ws.nombre);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Segmento aux=actual;
				actual=actual.next;
				System.out.println(id+"Estoy en "+ actual.self);
				aux.entry.release();
				
			}
			
		}
	}
	
}
