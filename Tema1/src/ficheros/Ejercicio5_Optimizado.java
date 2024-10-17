package ficheros;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Ejercicio5_Optimizado {

	public final static String RUTA = System.getProperty("user.dir") + System.getProperty("file.separator") + "src"
			+ System.getProperty("file.separator)" + "data" + System.getProperty("file.separator"));
	private static final String nombreArchivo = "numeros_optimizado.dat";
	private static final int TAMANIO = 4;
	private static RandomAccessFile raf;

	public static void main(String args[]) {
		try {
			raf = new RandomAccessFile(new File(RUTA + nombreArchivo), "rw");
			menu();
			raf.close();
		} catch (FileNotFoundException e) {
			System.out.println("El fichero no existe");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de lectura escritura");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Escribe el valor del 1 al 100 en un fichero aleatorio o modifica el valor de una posicion determinada
	 * (ambos valores, posicion y nuevo valor de una posicion, se pasan como parámetros) o añade al final 
	 * del fichero un valor que se pasa como parametro.
	 * @param valores
	 * 		si vacio inicializa el fichero con valores del 1 al 100
	 * 		si tiene un único valor, se añade al final
	 * 		si tiene dos valores, el primero es tomado como la posicion a sobreescribir y el segundo como nuevo valor
	 */

	public static void escribir(int... valores) {
		try {
			if (valores.length == 0) {
				raf.setLength(0);
				for (int i = 1; i <= 100; i++) {
					raf.writeInt(i);
				}
			} else if (valores.length == 1) {
				raf.seek(raf.length());
				raf.writeInt(valores[0]);
			} else if (valores.length == 2) {
				int posicion = (valores[0] - 1) * TAMANIO;
				if (posicion > raf.length() || posicion < 0) {
					System.out.println("La posicion indicada no es la correcta");
				} else {
					raf.seek(posicion);
					raf.writeInt(valores[1]);
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
/**
 * Lee el contenido del fichero o el valor de una posicion que se pase por parametro
 * @param valores
 * 		si vacío, lee y muestra el contenido de todo el fichero
 * 		si tiene un unico valor, muestra el elemento contenido en ese lugar
 */
	

	public static void leer(int... valores) {
		try {
			if(valores.length==0) {
				raf.seek(0);
				while (raf.getFilePointer() != raf.length()) {
					System.out.println(raf.readInt() + " ");
				}
			} else if(valores.length==1) {
				int posicion = (valores[0]-1)*TAMANIO;
				
				if(posicion>raf.length() || posicion<0) {
					System.out.println("La posicion indicada no es correcta");
				} else {
					raf.seek(posicion);
					System.out.println("Valor en la posicion "+valores[0] + ": "+raf.readInt());
				}
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
				leer(sc.nextInt());
				break;
			case 3: 
				System.out.println("Añadiendo elemento al final del fichero");
				System.out.println("Indique el valor a añadir");
				escribir(sc.nextInt());
				break;
			case 4: 
				System.out.println("Modificando el valor de una posicion");
				System.out.println("Indique la posicion a modificar");
				lugar =sc.nextInt();
				System.out.println("Indique el nuevo valor: ");
				escribir(lugar,sc.nextInt());
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
