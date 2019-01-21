package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import simulator.Producto;

public class ProductoTest {

	 Producto producto;
		
		@Before
	    public void before() {
			producto = new Producto("test", 0, 0, 0);
	    }
		
		@Test
		public void pedidoClass() {
			
			assertNotNull("Check product object was create",producto);
		}
		
		@Test
		public void pedidoToString() {
			
			assertEquals("Producto [nombre=test, cantidad=0, wsID=0]",producto.toString() );
		}
		
}
