package daoImpl;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import beans.Contacto;
import dao.ContactoDao;
import io.ObjectOutputStreamSinHeader;

public class ContactoDaoImpl implements ContactoDao {

	private File ficheroAgenda;
	
	public ContactoDaoImpl(File ficheroAgenda) {
		super();
		this.ficheroAgenda = ficheroAgenda;
	}

	@Override
	public void addContacto(Contacto c) throws FileNotFoundException, IOException {
		
		ObjectOutputStream oos;
		if (ficheroAgenda.exists())
			oos = new ObjectOutputStreamSinHeader(new FileOutputStream(ficheroAgenda, true));
		else
			oos = new ObjectOutputStream(new FileOutputStream(ficheroAgenda));

		oos.writeObject(c);
		oos.close();

	}

	public LinkedList<Contacto> getContacto(String nombre)
			throws FileNotFoundException, IOException, ClassNotFoundException {

		LinkedList<Contacto> contactos = new LinkedList<>();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroAgenda));
		boolean finArchivo = false;
		while (!finArchivo)
			try {
				Contacto c = (Contacto) ois.readObject();
				if (c.getNombre().equals(nombre))
					contactos.add(c);
			} catch (EOFException e) {
				finArchivo = true;
				ois.close();
			}

		return contactos;
	}

	@Override
	public void updateContacto(String nombre, Contacto c) throws IOException, ClassNotFoundException{
		File f = new File("temp");

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroAgenda));
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));

		boolean finArchivo = false;

		while (!finArchivo)
			try {
				Contacto contacto = (Contacto) ois.readObject();
				if(contacto.getNombre().equals(c.getNombre()))
					oos.writeObject(c);
				else
					oos.writeObject(contacto);
				
				
			} catch (EOFException e) {
				finArchivo = true;
			}
		ois.close();
		oos.close();

		ficheroAgenda.delete();
		f.renameTo(ficheroAgenda);
		

	}

	@Override
	public void deleteContacto(String nombre) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		File f = new File("temp");

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroAgenda));
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));

		boolean finArchivo = false;

		while (!finArchivo)
			try {
				Contacto c = (Contacto) ois.readObject();
				if (!c.getNombre().equals(nombre))
					oos.writeObject(c);
			} catch (EOFException e) {
				finArchivo = true;
			}
		ois.close();
		oos.close();

		ficheroAgenda.delete();
		f.renameTo(ficheroAgenda);

	}
	
	public LinkedList<Contacto> listarContactos()
			throws FileNotFoundException, IOException, ClassNotFoundException {
		LinkedList<Contacto> contactos = new LinkedList<>();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroAgenda));
		boolean finArchivo = false;
		while (!finArchivo)
			try {
				contactos.add((Contacto) ois.readObject());
			} catch (EOFException e) {
				finArchivo = true;
				ois.close();
			}

		return contactos;
	}

}
