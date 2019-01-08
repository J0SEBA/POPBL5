package main;
import java.util.ArrayList;

public class Manager extends Thread{
	
	ArrayList<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
	
	public Manager(ArrayList<Vehiculo> listaVehiculos) {
		this.listaVehiculos=listaVehiculos;
	}
	
	@Override
	public void run() {
		boolean estado=false;
		while(!estado) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(Vehiculo v:listaVehiculos) {
				if(v.estado.equals("sleeping")) {
					v.fin=v.init;
					v.actual.ws.exit.release();
					estado=true;
					System.out.println("He despertado ha "+v.id+" para que vaya a"+v.fin);
					break;
				}
			}
		}
		
	}

}
