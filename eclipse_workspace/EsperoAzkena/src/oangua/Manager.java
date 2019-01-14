package oangua;

import java.awt.Point;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
		String query2="select orders_product.orderID, orders_product.remaining, orders_product.productID, product.warehouseID, product.description "
				+ "from orders_product join product on product.productID = orders_product.productID";
		
		Statement stm, stm2;
		
		try {
			sql.acquire();
			stm = connection.createStatement();
			stm2 = connection.createStatement();
			rs = stm.executeQuery(query1);
			while(rs.next()) {
				if(rs.getString("statee").equals("Unfinished")) {
					listaPedidos.add(new Pedido(rs.getInt("orderID")));
					rs2=stm2.executeQuery(query2);
					while(rs2.next()) {
						if(rs2.getInt("orderID")==rs.getInt("orderID")) {
							listaPedidos.get(listaPedidos.size()-1).lista.add(new Producto(rs2.getString("description"),rs2.getInt("remaining"),rs2.getInt("warehouseID")));
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
	
	public void init() {
		ResultSet rs;
		String query1="select orderID, statee from orders";
		String query2="select orders_product.orderID, orders_product.isAssigned, orders_product.productID, orders_product.quantity, product.description, product.warehouseID from orders_product join product on orders_product.productID=product.productID";
		Statement stm;
		try {
			sql.acquire();
			stm = connection.createStatement();
			rs = stm.executeQuery(query1);
			while(rs.next()) {
				if(rs.getInt("statee")==0) {
					listaPedidos.add(new Pedido(rs.getInt("orderID")));
				}
			}
			while(rs.next()) {
				if(rs.getInt("isAssigned")==0) {
					int aux = rs.getInt("orderID");
					int result = -1;
					for(int i = 0;i<listaPedidos.size();i++) {
						if(listaPedidos.get(i).id==aux) {
							result = i;
						}
					}
					listaPedidos.get(result).lista.add(new Producto(rs.getString("description"), rs.getInt("quantity"), rs.getInt("warehouseID")));
					cola.add(new Producto(rs.getString("description"), rs.getInt("quantity"), rs.getInt("warehouseID")));
				}
			}
			for(Pedido p:listaPedidos) {
				System.out.println(p.toString());
			}
			
			stm.executeUpdate("update orders set statee = 1");
			stm.executeUpdate("update orders_product set isAssigned = 1");
			sql.release();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public void asignar() {
		Point aux=null;
		for(Vehiculo v:listaVehiculos) {
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
								case 6:
									aux=new Point(3,2);
									break;
								}
								v.fin=aux;
								v.objetivo=aux;
								v.parking=new Point(0,0);
								v.actual.ws.exit.release();
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
