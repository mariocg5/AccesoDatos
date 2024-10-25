package jaxb;

import java.io.File;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jaxb.clasesEjercicio16.Examen;
import utilidades.utilidades;

public class Ejercicio16 {
	private static JAXBContext jC;
	private static Examen examen;
	private static final String DOCUMENTO = "Ejercicio16.xml";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Carga en el javabean (clases creadaas y etiquetadas) en XML
	 */
	public static void unmarshalling() {
		try {
			jC = JAXBContext.newInstance(Examen.class);
			Unmarshaller uM = jC.createUnmarshaller();
			examen = (Examen) uM.unmarshal(new File(utilidades.RUTA+DOCUMENTO)); //definir ruta y doctrabajo
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Hacer metpdo marshall para ver reflejados los cambios en el documento

}
