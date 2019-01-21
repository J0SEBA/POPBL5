package simulator;
import java.awt.Point;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
* This is the Vehicle class.
* Vehicle 
*
* @author Joseba Carnicero, Aitor Eizmendi, Jon Mugica and Marcos Azkarate
*/

public class Vehiculo extends Thread{
	
	Point init;
	Point fin;
	Point objetivo;
	Point parking;
	int productoID, pedidoID;
	ArrayList<Segmento> listaSegmentos;
	Segmento actual;
	int id;
	Semaphore selectParking;
	Semaphore sql;
	String estado= "Working";
	Segmento salida;
	
	Manager manager;
	
	Connection connection = null;
	Statement statement = null;
	String serverName = "localhost";
	String dataBaseName = "amuzon";
	String url = "jdbc:mysql://";
	String username = "admin";
	String password = "admin";
	String connectionString = null;
	
	private final static Logger LOGGER = Logger.getLogger("PriorityFail.simulator.Vehiculo");
	
	public Vehiculo(Point init, Point fin, ArrayList<Segmento> listaSegmentos, int id, Semaphore selectParking, Semaphore sql, Manager manager) {
		super();
		this.init = init;
		this.fin = fin;
		this.sql=sql;
		this.manager=manager;
		objetivo= new  Point(0,0);
		this.listaSegmentos = listaSegmentos;
		this.id=id;
		this.connectionString = url + serverName + "/" + dataBaseName;
		this.selectParking = selectParking;
		salida=listaSegmentos.get(4);
		connect(username);
		buscarActual();
		
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

			LOGGER.log(LOGGER.getLevel(),e.toString());
			System.out.print("Could Not Connect to DB ");
		}
	}
	/**
	* This method reserve an segment to a certain vehicle
	*/
	public void buscarActual() {
		for(int i=0;i<listaSegmentos.size();i++) {
			if(listaSegmentos.get(i).self.equals(init)) {
				actual=listaSegmentos.get(i);
				parking=listaSegmentos.get(i).self;
				fin=parking;
				actual.ws.ocupado=true;
				System.out.print(actual.ws.ocupado);
				try {
					
					actual.entry.acquire();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					LOGGER.log(LOGGER.getLevel(),e.toString());
				}
			}
		}
	}
	/**
	* This method look for a parking segment.
	*/
	public void buscarParking() {
		try {
			selectParking.acquire();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			LOGGER.log(LOGGER.getLevel(),e.toString());
		}
		boolean buscado=false;
		for(int i = 0; i<listaSegmentos.size() && buscado==false;i++) {
			if(listaSegmentos.get(i).ws.nombre.contains("Parking")) {
				if(!listaSegmentos.get(i).ws.ocupado) {
					listaSegmentos.get(i).ws.ocupado=true;
					parking=listaSegmentos.get(i).self;
					fin=parking;
					System.out.println(id+"----Buscado parking"+listaSegmentos.get(i).ws.nombre+fin);
					buscado=true;
				}
			}
		}
		if(buscado==false) {
			System.out.println("Hay un problema");
		}
		selectParking.release();
		System.out.print(buscado);
	}
	/**
	* This method simulates the movement of the vehicles
	*/
	public void mover() {
		if(actual.self.getY()==2) {
			if(actual.self.getX()>=fin.getX()) {
				if(actual.alt!=null) {
					if(tryEnterAlt()) {
						enterAlt();
					} else {
						System.out.println(id+" No he posido entrar a "+actual.alt.self);
						enterNext();
					}
				} else {
					enterNext();
				}
			} else {
				enterNext();
			}
		} else if(actual.self.getY()==0) {
			if(actual.self.getX()<=fin.getX()) {
				if(actual.alt!=null) {
					if(tryEnterAlt()) {
						enterAlt();
					} else {
						System.out.println(id+" No he posido entrar a "+actual.alt.self);
						enterNext();
					}
				} else {
					enterNext();
				}
			} else {
				enterNext();
			}
		} else {
			enterNext();
		}
	}
	
	public Semaphore getSelectParking() {
		return selectParking;
	}
	/**
	* This method simulates the entry into a parking
	*/

	public void entrarParking() {
		
		actual.entry.release();
		System.out.println(id+" Voy a dormir en "+actual.ws.nombre+actual.self);
		try(Statement stm = connection.createStatement(); Statement stm2 = connection.createStatement();Statement stm3 = connection.createStatement();Statement stm4 = connection.createStatement()) {
			actual.ws.ocupado=true;
			sql.acquire();
			stm.executeUpdate("update vehicles set statee ='Sleeping' where vehicleID="+id);
			stm2.executeUpdate("update vehicles set velocity = 0 where vehicleID="+id);
			sql.release();
			actual.ws.exit.acquire();
			System.out.println(id+" Me han despertado para hacer un trabajo"+actual.self);
			sql.acquire();
			stm3.executeUpdate("update vehicles set statee ='Working' where vehicleID="+id);
			stm4.executeUpdate("update vehicles set velocity = 100 where vehicleID="+id);
			sql.release();
			actual.entry.acquire();
			actual.ws.ocupado=false;
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			LOGGER.log(LOGGER.getLevel(),e.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.log(LOGGER.getLevel(),e.toString());
		}
	}
	/**
	* @return true if the vehicle arrives to the final destiny 
	*/
	public boolean llegada() {
		if(actual.self.equals(objetivo)) {
			
			try {
				actual.ws.entry.acquire();
				System.out.println(id+": He llegado al almacen---->"+objetivo);
				Thread.sleep(1000);
				actual.ws.entry.release();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				LOGGER.log(LOGGER.getLevel(),e.toString());
			}
			
			fin=salida.self;
		} else if(actual.self.equals(salida.self)) {
			System.out.println(id+": He llegado ha salida----->"+salida.self);
			if(salida.ws.ocupado) {
				salida.ws.exit.release();
				try {
					salida.ws.entry.acquire();
					salida.ws.ocupado=true;
					manager.entregar(pedidoID, productoID);
					try(Statement stm = connection.createStatement(); Statement stm2 = connection.createStatement();Statement stm3 = connection.createStatement();Statement stm4 = connection.createStatement()) {
						sql.acquire();
						stm.executeUpdate("update vehicles set statee ='sleeping' where vehicleID="+id);
						stm2.executeUpdate("update vehicles set velocity = 0 where vehicleID="+id);
						sql.release();
						salida.ws.exit.acquire();
						System.out.println("################"+id+"Me han hecho salir");
						buscarParking();
						salida.ws.ocupado=false;
						sql.acquire();
						stm3.executeUpdate("update vehicles set statee ='Working' where vehicleID="+id);
						stm4.executeUpdate("update vehicles set velocity= 100 where vehicleID="+id);
						sql.release();
						salida.ws.entry.release();
						System.out.print("Voy a salir de ocupado");
					}
					
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					LOGGER.log(LOGGER.getLevel(),e.toString());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					LOGGER.log(LOGGER.getLevel(),e.toString());
				}
			} else {
				try {
					salida.ws.entry.acquire();
					salida.entry.release();
					salida.ws.ocupado=true;
					manager.entregar(pedidoID, productoID);
					try(Statement stm = connection.createStatement(); Statement stm2 = connection.createStatement();Statement stm3 = connection.createStatement();Statement stm4 = connection.createStatement()) {
						sql.acquire();
						stm.executeUpdate("update vehicles set statee ='sleeping' where vehicleID="+id);
						stm2.executeUpdate("update vehicles set velocity = 0 where vehicleID="+id);
						sql.release();
						salida.ws.exit.acquire();
						System.out.println("################"+id+"Me han hecho salir");
						buscarParking();
						salida.ws.ocupado=false;
						sql.acquire();
						stm3.executeUpdate("update vehicles set statee ='Working' where vehicleID="+id);
						stm4.executeUpdate("update vehicles set velocity = 100 where vehicleID="+id);
						sql.release();
						salida.ws.entry.release();
						System.out.print("Voy a salir de desocupado");
					}
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					LOGGER.log(LOGGER.getLevel(),e.toString());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					LOGGER.log(LOGGER.getLevel(),e.toString());
				}
			}
			
		} else if(actual.self.equals(parking)) {
			entrarParking();
			System.out.print("He entrado a parking");
		}
		
		return true;
	}
	
	public void setProductoID(int productoID) {
		this.productoID = productoID;
	}

	public void setPedidoID(int pedidoID) {
		this.pedidoID = pedidoID;
	}

	public Semaphore getSql() {
		return sql;
	}

	public Segmento getSalida() {
		return salida;
	}

	public Point getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(Point objetivo) {
		this.objetivo = objetivo;
	}
	/**
	* This method is a loop of 0.1 seconds where if the vehicle arrives to the final objective it executes the arrival() function
	* otherwise it will execute move() function
	*/
	@Override
	public void run() {
		boolean bool = true;
		while(bool) {
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				LOGGER.log(LOGGER.getLevel(),e.toString());
			}
			if(actual.self.equals(fin)) {
				llegada();
				
			} else {
				mover();
			}
		}
	}
	
	/**
	* @return true if the vertical segment is available, otherwise it will return false
	*/
	public boolean tryEnterAlt() {
		boolean done=false;
		try {
			done=actual.alt.entry.tryAcquire(1, TimeUnit.NANOSECONDS);
			//System.out.println(done);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			LOGGER.log(LOGGER.getLevel(),e.toString());
		}
		
		return done;
	}
	/**
	* This method simulates the entrance of a vehicle into a vertical segment
	*/
	public void enterAlt() {
		Segmento aux=actual;
		System.out.println(id+" Estoy en "+ actual.self);
		try (Statement stm = connection.createStatement();Statement stm2 = connection.createStatement()){
			sql.acquire();
			stm.executeUpdate("update vehicles set velocity = 100 where vehicleID="+id);
			sql.release();
			Thread.sleep(100);
			if(actual.alt.entry.availablePermits()==0) {
				sql.acquire();
				stm2.executeUpdate("update vehicles set velocity = 50 where vehicleID="+id);
				sql.release();
				System.out.println("He reducido velocidad");
				Thread.sleep(200);
			} else {
				Thread.sleep(100);
			}
		} catch (InterruptedException e1) {
			Thread.currentThread().interrupt();
			LOGGER.log(LOGGER.getLevel(),e1.toString());
			LOGGER.log(LOGGER.getLevel(),e1.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.log(LOGGER.getLevel(),e.toString());
		}
		
		actual=actual.alt;
		try (Statement stm=connection.createStatement();Statement stm2=connection.createStatement();){
			sql.acquire();
			String s1 = "update vehicles set position = '"+actual.ws.nombre+"' where vehicleID="+id;
			String s = "update vehicles set segmentCounter = (select segmentCounter from (select * from vehicles) as a where vehicleID = "+id+") + 1 where vehicleID="+id;
			stm.executeUpdate(s1);
			stm2.executeUpdate(s);
			sql.release();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			LOGGER.log(LOGGER.getLevel(),e.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.log(LOGGER.getLevel(),e.toString());
		}
		aux.entry.release();
	}
	/**
	* This method simulates the entrance of the vehicle into a horizontal segment
	*/
	public void enterNext() {
		Segmento aux= actual;
		System.out.println(id+" Estoy en "+ actual.self);
		try(Statement stm=connection.createStatement();Statement stm2=connection.createStatement();) {
			sql.acquire();
			stm.executeUpdate("update vehicles set velocity = 100 where vehicleID="+id);
			sql.release();
			Thread.sleep(100);
			if(actual.next.entry.availablePermits()==0) {
				sql.acquire();
				stm2.executeUpdate("update vehicles set velocity = 50 where vehicleID="+id);
				sql.release();
				System.out.println("He reducido velocidad");
				Thread.sleep(200);
			} else {
				Thread.sleep(100);
			}
		} catch (InterruptedException e1) {
			Thread.currentThread().interrupt();
			LOGGER.log(LOGGER.getLevel(),e1.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.log(LOGGER.getLevel(),e.toString());
		}
		
		try {
			actual.next.entry.acquire();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			LOGGER.log(LOGGER.getLevel(),e.toString());
		}
		actual=actual.next;
		try(Statement stm=connection.createStatement();Statement stm2=connection.createStatement();) {
			sql.acquire();
			String s1 = "update vehicles set position = '"+actual.ws.nombre+"' where vehicleID="+id;
			String s = "update vehicles set segmentCounter = (select segmentCounter from (select * from vehicles) as a where vehicleID = "+id+") + 1 where vehicleID="+id;
			stm.executeUpdate(s1);
			stm2.executeUpdate(s);
			sql.release();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			LOGGER.log(LOGGER.getLevel(),e.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.log(LOGGER.getLevel(),e.toString());
		}
		aux.entry.release();
	}

	public Segmento getActual() {
		// TODO Auto-generated method stub
		return actual;
	}

	public Point getFin() {
		return fin;
	}

	public void setFin(Point fin) {
		this.fin = fin;
	}

	public void setParking(Point point) {
		// TODO Auto-generated method stub
		parking=point;
	}

	public void setActual(Segmento segmento) {
		actual=segmento;
		
	}
	
	
	
}
