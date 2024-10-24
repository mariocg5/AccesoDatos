package ficheros.ejercicio13_SAX;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import ficheros.Ejercicio8;

public class Ejercicio13_bis {
	private final static String DOCTRABAJO = "Ejercicio13.xml";
	public static void main(String[] args) {
		
		try {
			SAXParserFactory sPF = SAXParserFactory.newInstance();
			SAXParser sP = sPF.newSAXParser();
			sP.parse(new InputSource(Ejercicio8.RUTA+DOCTRABAJO), new Manejador2("Sopa de cebolla"));
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
