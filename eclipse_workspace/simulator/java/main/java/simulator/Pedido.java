package simulator;
import java.util.ArrayList;


/**
* This is the object Pedido that has the paremeters id and a ArrayList
*
* @author Joseba Carnicero, Aitor Eizmendi, Jon Mugica and Marcos Azkarate
*/
public class Pedido {
	int id;
	ArrayList<Producto> lista = new ArrayList<Producto>();
	
	public Pedido(int id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", lista=" + lista + "]\n";
	}

	public ArrayList<Producto> getLista() {
		return lista;
	}

	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
}
