package main;

import java.awt.Point;
import java.util.concurrent.Semaphore;

public class Segmento {
	Point self;
	public Segmento alt;
	public Segmento next;
	boolean ocupado=false;
	Semaphore entry=new Semaphore(1);
	Workstation ws;
	
	public Segmento(Point self, Workstation ws) {
		this.ws=ws;
		this.self=self;
	}
}
