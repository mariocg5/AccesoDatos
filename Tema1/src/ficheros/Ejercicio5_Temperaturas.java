package ficheros;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio5_Temperaturas {
	public static final String RUTA = System.getProperty("user.dir") + File.separator + "src" + File.separator + "data"
			+ File.separator;
	private static final String nombreArchivo = "meses.dat";
	private static final int TAMANIO = 13;
	private static RandomAccessFile raf;
	private static String[] meses = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov",
			"Dec" };
	private static int[][] temperaturas = { { 10, -1 }, { 12, 0 }, { 15, 2 }, { 18, 5 }, { 22, 8 }, { 25, 12 },
			{ 28, 15 }, { 30, 18 }, { 26, 14 }, { 20, 10 }, { 15, 5 }, { 12, 0 } };

	public static void main(String[] args) {
		try {
			raf = new RandomAccessFile(new File(RUTA + nombreArchivo), "rw");
			menu();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Método que escribe los meses y sus temperaturas maximas y minimas
	 * correspondientes Modifica las temperaturas máximas y minimas del mes deseado
	 * 
	 * @param valores Si vacío escribe meses y temperaturas correspondiente en el
	 *                fichero Si tiene 3 valores, el primero es para seleccionar el
	 *                mes, el segundo para la temperatura máxima y el tercero para
	 *                la temperatura minima
	 */

	public static void escribir(int... valores) {
		try {
			if (valores.length == 0) {
				raf.setLength(0);
				for (int i = 0; i < meses.length; i++) {
					raf.writeUTF(meses[i]);
					raf.writeInt(temperaturas[i][0]);
					raf.writeInt(temperaturas[i][1]);
				}
			} else if (valores.length == 3) {
				int posicion = (valores[0] - 1) * TAMANIO;
				if (posicion >= raf.length() || posicion < 0) {
					System.out.println("El mes introducido no existe");
				} else {
					raf.seek(posicion + 5); // se mueve a la posicion de la primera temperatura
					raf.writeInt(valores[1]);
					raf.writeInt(valores[2]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que permite leer los meses con sus temperaturas y permite saber a que
	 * mes corresponde una posición
	 * 
	 * @param valores Si vacío, muestra los meses con sus temperaturas Si tiene un
	 *                valor, muestra el mes correspondiente a esa posición
	 */
	public static void leer(int... valores) {
		try {
			if (valores.length == 0) {
				raf.seek(0);
				while (raf.getFilePointer() != raf.length()) {
					System.out.println(raf.readUTF() + " : ");
					System.out.println("La temperatura máxima es: " + raf.readInt());
					System.out.println("La temperatura mínima es: " + raf.readInt());
				}
			} else if (valores.length == 1) {
				int posicion = (valores[0] - 1) * TAMANIO;
				if (posicion >= raf.length() || posicion < 0) {
					System.out.println("La posicion no es correcta");
				} else {
					raf.seek(posicion);
					System.out.println("El mes en la posicion " + valores[0] + " es " + raf.readUTF());
					System.out.println("Su temperatura máxima es: " + raf.readInt());
					System.out.println("Su temperatura mínima es: " + raf.readInt());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Menú que muestra opciones para interactuar con el programa, desde leer el
	 * fichero hasta modificar sus valores
	 */
	public static void menu() {
		Scanner sc = new Scanner(System.in);
		boolean salir = false;
		int opcion, mes, tMax, tMin;
		while (!salir) {
			System.out.println("\n1. Leer fichero");
			System.out.println("\n2. Consultar que mes es según su posicion");
			System.out.println("\n3. Modificar valor de temperaturas");
			System.out.println("\n4 Salir");
			try {
				System.out.println("Escriba una de las opciones");
				opcion = sc.nextInt();

				switch (opcion) {
				case 1:
					System.out.println("Leyendo el contenido del fichero");
					leer();
					break;
				case 2:
					System.out.println("Consultando una posicion del fichero");
					System.out.println("Indique la posicion al consultar");
					leer(sc.nextInt());
					break;
				case 3:
					System.out.println("Modificando el valor de una posicion");
					System.out.println("Indique la posicion del mes a modificar");
					mes = sc.nextInt();
					System.out.println("Indique el nuevo valor de la temperatura máxima: ");
					tMax = sc.nextInt();
					System.out.println("Indique el valor de la temperatura mínima");
					tMin = sc.nextInt();
					escribir(mes, tMax, tMin);
					break;
				case 4:
					salir = true;
					break;
				default:
					System.out.println("Solo números entre 1 y 4");
					sc.next();
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Debes escribir un número del 1 al 4");
				sc.next();
			}

		}
	}
}
