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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import ficheros.ejercicio6.Ejercicio6;
import ficheros.ejercicio6.Persona;

public class Ejercicio12 {
	
	private final static String DOCTRABAJO = "Ejercicio12.xml";

	public static void main(String[] args) {
		leerObjetos();
		LeerXML();
		
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
					
					CreaElemento("nombre",persona.getNombre().toString(),elementoPersona, doc );
					CreaElemento("apellido1",persona.getApellido1().toString(),elementoPersona, doc );
					CreaElemento("apellido2",persona.getApellido2().toString(),elementoPersona, doc );
					CreaElemento("nacimiento",persona.getNacimiento().toString(),elementoPersona, doc );
					
					
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
			//t.transform(new DOMSource(doc), new StreamResult(System.out));
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

	private static void CreaElemento(String etiqueta, String valor, Element padre, Document doc) {
		Element elem = doc.createElement(etiqueta);
		Text texto = doc.createTextNode(valor);
		padre.appendChild(elem);
		elem.appendChild(texto);		
	}
	
	public static void LeerXML() {
		   try {
		       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		       DocumentBuilder builder = factory.newDocumentBuilder();
		       
		       //Cargamos en memoria el doc XML        
		       Document doc = builder.parse(new File(Ejercicio8.RUTA+DOCTRABAJO));
		       
		       LeeNodo(doc.getDocumentElement());
		       
		   } catch (Exception e) {e.printStackTrace();}
	}
		       
	public static void LeeNodo(Node nodo) {
		
		   if(nodo.getNodeType()==Node.ELEMENT_NODE)    {
			   Element element = (Element) nodo;
			   System.out.print("<"+element.getNodeName()+">");
			   
			   NodeList nodosHijos= element.getChildNodes();
			   if(nodosHijos.item(0).getNodeType()==Node.ELEMENT_NODE) {
				   System.out.println("\t");
			   }
			   for (int i = 0; i < nodosHijos.getLength(); i++) {
				   LeeNodo(nodosHijos.item(i));
			}
			   System.out.println("</"+element.getNodeName()+">");
			   
		   } else if(nodo.getNodeType()==Node.TEXT_NODE) {
			   
			   String texto= nodo.getNodeValue();
			   if(!texto.isEmpty()) {
				   System.out.print(texto);
			   }
		   }
		   
		  
	
	}
	
	

}
