package ficheros;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/*
 * Mostrar el contenido de documento XML "ejercicio10.xml" 
 * por pantalla y también el contenido del documento
 *  XML ubicado en la siguiente URL https://acortar.link/dO4FNx.
 */


public class Ejercicio10 {
	
	private final static String DOCTRABAJO_IN = "Ejercicio10.xml";
	
	
	public static void main (String[]args) {		
		try {
			DocumentBuilderFactory dBF= DocumentBuilderFactory.newInstance();
			DocumentBuilder dB = dBF.newDocumentBuilder();
			
			Document doc = dB.parse(new File(Ejercicio8.RUTA+DOCTRABAJO_IN));
			
			URI uri = new URI( "https://acortar.link/dO4FNx"); //usamos URI ya que URL está deprecated
			Document docWeb = dB.parse(uri.toURL().openStream());
			
			TransformerFactory tF= TransformerFactory.newInstance();
			Transformer t = tF.newTransformer();
			t.transform(new DOMSource(doc), new StreamResult(System.out));
			System.out.println("\nDocumento en línea: ");
			t.transform(new DOMSource(docWeb), new StreamResult(System.out));
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
