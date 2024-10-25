package ficheros.ejercicio6;

import java.io.EOFException;
import utilidades.utilidades;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;



public class Ejercicio6 {


	public static final String DOCTRABAJO_IN = "FicheroPersonasSerializado.dat";
	private static Scanner sc = new Scanner(System.in);
	private static ObjectOutputStream oOS;

	public static void inicializar() {
		try {
			File file = new File(utilidades.RUTA + DOCTRABAJO_IN);
			if (file.exists() && file.length() > 0) {
				oOS = new MyObjectOutputStream(new FileOutputStream(file, true)); // existe cabecera, hereda del object
			} else {
				oOS = new ObjectOutputStream(new FileOutputStream(file, true));
			}

		} catch (IOException e) {
			System.out.println("Error al abrir el fichero");
			e.printStackTrace();
		}
	}

	/**
	 * Pide por teclado los datos de la persona
	 * 
	 * @return objeto de la clase persona
	 */
	public static Persona obtenerDatos() {
		Persona persona = new Persona();
		System.out.println("DATOS DEL USUARIO\n\tNombre");
		persona.setNombre(new StringBuilder(sc.nextLine())); // transforma de string a StringBuilder
		System.out.println("\tPrimer Apellido");
		persona.setApellido1(new StringBuilder(sc.nextLine()));
		System.out.println("\tSegundo Apellido");
		persona.setApellido2(new StringBuilder(sc.nextLine()));
		System.out.println("\tFecha de Nacimiento (dd-mm-yyyy)");
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy"); // defino un formato o patron (dd-mm-yyyy)																			// para la fecha
			persona.setNacimiento(formatter.parse(sc.nextLine()));
		} catch (ParseException e) {
			System.out.println("Error en el formato de la fecha");
			e.printStackTrace();
		}
		return persona;
	}

	public static String getDoctrabajoIn() {
		return DOCTRABAJO_IN;
	}

	/**
	 * Escribe un objeto de la clase persona en un stream de salida
	 * 
	 * @param persona objeto a escribir
	 */
	public static void escribirObjeto(Persona persona) {
		try {
			oOS.writeObject(persona);
		} catch (FileNotFoundException e) {
			System.out.println("Error al encontrar el archivo");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error al escribir el fichero");
			e.printStackTrace();
		}
	}

	public static ObjectOutputStream getoOS() {
		return oOS;
	}

	public static void leerObjetos() { // void o persona, preguntar
		try {
			ObjectInputStream oIS = new ObjectInputStream(new FileInputStream(new File(utilidades.RUTA + DOCTRABAJO_IN)));
			try {
				Persona persona = new Persona();
				while (true) {
					persona = (Persona) oIS.readObject();
					System.out.println(persona);
				}
			} catch (ClassNotFoundException e) {
				System.out.println("Error de lectura en el fichero");
				e.printStackTrace();
			} catch (EOFException e) {

			} finally {
				oIS.close();
			}

		} catch (IOException e) {
			System.out.println("Error al abrir el fichero de lectura");
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		inicializar();
//		for (int i = 0; i < 5; i++) {
//			escribirObjeto(obtenerDatos());
//		}
		leerObjetos();
		try {
			oOS.close();
		} catch (IOException e) {
			System.out.println("Error al cerrar el fichero");
			e.printStackTrace();
		}
	}
}
