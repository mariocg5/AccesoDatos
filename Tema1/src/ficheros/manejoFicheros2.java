package ficheros;
import java.io.File;
import java.util.Scanner;

public class manejoFicheros2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Introduce el nombre de un fichero o directorio");
		String fileName = sc.nextLine();
		File dirAnalizado = new File(fileName);

		if (dirAnalizado.exists()) {
			System.out.print(dirAnalizado.isFile() ? "se trata de un fichero" : "Se trata de un directorio\n");
			if (dirAnalizado.isDirectory()) {
				File[] hijos = dirAnalizado.listFiles();
				for (File f2 : hijos) {
					System.out.println("\t\t" + f2.getName() + "------>\t" + (f2.isFile() ? "F" : "D"));
					infoFichero(f2);
				}
			}
		} else {
			System.out.println("El fichero o directorio no existe");
		}

	}

	public static void infoFichero(File f) {
		System.out.println(
				(f.isFile()
						? f.length() + " bytes " + (f.canRead() ? "r " : "- ") + (f.canWrite() ? "w " : "- ")
								+ (f.canExecute() ? "x " : "- ")
						: ""));
	}

}
