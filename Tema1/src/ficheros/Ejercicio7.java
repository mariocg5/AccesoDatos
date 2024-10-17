package ficheros;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Scanner;

public class Ejercicio7 {

	public static final String RUTA = System.getProperty("user.dir") + File.separator + "src" + File.separator + "data"
			+ File.separator;
	private final static String DOCTRABAJO_IN = "configuracion.props";
	private static Properties props = new Properties();

	public static void crearFichero() {
		try {
			FileOutputStream fOS = new FileOutputStream(RUTA + DOCTRABAJO_IN);
			props.setProperty("user", "usuario");
			props.setProperty("password", "micontraseña");
			props.setProperty("server", "localhost");
			props.setProperty("port", "3306");
			props.setProperty("date", "2022-11-12");
			props.setProperty("power", "true");
			props.store(fOS, "Fichero de configuración \n# Thu Nov 14 10:49:39 CET 2013");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void leerFichero() {
		try {
			FileInputStream fIS = new FileInputStream(RUTA + DOCTRABAJO_IN);
			props.list(System.out);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void modificarConfiguración() {
		Scanner sc = new Scanner(System.in);
		FileInputStream fIS = null;
		FileOutputStream fOS = null;
		try {
			fIS = new FileInputStream(RUTA + DOCTRABAJO_IN);
			fOS = new FileOutputStream(RUTA + DOCTRABAJO_IN);
			props.load(fIS);
			System.out.println("MODIFICANDO CONFIGURACIÓN");

			System.out.println("User: ");
			props.setProperty("user", sc.nextLine());

			System.out.println("Password: ");
			props.setProperty("password", sc.nextLine());

			System.out.println("server: ");
			props.setProperty("server", sc.nextLine());

			System.out.println("Valor a sumar al puerto");
			int puerto = Integer.valueOf(sc.nextLine());
			puerto = Integer.valueOf(props.getProperty("port")) + puerto;
			props.setProperty("port", String.valueOf(puerto));

			System.out.println("Nuevo mes para la fecha");
			int mes = Integer.valueOf(sc.nextLine());
			props.setProperty("date", String.valueOf(LocalDate.parse(props.getProperty("date")).withMonth(mes)));

			props.setProperty("power", String.valueOf(!Boolean.valueOf(props.getProperty("power"))));
			props.store(fOS, DOCTRABAJO_IN);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fIS.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fOS.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		crearFichero();
		leerFichero();
		modificarConfiguración();
		System.out.println("CONFIGURACION MODIFICADA");
		leerFichero();
	}

}
