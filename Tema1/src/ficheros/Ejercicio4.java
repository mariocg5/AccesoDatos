package ficheros;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * Leer el fichero imagen.jpg y copiar su contenido en el fichero imagen_copia.gif.
 */
public class Ejercicio4 {

	public final static String RUTA = System.getProperty("user.dir") + System.getProperty("file.separator") + "src"
			+ System.getProperty("file.separator)" + "data" + System.getProperty("file.separator"));
	private final static String DOCTRABAJO_IN = "imagen.jpg";
	private final static String DOCTRABAJO_OUT = "imagen_copia.jpg";

	public static void main(String args[]) {
		
		File f = new File(RUTA+DOCTRABAJO_IN);
		File f2 = new File(RUTA+DOCTRABAJO_OUT);
		
		try {
			FileInputStream flujoEntrada = new FileInputStream(f);
			FileOutputStream flujoSalida = new FileOutputStream(f2);
			
			flujoSalida.write(flujoEntrada.readAllBytes());
			flujoEntrada.close();
			flujoSalida.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}
