import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	
	ArrayList<Articulo> listaArticulos= new ArrayList<Articulo>();
	ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();
	
	public Principal() throws InterruptedException {
		initArticulos();
		menu();
	}
	
	public void initArticulos() {
		listaArticulos.add(new Articulo(1, 1, 1, "Zapatillas"));
		listaArticulos.add(new Articulo(2, 2, 2, "TV"));
		listaArticulos.add(new Articulo(3, 3, 3, "PC"));
		listaArticulos.add(new Articulo(4, 4, 4, "Bananas"));
		listaArticulos.add(new Articulo(5, 5, 5, "Raton"));
	}
	
	public void menu() throws InterruptedException {
		Scanner teclado = new Scanner(System.in);
		System.out.println("Cuantos pedidos deseas hacer?");
		int cantidad = teclado.nextInt();
		for(int i=0;i<cantidad;i++) {
			System.out.println("Vamos con el "+(i+1)+"º pedido");
			Pedido pedido = new Pedido(i+1);
			System.out.println("Cuales de estos productos deseas? Cuando este listo el pedido pulsa 0");
			for(Articulo a:listaArticulos) {
				System.out.println(a.getNombre());
			}
			int aux=1;
			do {
				aux = teclado.nextInt();
				if(aux!=0) {
					pedido.add(listaArticulos.get(aux-1));
				}
			}while(aux!=0);
			listaPedidos.add(pedido);
		}
		System.out.println("Pedidos hechos");
		ArrayList<Vehiculo> listaV = new ArrayList<Vehiculo>();
		int cont=1;
		for(Pedido p: listaPedidos) {
			listaV.add(new Vehiculo(cont++, p)); 
		}
		for(Vehiculo v: listaV) {
			v.start();
		}
		for(Vehiculo v: listaV) {
			v.join();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Principal principal= new Principal();

	}

}
