package ficheros;
import java.io.File;

public class manejoFicheros {
	
	public static void main (String[]args) {
		try {
		File f = new File(System.getProperty("user.dir"));
		File []hijos = f.listFiles();
		
		System.out.println("Nombre del directorio axtual : "+ f.getName() +
				" tiene " + hijos.length + " ficheros y directorios contenidos. \n\t Contenido:");
		
		for(File f2 : hijos) {
			System.out.println("\t\t" + f2.getName() + "------>\t" + (f2.isFile()?"F":"D"));
		}
	
	}catch (Exception e) {
		System.out.println(e);
	}
}
}
