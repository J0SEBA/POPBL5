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
	ArrayList<Integer> dataSet = new ArrayList<Integer>();
	ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();
	ArrayList<Producto> cola = new ArrayList<Producto>();
	
	public Manager(ArrayList<Vehiculo> listaVehiculos, Semaphore sql, ArrayList<Segmento> listaSegmentos) {
		this.listaVehiculos=listaVehiculos;
		this.listaSegmentos=listaSegmentos;
		this.connectionString = url + serverName + "/" + dataBaseName;
		this.sql=sql;
		connect();
		init();
		/*check();
		leer();*/
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
	
	public void leer() {
		ResultSet rs;
		String query = "select * from orders";
		Statement stm;
		try {
			sql.acquire();
			stm = connection.createStatement();
			rs = stm.executeQuery(query);
			
			/*while(rs.next()) {
				int i = rs.getInt("isAssigned");
				if(i==0) {
					dataSet.add(rs.getInt("productID"));
					System.out.println(rs.getInt("productID"));
				}
			}*/
			stm.executeUpdate("update orders_product set isAssigned = 1 where isAssigned = 0");
			
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
		
		for(Vehiculo v:listaVehiculos) {
			if(cola.size()>0) {
				if(v.actual.self==v.parking) {
					v.actual.ws.ocupado=false;
					v.fin=cola.get(cola.size()-1).wsID>5 ? new Point(9,0) : new Point(1,2);
					v.objetivo=cola.get(cola.size()-1).wsID>5 ? new Point(9,0) : new Point(1,2);
					v.parking= new Point(0,0);
					v.actual.ws.exit.release();
					System.out.println("He despertado ha "+v.id+" para que vaya a"+v.fin);
					cola.get(cola.size()-1).cantidad--;
					if(cola.get(cola.size()-1).cantidad==0) {
						cola.remove(cola.size()-1);
					}
				}
			}
		}
		
		System.out.println("Quedan: "+cola.size());
		/*
		int helper = dataSet.size();
		for(int j=helper-1;j>=0;j--) {
			for(int i=0;i<listaVehiculos.size();i++) {
				if(listaVehiculos.get(i).actual.self==listaVehiculos.get(i).parking) {
					int aux = getWs(dataSet.get(j));
					Point temp;
					switch(aux) {
					case 1:
						temp=new Point(9,0);
						break;
					case 2:
						temp=new Point(5,0);
						break;
					case 3:
						temp=new Point(1,0);
						break;
					case 4:
						temp=new Point(1,2);
						break;
					default:
						temp=listaVehiculos.get(i).init;
					}
					listaVehiculos.get(i).actual.ws.ocupado=false;
					listaVehiculos.get(i).fin=temp;
					listaVehiculos.get(i).objetivo=temp;
					listaVehiculos.get(i).parking= new Point(0,0);
					listaVehiculos.get(i).actual.ws.exit.release();
					System.out.println("He despertado ha "+listaVehiculos.get(i).id+" para que vaya a"+listaVehiculos.get(i).fin);
					dataSet.remove(j);
					System.out.println("Cantidad de que haceres: " +dataSet.size());
					break;
				}
			}
		}*/
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
						+"\n"+listaSegmentos.get(8).ws.ocupado);
				Thread.sleep(1000);
				/*if(check()) {
					leer();
				}
				if(dataSet.size()>0) {
					asignar();
				}*/
				asignar();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
