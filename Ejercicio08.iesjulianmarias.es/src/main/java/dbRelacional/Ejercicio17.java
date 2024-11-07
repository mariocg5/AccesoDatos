package dbRelacional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejercicio17 {

    private static Connection conn;

    public Ejercicio17() {
    }

    public static void main(String[] args) {
        establecerConexion();
        listarDptos();
        cerrarConexion();
    }

    private static void listarDptos() {
        String query = "SELECT * FROM empresa.departamentos";
        try (PreparedStatement sentencia = conn.prepareStatement(query);
             ResultSet rS = sentencia.executeQuery()) {

            while (rS.next()) {
                System.out.println(rS.getInt(1) + " " + rS.getString(2) + " " + rS.getString(3));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los departamentos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void cerrarConexion() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void establecerConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "admin");
            System.out.println("Conexión establecida.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver JDBC: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al establecer la conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
