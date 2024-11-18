package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import beans.Contacto;

public interface ContactoDao {

	//método CRUD (create, read, update, delete)
	
	void addContacto(Contacto c) throws FileNotFoundException, IOException;
	
	LinkedList<Contacto> getContacto(String nombre) throws FileNotFoundException, IOException, ClassNotFoundException;
	
	void updateContacto(String nombre, Contacto c) throws IOException, ClassNotFoundException;
	
	void deleteContacto(String nombre) throws FileNotFoundException, IOException, ClassNotFoundException;
	
	LinkedList<Contacto> listarContactos() throws FileNotFoundException, IOException, ClassNotFoundException;
	
}
