package oangua;

import java.awt.Point;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.Semaphore;

public class Manager extends Thread{
	
	
	
	ArrayList<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
	ArrayList<Segmento> listaSegmentos = new ArrayList<Segmento>();
	Semaphore sql;
	Connection connection = null;
	Statement statement = null;
	String serverName = "localhost";
	String dataBaseName = "amuzon";
	String url = "jdbc:mysql://";
	String username = "root";
	String password = "";
	String connectionString = null;
	
	String check="none";
	ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();
	ArrayList<Producto> cola = new ArrayList<Producto>();
	
	public Manager(ArrayList<Vehiculo> listaVehiculos, Semaphore sql, ArrayList<Segmento> listaSegmentos) {
		this.listaVehiculos=listaVehiculos;
		this.listaSegmentos=listaSegmentos;
		this.connectionString = url + serverName + "/" + dataBaseName;
		this.sql=sql;
		connect();
		check();
		leerDatabase();
	}
	
	public boolean check() {
		String s = "select update_time from information_schema.tables where table_schema='amuzon' and table_name='orders_product'";
		try {
			sql.acquire();
			ResultSet rs=statement.executeQuery(s);
			if(rs.next()) {
				if(!check.equals(rs.getString("update_time"))&&rs.getString("update_time")!=null) {
					check=rs.getString("update_time");
					sql.release();
					return true;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql.release();
		return false;
	}
	
	private void connect() {

		try {

			Class.forName("com.mysql.jdbc.Driver");
			// Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionString,
					username, password);
			statement = connection.createStatement();
		} catch (ClassNotFoundException e) {

			System.out.println("Connection Driver Error");
		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("Could Not Connect to DB ");
		}
	}

	private void disconnect() {

		try {

			if (statement != null) {

				statement.clearWarnings();
				statement.close();
			}

			if (connection != null) {

				connection.clearWarnings();
				connection.close();
			}
		} catch (SQLException e) {

			System.out.println("Error disconnecting");
		}
	}
	
	public void leerDatabase() {
		ResultSet rs, rs2;
		String query1="select orderID, statee from orders";
		String query2="select orders_product.orderID, orders_product.quantity, orders_product.productID, product.workstationID, product.description "
				+ "from orders_product join product on product.productID = orders_product.productID";
		
		Statement stm, stm2;
		
		try {
			sql.acquire();
			stm = connection.createStatement();
			stm2 = connection.createStatement();
			rs = stm.executeQuery(query1);
			while(rs.next()) {
				if(!rs.getString("statee").equals("Finished")) {
					listaPedidos.add(new Pedido(rs.getInt("orderID")));
					rs2=stm2.executeQuery(query2);
					while(rs2.next()) {
						if(rs2.getInt("orderID")==rs.getInt("orderID")) {
							listaPedidos.get(listaPedidos.size()-1).lista.add(new Producto(rs2.getString("description"),rs2.getInt("quantity"),rs2.getInt("workstationID")));
						}
					}
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql.release();
		System.out.println(listaPedidos);
		
	}
	
	public int getWs(int i) {
		String s = "select warehouseID from product where productID="+i;
		ResultSet rs;
		Statement stm;
		try {
			sql.acquire();
			stm=connection.createStatement();
			rs=stm.executeQuery(s);
			if(rs.next()) {
				sql.release();
				return rs.getInt("warehouseID");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql.release();
		return 0;
	}
	
	public class Aux {
		@Override
		public String toString() {
			return "Aux [cantidad=" + cantidad + ", id=" + id + "]";
		}
		public int getCantidad() {
			return cantidad;
		}
		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public Aux(int cantidad, int id) {
			super();
			this.cantidad = cantidad;
			this.id = id;
		}
		int cantidad;
		int id;
	}
	
	public void asignar() {
		Point aux=null;
		ArrayList<Aux> auxes = new ArrayList<Aux>();
		try {
			sql.acquire();
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("select timesUsed from vehicles");
			sql.release();
			while(rs.next()) {
				auxes.add(new Aux(rs.getInt("timesUsed"), auxes.size()+1));
			}
			Collections.sort(auxes, new Comparator<Aux>() {
			    @Override
			    public int compare(Aux z1, Aux z2) {
			        if (z1.getCantidad() > z2.getCantidad())
			            return 1;
			        if (z1.getCantidad() < z2.getCantidad())
			            return -1;
			        return 0;
			    }
			});
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Vehiculo> listaVehiculos2 = new ArrayList<Vehiculo>();
		for(Aux a:auxes) {
			listaVehiculos2.add(listaVehiculos.get(a.id-1));
		}
		
		
		for(Vehiculo v:listaVehiculos2) {
			if(v.actual.self==v.parking) {
				for(Pedido pedido:listaPedidos) {
					for(Producto producto: pedido.lista) {
						if(producto.cantidad>0) {
							if(v.actual.self==v.parking) {
								v.actual.ws.ocupado=false;
								switch (producto.wsID) {
								case 1:
									aux=new Point(9,0);
									break;
								case 2:
									aux=new Point(5,0);
									break;
								case 3:
									aux=new Point(1,0);
									break;
								case 4:
									aux=new Point(1,2);
									break;
								case 5:
									aux=new Point(5,2);
									break;
								}
								v.fin=aux;
								v.objetivo=aux;
								v.parking=new Point(0,0);
								v.actual.ws.exit.release();
								v.aumentarUsos();
								System.out.println("He despertado ha "+v.id+" para que vaya a"+v.fin);
								producto.cantidad--;
							}
						}
					}
				}
			}
		}
		
		for(int i=listaPedidos.size()-1;i>=0;i--) {
			for(int j=listaPedidos.get(i).lista.size()-1;j>=0;j--) {
				if(listaPedidos.get(i).lista.get(j).cantidad==0) {
					listaPedidos.get(i).lista.remove(j);
				}
			}
		}
		for(int i=listaPedidos.size()-1;i>=0;i--) {
			if(listaPedidos.get(i).lista.size()==0) {
				listaPedidos.remove(i);
			}
		}
		System.out.println("Quedan "+listaPedidos.size());
		System.out.println("Quedan "+listaPedidos);
	}
	
	@Override
	public void run() {
		boolean estado=false;
		while(!estado) {
			try {
				System.out.println(listaVehiculos.get(0).id+"------------>"+listaVehiculos.get(0).actual.self+","+listaVehiculos.get(0).estado
						+"\n"+listaVehiculos.get(1).id+"------------>"+listaVehiculos.get(1).actual.self+","+listaVehiculos.get(1).estado
						+"\n"+listaVehiculos.get(2).id+"------------>"+listaVehiculos.get(2).actual.self+","+listaVehiculos.get(2).estado
						+"\n"+listaVehiculos.get(3).id+"------------>"+listaVehiculos.get(3).actual.self+","+listaVehiculos.get(3).estado
						+"\n"+listaVehiculos.get(4).id+"------------>"+listaVehiculos.get(4).actual.self+","+listaVehiculos.get(4).estado
						+"\n"+listaSegmentos.get(1).ws.ocupado
						+"\n"+listaSegmentos.get(3).ws.ocupado
						+"\n"+listaSegmentos.get(6).ws.ocupado
						+"\n"+listaSegmentos.get(8).ws.ocupado
						+"\n"+listaSegmentos.get(4).ws.ocupado);
				Thread.sleep(1000);
				if(check()) {
					
				}
				//leerDatabase();
				asignar();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
