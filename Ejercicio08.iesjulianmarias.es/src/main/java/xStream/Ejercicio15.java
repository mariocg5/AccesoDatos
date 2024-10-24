package xStream;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver; //pegado manualmente

import ficheros.ejercicio6.Persona;
import utilidades.utilidades;

public class Ejercicio15 {

	public static final String DOCTRABAJO_IN = "FicheroPersonasSerializado.dat";
	public static final String DOCTRABAJO_OUT = "Ejercicio15.xml";
	private static ObjectInputStream oIS=null;
	

	public static void main(String[] args) {
		ListaPersonas lP = null;
		XStream xS = null;
		try {
			oIS = new ObjectInputStream(new FileInputStream(new File(utilidades.RUTA + DOCTRABAJO_IN)));
			xS = new XStream(new DomDriver("UTF-8"));
			lP = new ListaPersonas();
			while (true) {
				Persona persona = (Persona) oIS.readObject();
				lP.anadir(persona);
			}
		} catch (EOFException e) { // creado manualmente

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				
				xS.alias("familia", ListaPersonas.class); //pone el nombre a la clase
				xS.alias("miembro",Persona.class); //pone el nombre a la clase
				xS.addImplicitCollection(ListaPersonas.class, "lista");
				xS.aliasField("PrimerApellido", Persona.class, "apellido1");
				xS.aliasField("SegundoApellido", Persona.class, "apellido2");
				xS.useAttributeFor(Persona.class, "nombre");
				xS.toXML(lP, new FileOutputStream(utilidades.RUTA + DOCTRABAJO_OUT));
				oIS.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
