package logica;

import java.io.*;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ArbolDOM{
	

	public static void main (String[] args){
		
		System.out.println("Introduzca nombre de archivo XML incluyendo ruta:");
		
		Scanner scan = new Scanner(System.in);
		
		String fich = scan.nextLine();
		
		scan.close();
    
		Document document; 
    
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
      
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse( new File(fich) );
			imprimirNodos(document);

		} catch (ParserConfigurationException pexc) { 
			pexc.printStackTrace();
		} catch (Exception exc) {
			System.out.println("   " + exc.getMessage() );
		}
	}


	public static void imprimirNodos(Node node) {

		if ( node == null ) {
			return;
		}

       int type = node.getNodeType();
 
       switch ( type ) {

       	case Node.DOCUMENT_NODE: {
    	   NodeList children = node.getChildNodes();
    	   for ( int i = 0; i < children.getLength(); i++ ) {
               imprimirNodos(children.item(i));
    	   }
        
    	   break;
       	}

       	case Node.ELEMENT_NODE: {
    	  
    	  System.out.print('<');
    	  System.out.print(node.getNodeName());
    	  NamedNodeMap attrs= node.getAttributes();
    	  for ( int i = 0; i < attrs.getLength(); i++ ) {
    		  Attr attr = (Attr) attrs.item(i);
    		  System.out.print(' ');
    		  System.out.print(attr.getNodeName());
    		  System.out.print("=\"");
    		  System.out.print(attr.getNodeValue());
    		  System.out.print('"');
    	  }
    	  System.out.print('>');
    	  NodeList children = node.getChildNodes();
    	  if ( children != null ) {
    		  int len = children.getLength();
    		  for ( int i = 0; i < len; i++ ) {
    			  imprimirNodos(children.item(i));
    		  }
    	  }
    	  
    	  
    	  break;
       	}

      
       	case Node.TEXT_NODE: {
    	  System.out.print(node.getNodeValue());
    	  break;
       	}

	      

     } // fin switch

     // En caso de ser nodo de Elemento cerrar etiqueta 
     if ( type == Node.ELEMENT_NODE ) {
    	 System.out.print("</");
    	 System.out.print(node.getNodeName());
    	 System.out.print('>');
     }

	

    } // fin imprimir Nodos
    
   

}