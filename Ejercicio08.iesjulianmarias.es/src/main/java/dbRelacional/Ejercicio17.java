package dbRelacional;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import utilidades.utilidades;
 
public class Ejercicio17 {
 
	private static Connection conn;
	private final static String DB = "dBEmpresaSQLite.dat";
	
 
	public Ejercicio17() {
 
	}
 
	private static void aniadirEmpleado(String nombre, String apellido, String apellido2, String nombreDepartamento) {
 
		try {
			
			int deptId = buscarIdDepartamento(nombreDepartamento);
 
			if (deptId == -1) { // si no existe el departamento
				System.out.println("El departamento buscado no existe");
				listarDepartamentos();
 
				Scanner sc = new Scanner(System.in);
				System.out.println("Opciones:");
				System.out.println("1. Añadir un nuevo departamento y asignar el empleado");
				System.out.println("2. Asignar el empleado a un departamento existente");
				System.out.println("3. Abortar la operación");
				System.out.print("Seleccione una opción: ");
				int opcion = sc.nextInt();
				sc.nextLine();
 
				switch (opcion) {// Añadir nuevo departamento y asignar empleado
				case 1:
					conn.setAutoCommit(false); // uso la transaccion en el caso en el que se realizan dos consultas para que si falla una no se haga ninguna insercion
					deptId = aniadirDepartamento(nombreDepartamento);
					if (deptId != -1) {
						aniadirEmpleados(nombre, apellido, apellido2, deptId);
						System.out.println("Departamento y empleados añadidos correctamente");
					} else {
						System.out.println("El departamento no se pudo añadir correctamente");
					}
					conn.commit();
					conn.setAutoCommit(true);
					break;
				case 2: // Asignar al empleado a un departamento existente
					System.out.println("Ingrese el ID de un departamento existente");
					deptId = sc.nextInt();
					sc.nextLine();
					aniadirEmpleados(nombre, apellido, apellido2, deptId);
					System.out.println("Empleado añadido al departamento");
					mostrarEmpleados(deptId);
					break;
				case 3:
					System.out.println("Operación abortada");
					conn.rollback();
					return;
 
				default:
					System.out.println("Opción no válida");
 
				}
 
			} else { // El departamento existe, añadir empleado
				aniadirEmpleados(nombre, apellido, apellido2, deptId);
				System.out.println("Empleado añadido al departamento");
				mostrarEmpleados(deptId);
			}
 
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
 
	private static void mostrarEmpleados(int numDept) {
		try {
			PreparedStatement sentencia = conn
					.prepareStatement("SELECT COUNT(*) AS total FROM empresa.empleados WHERE departamento = ?;");
			sentencia.setInt(1, numDept);
			ResultSet rs = sentencia.executeQuery();
			if (rs.next()) {
				int total = rs.getInt("total");
				System.out.println("Número de empleados en el departamento: " + total);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 
	private static void aniadirEmpleados(String nombre, String apellido1, String apellido2, int numDpto) {
		try {
			PreparedStatement sentencia = conn.prepareStatement(
					"INSERT INTO empresa.empleados (nombre, apellido1, apellido2, departamento) VALUES (?, ?, ?, ?);",
					Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, nombre);
			sentencia.setString(2, apellido1);
			sentencia.setString(3, apellido2);
			sentencia.setInt(4, numDpto);
			sentencia.executeUpdate();
 
			ResultSet rs = sentencia.getGeneratedKeys();
			if (rs.next()) {
				int nuevoIdEmpleado = rs.getInt(1);
				System.out.println("El empleado tiene un ID: " + nuevoIdEmpleado);
			} else {
				System.out.println("Error al generar el ID del nuevo empleado");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 
	private static int aniadirDepartamento(String nombreDepartamento) {
		try {
			PreparedStatement sentencia = conn.prepareStatement(
				"INSERT INTO empresa.departamentos (dnombre, loc) VALUES (?, 'Valladolid')",
				Statement.RETURN_GENERATED_KEYS
			);
			sentencia.setString(1, nombreDepartamento);
			sentencia.executeUpdate();
 
			ResultSet rs = sentencia.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	private static int buscarIdDepartamento(String nombreDepartamento) {
		try {
			PreparedStatement sentencia = conn.prepareStatement(
				"SELECT dept_no FROM empresa.departamentos WHERE dnombre = ?"
			);
			sentencia.setString(1, nombreDepartamento);
			ResultSet rs = sentencia.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; //si no existe el departamento devuelve -1
	}
 
	public static void main(String[] args) {
		establecerConexion();
		aniadirEmpleado("Bacari", "Perez", "Ruiz", "Badminton");
		cerrarConexión();
	}
 
	private static void listarDepartamentos() {
		try {
			PreparedStatement sentencia = conn.prepareStatement("SELECT * FROM empresa.departamentos;");
			ResultSet rs = sentencia.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	}
 
	private static void cerrarConexión() {
	    try {
	        if (conn != null) {
	            conn.close();
	            System.out.println("Conexión cerrada.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
 
	private static void establecerConexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "admin");
			System.out.println("Conexión establecida");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	}
 
	private static void borrarDatos() {
		try {
			PreparedStatement sentencia = conn
					.prepareStatement("DELETE FROM empresa.departamentos WHERE dnombre = ? ;");
			sentencia.setString(1, "Quejas");
			sentencia.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
	private static void aniadirDatos() {
		try {
			PreparedStatement sentencia = conn
					.prepareStatement("INSERT INTO empresa.departamentos (dept_no, dnombre, loc) VALUES (?, ?, ?);");
			sentencia.setInt(1, 50);
			sentencia.setString(2, "Quejas");
			sentencia.setString(3, "Valladolid");
			sentencia.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	}
 
	private static void modificarDatos() {
		try {
			PreparedStatement sentencia = conn
					.prepareStatement("UPDATE empresa.departamentos SET dnombre = ?, loc = ? " + "WHERE dept_no =?;");
			sentencia.setString(1, "Deporte");
			sentencia.setString(2, "Alar");
			sentencia.setInt(3, 40);
			sentencia.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	}
 


	private static void establecerConexion_SQLite() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + utilidades.RUTA + DB);
			System.out.println("Conexión establecida");
			try (PreparedStatement sentencia = conn.prepareStatement(
					"CREATE TABLE IF NOT EXISTS departamentos (" + "dept_no INTEGER PRIMARY KEY AUTOINCREMENT,"
							+ "dnombre VARCHAR(15)," + "loc VARCHAR(15)" + ");")) {
				sentencia.executeUpdate();
				System.out.println("Tabla creada.");
			}
		} catch (ClassNotFoundException e) {
			System.err.println("Error al cargar el driver JDBC: " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Error al establecer la conexión: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
