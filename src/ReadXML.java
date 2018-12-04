import java.io.*;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
public class ReadXML {
	public static void main (String [] args)
	throws FileNotFoundException, IOException, SAXException {
		/* A continuaci�n se crea objeto procesador XML - XMLReader -. Durante la creaci�n de este objeto se puede producir una
		excepci�n SAXException. */
		XMLReader procesadorXML = XMLReaderFactory.createXMLReader();
		
		/* A continuaci�n, mediante setContentHandler establecemos que la clase que gestiona los eventos provocados por la
		lectura del XML ser� GestionContenido */
		GestionContenido gestor = new GestionContenido();
		procesadorXML.setContentHandler(gestor);
		
		/* Por �ltimo, se define el fichero que se va leer mediante InputSource y se procesa el documento XML mediante el
		m�todo parse() de XMLReader */
		InputSource fileXML = new InputSource ("Empleados.xml");
		procesadorXML.parse(fileXML);
	}
}

/* GestionContenido es la clase que implementa los m�todos necesarios para crear nuestro parser de XML. Es decir,
definimos los m�todos que ser�n llamados al provocarse los eventos comentados anteriormente: startDocument,
startElement, characters, etc. Si quisieramos tratar m�s eventos definir�amos el m�todo asociado en esta clase. */
class GestionContenido extends DefaultHandler {
	public GestionContenido(){
		super();
	}
	public void startDocument(){
		System.out.println("Comienzo del documento XML");
	}
	public void endDocument(){
		System.out.println("Final del documento XML");
	}
	public void startElement (String uri, String nombre, String nombreC, Attributes atts) {
		System.out.printf("\tPrincipio Elemento: %s %n", nombre);
	}
	public void endElement (String uri, String nombre, String nombreC){
		System.out.printf("\tFin Elemento: %s %n",nombre);
	}
	public void characters(char[] ch, int inicio, int longitud) throws SAXException {
		String car = new String (ch, inicio, longitud);
		car = car.replaceAll("[\t\n]","");
		System.out.printf("\tCaracteres: %s %n", car);
	}
}