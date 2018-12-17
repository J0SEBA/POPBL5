import java.util.ArrayList;

public class Pedido {
	
	ArrayList<Articulo> lista=new ArrayList<Articulo>();
	int pedidoId;
	Cliente cliente;
	
	public Pedido(int pedidoId) {
		super();
		this.pedidoId = pedidoId;
	}
	
	public void add(Articulo a) {
		lista.add(a);
	}
	
	public ArrayList<Articulo> getLista() {
		return lista;
	}
	public void setLista(ArrayList<Articulo> lista) {
		this.lista = lista;
	}
	public int getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(int pedidoId) {
		this.pedidoId = pedidoId;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
