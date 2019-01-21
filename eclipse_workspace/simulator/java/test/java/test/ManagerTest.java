package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Point;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import simulator.ListaSegmento;
import simulator.Manager;
import simulator.Vehiculo;

public class ManagerTest{
	
	private Manager test; 
	private ArrayList<Vehiculo> listaVehiculos;
	private Semaphore sql;
	ListaSegmento listaSegmentos;
	
	@Before
	public void before() {
		listaVehiculos = new ArrayList<Vehiculo>();
		sql  = new Semaphore(1);
		listaSegmentos = new ListaSegmento();
		test = new Manager(listaVehiculos, sql, listaSegmentos.getListaSegmentos());
	}
	
	/*****************Connection test*************************/
	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	
	@Test
	public void connectTest() {
		
		
	    assertEquals("Conexion establecida", systemOutRule.getLog());
		
	}
	@Test
	public void ClassNotFoundExceptionTest() throws SQLException{
		
		systemOutRule.clearLog();
		test.connect("testError");
		assertEquals("Could Not Connect to DB", systemOutRule.getLog());
		
	}

	
	/*************Disconnect test******************************/

	@Test
	public void disconnectTest() {
		
		test.disconnect();
		assertNotNull(test.getStatement());
		assertNotNull(test.getConnection());
	}
	
	
	
	/******************leerDatabase*********************/
	
	@Test
	public void leerDatabaseTest() {
		
		int i = test.getListaPedidos().size();
		try {
			test.getConnection().createStatement().executeUpdate("update orders set statee='Unfinished'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.leerDatabase();
		int a  = test.getListaPedidos().size();
		assertNotEquals(i, a);
	}
	
	
/******************entregarTest*********************/
	
	@Test
	public void entregarTest() {
		
		
		try {
			test.getConnection().createStatement().executeUpdate("update orders set statee='Unfinished'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.leerDatabase();
		int i = test.getListaPedidosAux().size();
		test.entregar(test.getListaPedidosAux().get(0).getId(),test.getListaPedidosAux().get(0).getLista().get(0).getProductID());
		assertNotEquals(i, test.getListaPedidosAux().size());
		
	}
	
	
/******************asignarTest*********************/
	
	@Test
	public void asignarTest() {
		
		
		try {
			test.getConnection().createStatement().executeUpdate("update orders set statee='Unfinished'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.leerDatabase();
		int i=test.getListaPedidos().get(0).getLista().size();
		test.getListaVehiculos().add(new Vehiculo(new Point(3,0), new Point(5,0), listaSegmentos.getListaSegmentos(), 1, null, sql, test));
		test.getListaVehiculos().get(0).getActual().setSelf(new Point(3,2));
		test.getListaVehiculos().get(0).setParking(new Point(3,2));
		test.asignar();
		assertNotEquals(i, test.getListaPedidos().get(0).getLista().size());
	}
	
	
	
}
