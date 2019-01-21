package simulator;


import java.awt.Point;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

/**
* This is the object MANAGER that has the methods to do the conection and disconection for the database.
*
* @author Joseba Carnicero, Aitor Eizmendi, Jon Mugica and Marcos Azkarate
*/

public class Manager extends Thread{
	
	
	
	ArrayList<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
	ArrayList<Segmento> listaSegmentos = new ArrayList<Segmento>();
	Semaphore sql;
	Connection connection = null;
	Statement statement = null;
	String serverName = "localhost";
	String dataBaseName = "amuzon";
	String url = "jdbc:mysql://";
	String username = "admin";
	String password = "admin";
	String connectionString = null;
	
	private final static Logger LOGGER = Logger.getLogger("PriorityFail.simulator.Manager");
	String check="none";
	ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();
	ArrayList<Pedido> listaPedidosAux = new ArrayList<Pedido>();
	
	
	/**
	* Parameterized constructor.
	*
	* @param listaVehiculos contains an ArrayList of Vehicles
	* @param sql is a Semaphore that it is used to a good synchronization
	* @param listaSegmentos is an ArrayList of Segments
	*/
	
	public Manager(ArrayList<Vehiculo> listaVehiculos, Semaphore sql, ArrayList<Segmento> listaSegmentos) {
		this.listaVehiculos=listaVehiculos;
		this.listaSegmentos=listaSegmentos;
		this.connectionString = url + serverName + "/" + dataBaseName;
		this.sql=sql;
		connect(username);
	}
	
	/**
	* This method connects to the selected database
	*/
	public void connect(String username) {

		try {

			Class.forName("com.mysql.jdbc.Driver");
			// Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionString,
					username, password);
			statement = connection.createStatement();
			System.out.print("Conexion establecida");
		} catch (ClassNotFoundException e) {

			System.out.print("Connection Driver Error");
		} catch (SQLException e) {

			e.printStackTrace();
			System.out.print("Could Not Connect to DB");
		}
	}

	/**
	* This method closes the connected to the selected database
	*/
	public void disconnect() {

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
	
	/**
	* Parameterized constructor.
	*
	* @param int pedidoID describes the Id a certain order has
	* @param int productoID describes the Id a certain product has
	* 
	* This method delivers all the products of a new order and change the status of the order into "Finished"
	*/
	
	public void entregar(int pedidoID, int productoID) {
		for(Pedido p:listaPedidosAux) {
			if(pedidoID == p.id) {
				for(Producto pro:p.lista) {
					if(productoID == pro.productID) {
						pro.remaining++;
					}
					if(pro.remaining == pro.cantidad) {
						pro.cantidad=0;
					}
				}
			}
		}
		for(int i=listaPedidosAux.size()-1;i>=0;i--) {
			for(int j=listaPedidosAux.get(i).lista.size()-1;j>=0;j--) {
				if(listaPedidosAux.get(i).lista.get(j).cantidad==0) {
					listaPedidosAux.get(i).lista.remove(j);
				}
			}
			if(listaPedidosAux.get(i).lista.size()==0) {
				try {
					sql.acquire();
					try (Statement stm = connection.createStatement()){
						stm.executeUpdate("update orders set statee='finished' where orderID="+listaPedidosAux.get(i).id);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					sql.release();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					e.printStackTrace();
				}
				listaPedidosAux.remove(i);
			}
		}
	}
	/**
	* This method reads from the database the new orders and 
	*/
	
	public void leerDatabase() {
		String query1="select orderID, statee from orders where statee ='Unfinished'";
		String query2="select orders_product.orderID, orders_product.quantity, orders_product.productID, product.workstationID, product.description "
				+ "from orders_product join product on product.productID = orders_product.productID";
		String query3 = "update orders set statee='doing'";
		
		int first=0;
		int last =0;
		try {
			sql.acquire();
			try(Statement stm = connection.createStatement(); Statement stm2 = connection.createStatement();Statement stm3 = connection.createStatement()) {
				try(ResultSet rs = stm.executeQuery(query1);){
					while(rs.next()) {
						if(rs.isFirst()) {
							first=rs.getInt("orderID");
						}
						if(rs.isLast()) {
							last = rs.getInt("orderID");
						}
						if(rs.getString("statee").equals("Unfinished")) {
							System.out.println("sartu da");
							listaPedidos.add(new Pedido(rs.getInt("orderID")));
							listaPedidosAux.add(new Pedido(rs.getInt("orderID")));
							try(ResultSet rs2 = stm2.executeQuery(query2);){
								while(rs2.next()) {
									if(rs2.getInt("orderID")==rs.getInt("orderID")) {
										listaPedidos.get(listaPedidos.size()-1).lista.add(new Producto(rs2.getString("description"),rs2.getInt("quantity"),rs2.getInt("workstationID"),rs2.getInt("productID")));
										listaPedidosAux.get(listaPedidosAux.size()-1).lista.add(new Producto(rs2.getString("description"),rs2.getInt("quantity"),rs2.getInt("workstationID"),rs2.getInt("productID")));
										
									}
								}
							}
							
						}
					}
				}
				query3 = query3 + " where orderID >="+first+" and orderID <= "+last;
				stm3.executeUpdate(query3);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
		sql.release();
		
	}
	/**	
	* This method assigned different segment according to its priority
	*/
	
	public void asignar() {
		Point aux=null;
		for(Vehiculo v:listaVehiculos) {
			if(v.actual.self.equals(v.parking)) {
				for(Pedido pedido:listaPedidos) {
					for(Producto producto: pedido.lista) {
						if(producto.cantidad>0) {
							if(v.actual.self.equals(v.parking)) {
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
								v.productoID=producto.productID;
								v.pedidoID=pedido.id;
								v.fin=aux;
								v.objetivo=aux;
								v.parking=new Point(0,0);
								try {
									String s = "update vehicles set timesUsed = (select timesUsed from (select * from vehicles) as a where vehicleID = "+v.id+") + 1 where vehicleID="+v.id;
									sql.acquire();
									try(Statement stm = connection.createStatement()){
										stm.executeUpdate(s);
									}
									sql.release();
								} catch (InterruptedException e) {
									Thread.currentThread().interrupt();
									e.printStackTrace();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
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
	}
	
	/**
	* This method display the actual position of the autonomous vehicles and the status of the different workstations
	*/
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
				leerDatabase();
				asignar();
			} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					e.printStackTrace();
				}
				
			}
			
		}

	public ArrayList<Vehiculo> getListaVehiculos() {
		return listaVehiculos;
	}

	public void setListaVehiculos(ArrayList<Vehiculo> listaVehiculos) {
		this.listaVehiculos = listaVehiculos;
	}

	public ArrayList<Segmento> getListaSegmentos() {
		return listaSegmentos;
	}

	public void setListaSegmentos(ArrayList<Segmento> listaSegmentos) {
		this.listaSegmentos = listaSegmentos;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public ArrayList<Pedido> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(ArrayList<Pedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	public ArrayList<Pedido> getListaPedidosAux() {
		return listaPedidosAux;
	}

	public void setListaPedidosAux(ArrayList<Pedido> listaPedidosAux) {
		this.listaPedidosAux = listaPedidosAux;
	}
	
	}

