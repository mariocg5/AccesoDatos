package xStream;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver; //pegado manualmente
import com.thoughtworks.xstream.security.AnyTypePermission;

import ficheros.ejercicio6.Ejercicio6;
import ficheros.ejercicio6.MyObjectOutputStream;
import ficheros.ejercicio6.Persona;
import utilidades.utilidades;

public class Ejercicio15 {

	public static final String DOCTRABAJO_IN = "FicheroPersonasSerializado.dat";
	public static final String DOCTRABAJO_OUT = "Ejercicio15.xml";
	public static final String DOCTRABAJO_IN_XML = "ejercicio15_in.xml";
	private static ObjectInputStream oIS=null;
	private static ObjectOutputStream oOS=null;
	private static XStream xS=new XStream(new DomDriver("UTF-8"));

	public static void main(String[] args) {

		SerializaDesdeXML();
		Ejercicio6.leerObjetos();
	}


	/**
	 * Transforma los elementos de una clase Persona a un documento XML
	 */
	private static void DeserializaAXML() {
		ListaPersonas lP = null;
		try {
			oIS = new ObjectInputStream(new FileInputStream(new File(utilidades.RUTA + DOCTRABAJO_IN)));
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
				oIS.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private static void defineEstructura() {
	xS.alias("familia", ListaPersonas.class); //pone el nombre a la clase
	xS.alias("miembro",Persona.class); //pone el nombre a la clase
	xS.addImplicitCollection(ListaPersonas.class, "lista");
	xS.aliasField("primerApellido", Persona.class, "apellido1");
	xS.aliasField("segundoApellido", Persona.class, "apellido2");
	xS.aliasField("name", Persona.class, "nombre");		
	xS.useAttributeFor(Persona.class, "nombre");
	}


/**
 * A partir de un fichero xml ejercicio15_in se actualiza la clase persona con los datos del fichero
 */
	private static void SerializaDesdeXML() {		
		try {
			ListaPersonas lP = new ListaPersonas();
			xS.addPermission(AnyTypePermission.ANY); //importante
			defineEstructura();
			lP=(ListaPersonas) xS.fromXML(new FileInputStream(utilidades.RUTA+DOCTRABAJO_IN_XML));
			Iterator<Persona> it = lP.getLista().iterator();
			Ejercicio6.inicializar();
			Persona p = new Persona();
			while(it.hasNext()) {
				p=it.next();
				Ejercicio6.escribirObjeto(p);
			}
			Ejercicio6.getoOS().close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
}
