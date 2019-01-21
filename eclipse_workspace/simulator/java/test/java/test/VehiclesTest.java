package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.sql.SQLException;
import java.util.concurrent.Semaphore;

import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import simulator.ListaSegmento;
import simulator.Manager;
import simulator.Segmento;
import simulator.Vehiculo;

public class VehiclesTest extends EasyMockSupport{
	

    Vehiculo vehiculo;
	Point init;
	Point fin;
	ListaSegmento listaSegmentos;
	Semaphore selectParking;
	Semaphore sql;
	Manager manager;
	Vehiculo mock;
	Segmento segmento;
	
	@Before
	public void before() {
		
		init = new Point();
		fin = new Point();
		listaSegmentos = new ListaSegmento();
		selectParking = new Semaphore(1);
		sql = new Semaphore(1);

		manager = new Manager(null,sql,listaSegmentos.getListaSegmentos());
		vehiculo = new Vehiculo(init, fin, listaSegmentos.getListaSegmentos(), 1, selectParking, sql, manager);
		segmento = listaSegmentos.getListaSegmentos().get(0);
	}
	
	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	
	
	

	/**************Connect test******************************/
	
	
	

	
/**********************Llegada Test*************************/
	
	
	@Test
	public void buscarActualTest() {
		
		systemOutRule.clearLog();
		this.init.x = 3;
		this.init.y = 2;
		vehiculo.buscarActual();
		assertEquals("true", systemOutRule.getLog());
	}
	
	@Test
	public void buscarParkingTrue() {
		
		systemOutRule.clearLog();
		vehiculo.buscarParking();
		assertTrue(systemOutRule.getLog().contains("true"));
		
	}
	
	/********************tryEnterAlt***************/
	

	@Test
	public void tryEnterAltTest() {
		
		vehiculo.setActual(segmento);
		assertTrue(vehiculo.tryEnterAlt());

	}
	
	
	/*************enterNext*************/
	
	
	@Test
	public void enterNextTest() {
		
		vehiculo.setActual(segmento);
		vehiculo.enterNext();
		assertNotSame(vehiculo.getActual(), segmento);
		
	}
	
/*************enterAlt*************/
	
	
	@Test
	public void enterAltTest() {
		
		vehiculo.setActual(segmento);
		vehiculo.enterAlt();
		assertNotSame(vehiculo.getActual(), segmento);
	}
	
/*************moveTest*************/
	
	
	@Test
	public void moveTest1() {
		
		vehiculo.setActual(segmento);
		vehiculo.setFin(new Point(3,2));
		vehiculo.mover();
		assertNotSame(vehiculo.getActual(), vehiculo);
		
	}
	
	@Test
	public void moveTest2() {
		
		segmento = listaSegmentos.getListaSegmentos().get(6);
		vehiculo.setActual(segmento);
		vehiculo.setFin(new Point(5,0));
		vehiculo.mover();
		assertNotSame(vehiculo.getActual(), segmento);
		
	}
	
	@Test
	public void moveTest3() {
		
		segmento = listaSegmentos.getListaSegmentos().get(4);
		vehiculo.setActual(segmento);
		vehiculo.setFin(new Point(7,0));
		vehiculo.mover();
		assertNotSame(vehiculo.getActual(), segmento);
		
	}
	
	@Test
	public void moveTest4() {
		
		segmento = listaSegmentos.getListaSegmentos().get(9);
		vehiculo.setActual(segmento);
		vehiculo.setFin(new Point(3,2));
		vehiculo.mover();
		assertNotSame(vehiculo.getActual(), segmento);
		
	}
	
	@Test
	public void moveTest5() {
		
		segmento = listaSegmentos.getListaSegmentos().get(10);
		vehiculo.setActual(segmento);
		vehiculo.setFin(new Point(1,2));
		vehiculo.mover();
		assertNotSame(vehiculo.getActual(), segmento);
		
	}
	
	@Test
	public void moveTest6() {
		
		segmento = listaSegmentos.getListaSegmentos().get(4);
		vehiculo.setActual(segmento);
		vehiculo.setFin(new Point(5,0));
		vehiculo.getActual().alt = listaSegmentos.getListaSegmentos().get(10);
		vehiculo.mover();
		assertNotSame(vehiculo.getActual(), segmento);
		
	}
	
	@Test
	public void moveTest7() {
		
		segmento = listaSegmentos.getListaSegmentos().get(9);
		vehiculo.setActual(segmento);
		vehiculo.setFin(new Point(3,2));
		vehiculo.getActual().alt = listaSegmentos.getListaSegmentos().get(10);
		vehiculo.mover();
		assertNotSame(vehiculo.getActual(), segmento);
		
	}
	
	@Test
	public void moveTest8() {
	
		segmento = listaSegmentos.getListaSegmentos().get(2);
		vehiculo.setActual(segmento);
		vehiculo.setFin(new Point(3,0));
		Semaphore test = new Semaphore(0);
		vehiculo.getActual().alt.setEntry(test);
		vehiculo.mover();
		assertNotSame(vehiculo.getActual(), segmento);
		
	}
	
	@Test
	public void moveTest9() {
	
		segmento = listaSegmentos.getListaSegmentos().get(7);
		vehiculo.setActual(segmento);
		vehiculo.setFin(new Point(7,2));
		Semaphore test = new Semaphore(0);
		vehiculo.getActual().alt.setEntry(test);
		vehiculo.mover();
		assertNotSame(vehiculo.getActual(), segmento);
		
	}
	
	@Test
	public void llegadaObjetivoTest() {
		vehiculo.setActual(listaSegmentos.getListaSegmentos().get(7));
		vehiculo.setObjetivo(new Point(5,0));
		vehiculo.getActual().getWs().getEntry().release();
		vehiculo.llegada();
		assertEquals(vehiculo.getFin(),vehiculo.getSalida().getSelf());
	}
	
	@Test
	public void llegadaSalidaTest1() {
		systemOutRule.clearLog();
		vehiculo.setActual(vehiculo.getSalida());
		vehiculo.setProductoID(1);
		vehiculo.setPedidoID(1);
		vehiculo.getSalida().getWs().setOcupado(true);
		vehiculo.getSalida().getWs().getEntry().release();
		vehiculo.getSql().release();
		vehiculo.getSalida().getWs().getExit().release();
		vehiculo.getSelectParking().release();
		vehiculo.llegada();
		assertTrue(systemOutRule.getLog().contains("Voy a salir de ocupado"));
	}
	@Test
	public void llegadaSalidaTest2() {
		systemOutRule.clearLog();
		vehiculo.setActual(vehiculo.getSalida());
		vehiculo.setProductoID(1);
		vehiculo.setPedidoID(1);
		vehiculo.getSalida().getWs().setOcupado(false);
		vehiculo.getSalida().getWs().getEntry().release();
		vehiculo.getSql().release();
		vehiculo.getSalida().getWs().getExit().release();
		vehiculo.getSelectParking().release();
		vehiculo.llegada();
		assertTrue(systemOutRule.getLog().contains("Voy a salir de desocupado"));
	}
	
	
	@Test
	public void llegadaParking() {
		systemOutRule.clearLog();
		vehiculo.setActual(listaSegmentos.getListaSegmentos().get(1));
		vehiculo.setParking(new Point(3,2));
		vehiculo.getActual().getWs().getEntry().release();
		vehiculo.getActual().getEntry().release();
		vehiculo.getActual().getWs().getExit().release();
		vehiculo.llegada();
		assertTrue(systemOutRule.getLog().contains("He entrado a parking"));
	}
	

}
