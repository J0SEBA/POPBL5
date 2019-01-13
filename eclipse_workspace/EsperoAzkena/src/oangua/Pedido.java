package oangua;

import java.util.ArrayList;

public class Pedido {
	int id;
	ArrayList<Producto> lista = new ArrayList<Producto>();
	
	public Pedido(int id) {
		super();
		this.id = id;
	}
	
	public boolean empty() {
		/*boolean estado=true;
		for(Producto p:lista) {
			if(p.cantidad>0) {
				estado=false;
			}
		}
		return estado;*/
		return lista.size()>0 ? false : true;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", lista=" + lista + "]";
	}
	
}
