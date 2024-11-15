package MVC_Ejercicio18.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion { //Es un singleton
	private static Conexion instance;
	private static Connection conn;

	private Conexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "admin");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Conexion getInstance() {
		if(instance==null) {
			instance = new Conexion();
		}
		return instance;
	}

	public static Connection getConn() {
		return conn;
	}
	
	public static void cerrarCon() {
		try {
			instance.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
