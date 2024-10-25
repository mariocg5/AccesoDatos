package xStream;
 
import java.io.File;
import java.io.IOException;
 
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
 

import utilidades.utilidades;
 
public class Ejercicio15_Ampliacion2 {
	public static final String DOCTRABAJO_IN = "ejercicio15_in.xml";
	public static final String DOCTRABAJO_OUT = "Ejercicio15.xml";
 
	public static void main(String[] args) {
		File xmlIN = new File(utilidades.RUTA+DOCTRABAJO_IN);
		File xmlOUT = new File(utilidades.RUTA+DOCTRABAJO_OUT);
			
		try {
			DocumentBuilderFactory dBF= DocumentBuilderFactory.newInstance();
			DocumentBuilder dB = dBF.newDocumentBuilder();
			
			Document docIN= dB.parse(utilidades.RUTA+DOCTRABAJO_IN);
			docIN.getDocumentElement();
			Document docOUT= dB.parse(utilidades.RUTA+DOCTRABAJO_OUT);
			docOUT.getDocumentElement();
			
			Node nodoIN= docOUT.getElementsByTagName("familia").item(0);
			NodeList lista= docIN.getElementsByTagName("miembro");
			
			for (int i = 0; i < lista.getLength(); i++) {
				Node miembroNuevo = lista.item(i);
				Node miembroAniadido = docOUT.importNode(miembroNuevo, true);
				nodoIN.appendChild(miembroAniadido);
			}
			
			TransformerFactory tF= TransformerFactory.newInstance();
			Transformer t = tF.newTransformer();
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			//t.transform(new DOMSource(doc), new StreamResult(System.out));
			t.transform(new DOMSource(docOUT), new StreamResult(new File(utilidades.RUTA+DOCTRABAJO_OUT)));
			
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
		}
		
	}
 
}