package ficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 

 
public class Ejercicio8 {
	public static final String RUTA = System.getProperty("user.dir") + File.separator + "src" + File.separator + "data"
			+ File.separator;
	private final static String DOCTRABAJO_OUT = "ejercicio08.csv";
	private final static String SEPARADOR=",";
	private final static String COMILLA = "\"";
	
	public static void main (String [] args) {
		BufferedReader br = null;
		try {
			br= new BufferedReader(new FileReader(RUTA+DOCTRABAJO_OUT));
			String line;
				while((line = br.readLine())!=null) {
					String[]fields = line.split(SEPARADOR);
					fields=eliminaComillas(fields);
					mostrar(fields);
				}
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void mostrar(String[] fields) {
		for (int i = 0; i < fields.length; i++) {
			System.out.println("\t---"+fields[i]);
		}
		System.out.println();
		
	}
	
	private static String[] eliminaComillas(String[]fields) {
		for (int i = 0; i < fields.length; i++) {
			fields[i] = fields[i].trim() //elimina los espacios en blanco al principio y final de la cadena
					.replaceAll("^"+COMILLA, "") // elimina una comilla al principio de la cadena
					.replaceAll(COMILLA+"$","")// elimina una comilla al final de la cadena
					.replaceAll(COMILLA+COMILLA,COMILLA); // Reemplaza dos comillas consecutivas por una sola
		}
		return fields;
	}
}
	