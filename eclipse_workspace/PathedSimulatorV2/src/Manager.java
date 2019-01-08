import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Manager extends Thread{
	
	ArrayList<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
	Semaphore sql;
	
	Connection connection = null;
	Statement statement = null;
	String serverName = "localhost";
	String dataBaseName = "amuzon";
	String url = "jdbc:mysql://";
	String username = "root";
	String password = "";
	String connectionString = null;
	
	public Manager(ArrayList<Vehiculo> listaVehiculos, Semaphore sql) {
		this.listaVehiculos=listaVehiculos;
		this.connectionString = url + serverName + "/" + dataBaseName;
		this.sql=sql;
		connect();
		initialCheck();
		leer();
	}
	
	public void initialCheck() {
		String s = "select update_time from information_schema.tables where table_schema='amuzon' and table_name='orders_product'";
		try {
			ResultSet rs=statement.executeQuery(s);
			if(rs.next()) {
				System.out.println(rs.getDate("update_time").getTime());
				System.out.println(rs.getString("update_time"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//1546902000000
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
	
	public void leer() {
		ResultSet rs;
		String query = "select * from orders_product";
		Statement stm;
		try {
			sql.acquire();
			stm = connection.createStatement();
			rs = stm.executeQuery(query);
			while(rs.next()) {
				int i = rs.getInt("isAssigned");
				System.out.println("aaaaaaaaaaaaaaaa"+i);
			}
			
			sql.release();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	@Override
	public void run() {
		boolean estado=false;
		while(!estado) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(Vehiculo v:listaVehiculos) {
				if(v.estado.equals("Sleeping")) {
					//System.out.println(v.id);
					//v.fin=v.init;
					//v.actual.ws.exit.release();
					//estado=true;
					//System.out.println("He despertado ha "+v.id+" para que vaya a"+v.fin);
					//break;
				}
			}
		}
		
	}

}
