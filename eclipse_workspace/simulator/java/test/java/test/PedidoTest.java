package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import simulator.Pedido;

public class PedidoTest {

	 Pedido pedido;
	
	@Before
    public void before() {
        pedido = new Pedido(0);
    }
	
	@Test
	public void pedidoClass() {
		
		assertNotNull("Check pedido object was create",pedido);
	}
	
	@Test
	public void pedidoToString() {
		
		assertEquals("Pedido [id="+ pedido.getId() + ", lista=[]" + "]\n", pedido.toString());
	}
	
	@Test
	public void pedidoLista() {
		
		assertNotNull("Check list was create",pedido.getLista());
		
	}

}
