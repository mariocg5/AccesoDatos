package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utilidades.utilidades;

/**
 * Modificar el ejercicio creando al menos dos métodos: buscarCiudad, le
 * pasaremos el nombre de una ciudad y nos mostrará los puntos de recarga
 * buscarMarca, le pasaremos como parámetro la marca y nos mostrará las
 * ubicaciones de los punetos de recarga
 * 
 * Añadir una nueva hoja al documento excel al hacer una nueva busqueda por
 * ciudad con el nombre de la propia ciudad
 */

public class Ejercicio9 {
	private final static String DOCTRABAJO_IN = "vehiculosElectricos.xlsx";
	private static Workbook wb;

	public static void main(String[] args) {
		try {
			wb = new XSSFWorkbook(new FileInputStream(new File(utilidades.RUTA + DOCTRABAJO_IN)));
			// puntosRecarga();
			
			// buscarMarca();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void buscarCiudad2(String ciudad) {
	    // Obtiene la primera hoja del libro de trabajo (wb)
	    Sheet hoja = wb.getSheetAt(0);
	    // Crea una nueva hoja en el libro de trabajo con el nombre de la ciudad
	    Sheet newHoja = wb.createSheet(ciudad);
	    // Inicializa el contador de filas
	    int numFila = 1, newNumFila = 0;

	    // Obtiene la primera fila que se va a procesar
	    Row fila = hoja.getRow(numFila);
	    // Imprime un encabezado para los puntos de recarga de la ciudad
	    System.out.println("\n\n\nPUNTOS DE RECARGA EN " + ciudad);
	    
	    // Comienza a iterar sobre las filas
	    while (fila != null) {
	        // Obtiene la celda que contiene la información de la ciudad en la columna 1 (índice 1)
	        Cell celdaBusqueda = fila.getCell(1);
	        
	        // Verifica si la celda no es nula
	        if (celdaBusqueda != null) {
	            // Verifica si la celda contiene el nombre de la ciudad entre paréntesis
	            if (celdaBusqueda.getStringCellValue().contains("(" + ciudad + ")")) {
	                // Imprime la información de la fila actual
	                System.out.println("----> " + fila.getCell(0).getStringCellValue() + "\t - " + fila.getCell(1).getStringCellValue());
	                // Crea una nueva fila en la nueva hoja y copia la información de la fila actual
	                Row newFila = newHoja.createRow(newNumFila++);
	                newFila.createCell(0).setCellValue((String) fila.getCell(0).getStringCellValue());
	                newFila.createCell(1).setCellValue((String) fila.getCell(1).getStringCellValue());
	            }
	        }
	        // Aquí se incrementa numFila correctamente, permitiendo avanzar a la siguiente fila
	        fila = hoja.getRow(numFila++);
	    }
	    try {
	        // Escribe los cambios en el archivo especificado
	        wb.write(new FileOutputStream(new File(utilidades.RUTA + DOCTRABAJO_IN)));
	    } catch (IOException e) {
	        // Manejo de excepción en caso de error de entrada/salida
	        e.printStackTrace();
	    }
	}
	public static void buscarMarca() {
		Scanner sc = new Scanner(System.in);

		Sheet hoja = wb.getSheetAt(0);
		int numFila = 1;
		Row fila = hoja.getRow(numFila); // empezamos la busqueda desde la fila 1 ya que la 0 es la cabecera

		System.out.println("Introduce el nombre de la marca: ");
		String marca = sc.nextLine();

		while (fila != null) {
			Cell celdaBusqueda = fila.getCell(2); // columna 1
			if (celdaBusqueda != null) {
				if (celdaBusqueda.getStringCellValue().contains(marca)) {
					// pasa el valor de la celda a String y vemos si contiene la palabra de la
					// ciudadd
					System.out.println("--->" + fila.getCell(0).getStringCellValue() + " - "
							+ fila.getCell(1).getStringCellValue()); // ponemos 1 porque es la columna de la
																		// dirección // direccion
				}
			}
			numFila++;
			fila = hoja.getRow(numFila);
		}

	}

	public static void buscarCiudad() {
		Scanner sc = new Scanner(System.in);
		Sheet hoja = wb.getSheetAt(0);
		int numFila = 1;
		Row fila = hoja.getRow(numFila); // empezamos la busqueda desde la fila 1 ya que la 0 es la cabecera

		System.out.println("Introduce el nombre de la ciudad: ");
		String ciudad = sc.nextLine();

		while (fila != null) {
			Cell celdaBusqueda = fila.getCell(0); // columna 1
			if (celdaBusqueda != null) {
				if (celdaBusqueda.getStringCellValue().contains(ciudad)) {
					// pasa el valor de la celda a String y vemos si contiene la palabra de la
					// ciudadd
					System.out.println("--->" + fila.getCell(0).getStringCellValue() + " - "
							+ fila.getCell(1).getStringCellValue()); // ponemos 1 porque es la columna de la
																		// dirección // direccion
				}
			}
			numFila++;
			fila = hoja.getRow(numFila);
		}

	}

	public static void puntosRecarga() {

		// Abro documento excel con extensión xsl. Si quisiera abrir un documento excel
		// con extensión xslx
		// tendría que instanciar un objeto de la clase XSSFWorkbook
		// tomo la primera página
		Sheet hoja = wb.getSheetAt(0);
		int numFila = 1;
		Row fila = hoja.getRow(numFila); // empezamos la busqueda desde la fila 1 ya que la 0 es la cabecera

		// muestro las localizaciones de los puntos de carga de la marca Iberdrola que
		// está en la columna 2
		System.out.println("Puntos de recarga de CYL: ");
		while (fila != null) {
			Cell celdaBusqueda = fila.getCell(2); // columna 2
			if (celdaBusqueda != null) {
				if (celdaBusqueda.getStringCellValue().contains("IBERDROLA")) {

					// pasa el valor de la celda a String y vemos si contiene la palabra Iberdrola
					System.out.println("--->" + fila.getCell(0).getStringCellValue() + " - "
							+ fila.getCell(1).getStringCellValue()); // ponemos 1 porque es la columna de la
																		// dirección // direccion
				}
			}
			numFila++;
			fila = hoja.getRow(numFila);
		}

	}

}
