package main;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.SAXException;

import logica.Funcionalidades;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerFactoryConfigurationError, TransformerException {
		
//		System.out.println(Funcionalidades.getNota("Eva"));
//		Funcionalidades.modNota("Eva", 2);
//		System.out.println(Funcionalidades.getNota("Eva"));
//		
//		Funcionalidades.insAlumno("Pepe", 3);
//		Funcionalidades.insAlumno("Marcos", 9);
//		Funcionalidades.insAlumno("Alberticus", 10);
		for(String s: Funcionalidades.listadoAprobados()) {
			System.out.println(s);
		}
	}

}
