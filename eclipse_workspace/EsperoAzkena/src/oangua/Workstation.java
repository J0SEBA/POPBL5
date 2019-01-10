package oangua;

import java.util.concurrent.Semaphore;

import oangua.Vehiculo;

public class Workstation {
	String nombre;
	Semaphore exit= new Semaphore(0);
	Semaphore entry=new Semaphore(1);
	boolean ocupado= false;
	Vehiculo actual;
	
	public Workstation(String nombre) {
		this.nombre=nombre;
	}
}
