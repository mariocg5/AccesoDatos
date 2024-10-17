package ficheros.ejercicio_06bis;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ficheros.ejercicio6.Persona; //importo las clases de persona y MyObjectOutputStream para poder trabajar con ellas
import ficheros.ejercicio6.MyObjectOutputStream;

public class Ejercicio_06bis {
	public static final String RUTA = System.getProperty("user.dir") + File.separator + "src" + File.separator + "data"
			+ File.separator;
	private static final String DOCTRABAJO_IN = "FicheroPersonasSerializado.dat";
	private static final String DOCTRABAJO_OUT = "datosPersonalesc2.dat"; // fichero en el que copian los datos
	private static ObjectOutputStream oOS;

	/**
	 * Inicializa el ObjectOutputStream para escribir objetos en el fichero de
	 * salida. Si el fichero ya existe y tiene contenido, usa MyObjectOutputStream
	 * para evitar escribir un nuevo encabezado.
	 */
	public static void inicializar() {
		try {
			File file = new File(RUTA + DOCTRABAJO_OUT);
			if (file.exists() && file.length() > 0) {
				oOS = new MyObjectOutputStream(new FileOutputStream(file, true));
			} else {
				oOS = new ObjectOutputStream(new FileOutputStream(file, true));
			}
		} catch (IOException e) {
			System.out.println("Error al abrir el fichero");
			e.printStackTrace();
		}
	}

	/**
	 * Escribe un objeto de la clase persona_v2 en un stream de salida
	 * 
	 * @param persona objeto a escribir
	 */
	public static void escribirObjeto(Persona_v2 persona_v2) {
		try {
			oOS.writeObject(persona_v2);
		} catch (IOException e) {
			System.out.println("Error al encontrar el fichero");
			e.printStackTrace();
		}

	}

	/**
	 * Lee los objetos de la clase Persona, los convierte en objetos de persona_v2 y
	 * los escribe en el fichero de salida
	 */
	public static void copiarObjeto() {
		try {
			ObjectInputStream oIS = new ObjectInputStream(new FileInputStream(new File(RUTA + DOCTRABAJO_IN)));
			try {
				Persona persona = new Persona();
				while (true) {
					persona = (Persona) oIS.readObject();
					Persona_v2 persona_v2 = new Persona_v2(persona.getNombre(), persona.getApellido1(),
							persona.getApellido2(), persona.getNacimiento());
					escribirObjeto(persona_v2);
				}

			} catch (ClassNotFoundException e) {
				System.out.println("Error en la lectura del fichero");
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				System.err.println("Error al encontrar el fichero");
				e.printStackTrace();
			} catch (EOFException e) {

			} finally {
				oIS.close();
			}
		} catch (IOException e) {
			System.out.println("Error al copiar el fichero");
			e.printStackTrace();
		}
	}

	/**
	 * Lee y muestra los objetos Persona_v2 desde el fichero de salida.
	 */
	public static void leerPersona_v2() {
		try {
			ObjectInputStream oIS = new ObjectInputStream(new FileInputStream(new File(RUTA + DOCTRABAJO_OUT)));
			try {
				while (true) {
					Persona_v2 persona_v2 = (Persona_v2) oIS.readObject();
					System.out.println(persona_v2);
				}
			} catch (ClassNotFoundException e) {
				System.out.println("Error en la lectura del fichero");
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				System.err.println("Error al encontrar el fichero");
				e.printStackTrace();
			} catch (EOFException e) {

			} finally {
				oIS.close();
			}
		} catch (IOException e) {
			System.out.println("Error en la lectura del fichero");
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		inicializar();
		copiarObjeto();
		leerPersona_v2();
		try {
			oOS.close();
		} catch (IOException e) {
			System.out.println("Error al cerrar el fichero");
			e.printStackTrace();
		}

	}

}
