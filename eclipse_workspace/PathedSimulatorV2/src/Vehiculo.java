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
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(actual.self.equals(fin)) {
				System.out.println(id+"Voy por la "+(++contador));
				
				System.out.println(id+" He llegado ha "+ actual.ws.nombre+"--->"+ actual.self);
				if(!actual.ws.ocupado) {
					System.out.println(id+" Voy a entrar a "+actual.ws.nombre);
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
					actual.ws.entry.release();
					fin=extra;
				} else {
					System.out.println(id+" Voy a hechar al que esta y voy a entrat a "+actual.ws.nombre);
					actual.ws.exit.release();
					try {
						actual.ws.entry.acquire();
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
					actual.ws.entry.release();
					fin=extra;
				}
				
				//break;
			}
			if(actual.self.getY()==2) {
				if(actual.self.getX()>=fin.getX()) {
					if(actual.alt!=null) {
						boolean done=false;
						try {
							done=actual.alt.entry.tryAcquire(1, TimeUnit.NANOSECONDS);
							System.out.println(done);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(done) {
							Segmento aux=actual;
							System.out.println(id+" Estoy en "+ actual.self);
							actual=actual.alt;
							aux.entry.release();
						} else {
							System.out.println(id+" No he posido entrar a "+actual.alt.self);
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
					} else {
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
				} else {
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
			} else if(actual.self.getY()==0) {
				if(actual.self.getX()<=fin.getX()) {
					if(actual.alt!=null) {
						boolean done=false;
						try {
							done=actual.alt.entry.tryAcquire(1, TimeUnit.NANOSECONDS);
							System.out.println(done);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(done) {
							Segmento aux=actual;
							System.out.println(id+" Estoy en "+ actual.self);
							actual=actual.alt;
							aux.entry.release();
						} else {
							System.out.println(id+" No he posido entrar a "+actual.alt.self);
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
						
						
					} else {
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
				} else {
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
			} else {
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
	}
	
}
