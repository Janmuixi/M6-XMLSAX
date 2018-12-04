import java.io.*;
import java.util.Scanner;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
public class ReadXMLbyAuthor {
	
	public static void main (String [] args)
	throws FileNotFoundException, IOException, SAXException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("�Que nombre debe tener el artista?");
		String artist = br.readLine();
		/* A continuaci�n se crea objeto procesador XML - XMLReader -. Durante la creaci�n de este objeto se puede producir una
		excepci�n SAXException. */
		XMLReader procesadorXML = XMLReaderFactory.createXMLReader();
		
		/* A continuaci�n, mediante setContentHandler establecemos que la clase que gestiona los eventos provocados por la
		lectura del XML ser� GestionContenido */
		GestionContents gestor = new GestionContents(artist);
		procesadorXML.setContentHandler(gestor);
		
		/* Por �ltimo, se define el fichero que se va leer mediante InputSource y se procesa el documento XML mediante el
		m�todo parse() de XMLReader */
		InputSource fileXML = new InputSource ("discoteca.xml");
		
		
		procesadorXML.parse(fileXML);
	}
}

/* GestionContenido es la clase que implementa los m�todos necesarios para crear nuestro parser de XML. Es decir,
definimos los m�todos que ser�n llamados al provocarse los eventos comentados anteriormente: startDocument,
startElement, characters, etc. Si quisieramos tratar m�s eventos definir�amos el m�todo asociado en esta clase. */
class GestionContents extends DefaultHandler {
	String artist = "";
	public GestionContents(String artist){
		super();
		this.artist = artist;
	}
	int contador = 0;
	
	public void endDocument(){
		if (contador>0) {
			System.out.println(contador);
		}else {
			System.out.println("No hay discos de este autor");
		}
		
	}
	public void startElement (String uri, String nombre, String nombreC, Attributes atts) {
		if (nombre.equals("TITLE")) {
			//contador ++;
		}		
	}	
	public void characters(char[] ch, int inicio, int longitud) throws SAXException {
		String car = new String (ch, inicio, longitud);
		
		if (car.equals(artist)) {
			contador++;
		}
		
	}
}