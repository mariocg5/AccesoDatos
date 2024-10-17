package ficheros;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import ficheros.ejercicio6.Ejercicio6;
import ficheros.ejercicio6.Persona;

public class Ejercicio12 {
	
	private final static String DOCTRABAJO = "Ejercicio12.xml";

	public static void main(String[] args) {
		leerObjetos();

	}
	
	public static void leerObjetos() { // void o persona, preguntar
		try {
			ObjectInputStream oIS = new ObjectInputStream(new FileInputStream(new File(Ejercicio8.RUTA + Ejercicio6.DOCTRABAJO_IN)));
			DocumentBuilderFactory dBF= DocumentBuilderFactory.newInstance();
			DocumentBuilder dB = dBF.newDocumentBuilder();
			Document doc = dB.newDocument();
			
			Element raiz = doc.createElement("Personas"); //Elemento Raiz
			doc.appendChild(raiz);
			try {
				Persona persona= new Persona();
				while (true) {
					persona = (Persona) oIS.readObject();
					Element elementoPersona = doc.createElement("Persona");
					
					Element elementoNombre = doc.createElement("nombre");
					Text nombreText = doc.createTextNode(persona.getNombre().toString());
					elementoNombre.appendChild(nombreText);
					
					Element elementoApellido1 = doc.createElement("apellido");
					Text apellidoText=doc.createTextNode(persona.getApellido1().toString());
					elementoApellido1.appendChild(apellidoText);
					
					Element elementoApellido2=doc.createElement("apellido2");
					Text apellido2Text = doc.createTextNode(persona.getApellido2().toString());
					elementoApellido2.appendChild(apellido2Text);
					
					Element elementoNacimiento = doc.createElement("nacimiento");
					Text nacimientoText = doc.createTextNode(persona.getNacimiento().toString());
					elementoNacimiento.appendChild(nacimientoText);
					
					elementoPersona.appendChild(elementoNombre);
					elementoPersona.appendChild(elementoApellido1);
					elementoPersona.appendChild(elementoApellido2);
					elementoPersona.appendChild(elementoNacimiento);
					
					raiz.appendChild(elementoPersona);
					
				
				}
			} catch (ClassNotFoundException e) {
				System.out.println("Error de lectura en el fichero");
				e.printStackTrace();
			} catch (EOFException e) {
				
			} finally {
				oIS.close();
			}
			
			TransformerFactory tF= TransformerFactory.newInstance();
			Transformer t = tF.newTransformer();
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.transform(new DOMSource(doc), new StreamResult(System.out));
			t.transform(new DOMSource(doc), new StreamResult(new File(Ejercicio8.RUTA+DOCTRABAJO)));

		} catch (IOException e) {
			System.out.println("Error al abrir el fichero de lectura");
			e.printStackTrace();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

}
