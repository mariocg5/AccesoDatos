package dbRelacional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utilidades.utilidades;

public class Ejercicio17 {
	private static Connection conn;
	private final static String DB = "dBEmpresaSQLite.dat";

	public Ejercicio17() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		establecerConexion_MySQL();
		// listarDepartamentos();
		aniadirDatos();
		listarDepartamentos();

		cerrarConexión();

	}

	/**
	 * Ejecuta el script "Ejercicio1_MySQL_Ampliacion.sql" en MySQL Workbench. 
	 * Añade al ejercicio anterior un método que mediante una transacción añada un
	 * empleado a la tabla empleados. 
	 * Este método tendrá como parámetros de entrada el nombre y los apellidos del empleado 
	 * así como el NOMBRE DEL DEPARTAMENTO al que pertenecerá. 
	 * Ten en cuenta que deberás buscar el id del departamento y que esté podría no existir.
	 * En ese caso, se informará al usuario, se le mostrará un listado de los nombres de 
	 * los departamentos existentes y se le darán las siguientes opciones:
	 * - añadir el nuevo departamento a la base de  datos y asignar el empleado al mismo 
	 * - asignar al empleado a un departamento existente y mostrar el número de empleados de ese departamento.
	 * - abortar toda la operación sin añadir ni empleado ni departamento
	 * 
	 * Crea otro método que nos diga el número de empleados de la empresa.
	 * 
	 * @param nombre
	 * @param apellido
	 * @param apellido2
	 * @param dpto
	 */
	// Consulta en la base de datos
	private static void aniadirEmpleado(String nombre, String apellido, String apellido2, String dpto) {
		try {
			PreparedStatement sentenciaAltaEmpleado= conn.prepareStatement("INSERT INTO empleados (`nombre`, `apellido1`, `apellido2`, `departamento`) "
					+ "VALUES (?, ?, ?, ?);", PreparedStatement.RETURN_GENERATED_KEYS);
			sentenciaAltaEmpleado.setString(2, nombre);
			sentenciaAltaEmpleado.setString(3, apellido);
			sentenciaAltaEmpleado.setString(4, apellido2);
			sentenciaAltaEmpleado.setString(5, dpto);
			
			ResultSet idDptos = sentenciaAltaEmpleado.getGeneratedKeys();
			idDptos.next();
			int idDpto = idDptos.getInt(5);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static int borrarDatos(int numDept) {
		// Forma de comprobar si se eliminan o no los datos con un operador ternario en
		// el main
		try {
			PreparedStatement sentencia = conn.prepareStatement("DELETE FROM departamentos WHERE dept_no = ? ;");
			sentencia.setInt(1, numDept);
			return sentencia.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	private static void aniadirDatos() {
		try {
			PreparedStatement sentencia = conn
					.prepareStatement("INSERT INTO departamentos (dnombre, loc) VALUES (?, ?);");
			sentencia.setString(1, "Mondongo");
			sentencia.setString(2, "Matanassa");
			sentencia.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void modificarDatos() {
		try {
			PreparedStatement sentencia = conn
					.prepareStatement("UPDATE departamentos SET dnombre = ?, loc = ? " + "WHERE dept_no =?;");
			sentencia.setString(1, "Deporte");
			sentencia.setString(2, "Alar");
			sentencia.setInt(3, 40);
			sentencia.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void listarDepartamentos() {
		try {
			PreparedStatement sentencia = conn.prepareStatement("SELECT * FROM departamentos;");
			ResultSet rs = sentencia.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void cerrarConexión() {
		try {
			conn.close();
			System.out.println("Conexión cerrada");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void establecerConexion_MySQL() {
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
