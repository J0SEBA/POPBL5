
public class Vehiculo extends Thread{
	int vehiculoId;
	Pedido pedido;
	boolean finalizado=false;
	int estacionActual=1;
	
	public Vehiculo(int vehiculoId, Pedido pedido) {
		super();
		this.vehiculoId = vehiculoId;
		this.pedido = pedido;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Vehiculo "+ vehiculoId+ " ha recibido un pedido: ");
			for(Articulo a: pedido.lista) {
				System.out.println(" -"+a.getNombre()+": "+a.getCantidad());
			}
			Thread.sleep(2000);
			for(int i=0;i<pedido.lista.size();i++) {
				System.out.println("Vehiculo "+vehiculoId+" se dirige a workstation"+pedido.lista.get(i).getWorkstation()+" para coger "+ pedido.lista.get(i).getNombre());
				Thread.sleep(Math.abs(estacionActual-pedido.lista.get(i).getWorkstation())*1000);
				System.out.println("Vehiculo "+vehiculoId+" ha llegado a workstation"+pedido.lista.get(i).getWorkstation()+" y ha cogido "+ pedido.lista.get(i).getNombre());
				estacionActual=pedido.lista.get(i).getWorkstation();
			}
			System.out.println("Vehiculo "+vehiculoId+" ha terminado su trabajo");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public int getVehiculoId() {
		return vehiculoId;
	}
	public void setVehiculoId(int vehiculoId) {
		this.vehiculoId = vehiculoId;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
}
