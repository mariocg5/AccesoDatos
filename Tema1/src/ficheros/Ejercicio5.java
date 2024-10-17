package ficheros;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Ejercicio5 {

	public final static String RUTA = System.getProperty("user.dir") + System.getProperty("file.separator") + "src"
			+ System.getProperty("file.separator)" + "data" + System.getProperty("file.separator"));
	private static final String nombreArchivo = "numeros.dat";
	private static final int TAMANIO = 4;
	private static RandomAccessFile raf;

	public static void main(String args[]) {
		try {
			raf = new RandomAccessFile(new File(RUTA + nombreArchivo), "rw");
			
			raf.close();
		} catch (FileNotFoundException e) {
			System.out.println("El fichero no existe");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de lectura escritura");
			e.printStackTrace();
		}
		
	}
		

	public static void escribir() {
		try {
			raf.setLength(0);
			for (int i = 1; i <= 100; i++) {
				raf.writeInt(i);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void leer() {
		try {
			raf.seek(0);
			while (raf.getFilePointer() != raf.length()) {
				System.out.println(raf.readInt() + " ");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método que muestra la posicion del elemento que ocupa el puesto lugar en el
	 * fichero
	 * 
	 * @param pos lugar número de elemento a mostrar
	 */
	private static void consultar(int lugar) {
		try {
			
				// Calculo la posición
				int posicion = (lugar-1) * TAMANIO;
				// Compruebo que la posicion sea correcta no superior al tamaño del fichero ni
				// inferior a 0

				if (posicion >= raf.length() || posicion < 0) {
					System.out.println("La posición no es correcta");
				} else {
					raf.seek(posicion);
					System.out.println("Valor en la posición " + lugar + ":" + raf.readInt());
				}		
		} catch (IOException e) {
			System.out.println("Error de lectura o escritura");
			e.printStackTrace();
		}
	}
	
/**
 * Método que añade un número al final del fichero
 * @param valor número a añadir
 */
	public static void anadirFinal(int valor) {		
		try {		
			raf.seek(raf.length());
			raf.writeInt(valor);

		} catch (IOException e) {
			System.out.println("Error de lectura o escritura");
			e.printStackTrace();
		}
	}
	/**
	 * Método que modifica un valor o un número en una deretminada posicion
	 * @param valor nuevo numero a escribir en la nueva posicion
	 * @param lugar
	 */

	public static void modificarValor(int valor, int lugar) {
		try {
			
			// Calculo la posición
			int posicion = (lugar-1) * TAMANIO;
			// Compruebo que la posicion sea correcta no superior al tamaño del fichero ni
			// inferior a 0

			if (posicion >= raf.length() || posicion < 0) {
				System.out.println("La posición no es correcta");
			} else {
				raf.seek(posicion);
				raf.writeInt(valor);
			}		
	} catch (IOException e) {
		System.out.println("Error de lectura o escritura");
		e.printStackTrace();
	}
		
	}
	
	public static void menu() {
		Scanner sc = new Scanner (System.in);
		boolean salir = false;
		int opcion,lugar;
		while (!salir) {
			System.out.println("\n1. Leer fichero");
			System.out.println("\n2. Escribir fichero");
			System.out.println("\n3. Añadir al final del fichero");
			System.out.println("\n4. Modificar valor del fichero");
			System.out.println("\n5 Salir");
			try {
			System.out.println("Escriba una de las opciones");
			opcion = sc.nextInt();
			
			switch(opcion) {
			case 1: 
				System.out.println("Leyendo el contenido del fichero");
				leer();
				break;
			case 2: 
				System.out.println("Consultando una posicion del fichero");
				System.out.println("Indique la posicion al consultar");
				consultar(sc.nextInt());
				break;
			case 3: 
				System.out.println("Añadiendo elemento al final del fichero");
				System.out.println("Indique el valor a añadir");
				anadirFinal(sc.nextInt());
				break;
			case 4: 
				System.out.println("Modificando el valor de una posicion");
				System.out.println("Indique la posicion a modificar");
				lugar =sc.nextInt();
				System.out.println("Indique el nuevo valor: ");
				modificarValor(sc.nextInt(), lugar);
				break;
			case 5: 
				salir=true;
				break;
			default:
				System.out.println("Solo números entre 1 y 5");
				sc.next();
				break;			
			}
		}catch (InputMismatchException e){
			System.out.println("Debes escribir un número del 1 al 5");
			sc.next();
		}
		
	}
}}
