package ficheros;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Ejercicio3 {

	public static void main(String[] args) {
		File f = new File("3_Ejercicio.csv");
		String linea;
		try {

			FileReader fr = new FileReader(f);
			BufferedReader bf = new BufferedReader(fr);
		
			while ((linea = bf.readLine()) != null) {
				System.out.println(linea);
			}
					

			bf.close();
			fr.close();
			
			FileWriter fw = new FileWriter(f,true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("Mario;Cuevas;DAM;2");
			bw.newLine();
			bw.close();
			fw.close();
			
		} catch (Exception e) {
			System.out.println("El fichero no existe"+e);
		}

	}
}
