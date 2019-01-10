package oangua;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Producer extends Thread{
	Semaphore sql;
	Scanner teclado = new Scanner(System.in);
	
	Connection connection = null;
	Statement statement = null;
	String serverName = "localhost";
	String dataBaseName = "amuzon";
	String url = "jdbc:mysql://";
	String username = "root";
	String password = "";
	String connectionString = null;
	
	public Producer(Semaphore sql) {
		this.sql=sql;
		this.connectionString = url + serverName + "/" + dataBaseName;
		connect();
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
	
	@Override
	public void run() {
		int i=1;
		while(i!=0) {
			i=teclado.nextInt();
			produce(i);
		}
	}
	
	public void produce(int i) {
		String s = "insert into orders_product values(10,"+i+",1,0,1,1)";
		try {
			statement.executeUpdate(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
