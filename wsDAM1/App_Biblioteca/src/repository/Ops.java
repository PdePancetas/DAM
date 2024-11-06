package repository;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;

import data.Libro;
import data.Movimiento;
import objects.Object_Output_StreamSinHeader;

public class Ops {

	/**
	 * Desarrolla una aplicación Java para gestionar el inventario de una
	 * biblioteca. La aplicación debe permitir al usuario: Pestaña Gestión de
	 * Libros:
	 * 
	 * • Añadir libros: El usuario debe poder introducir los datos de un libro, como
	 * el nombre, descripción, categoría, cantidad de copias actual. Se debe
	 * utilizar una clase Libro para representar los libros, con atributos para
	 * almacenar cada uno de estos datos. La clase debe incluir métodos para acceder
	 * y modificar los atributos de un libro.
	 * 
	 * • Eliminar libros: El usuario debe poder eliminar un libro por su nombre. Se
	 * debe utilizar el método remove() de la lista que almacena los libros para
	 * eliminar el libro correspondiente.
	 * 
	 * • Buscar libros: El usuario debe poder buscar libros por nombre o categoría.
	 * Se debe utilizar la funcionalidad de filtrado de listas para buscar los
	 * libros que coincidan con los criterios del usuario.
	 * 
	 * • Listar libros: El usuario debe poder ver una lista de todos los libros en
	 * el inventario, ordenada por nombre, descripción, categoría o stock. Se debe
	 * utilizar el método sort() de la lista para ordenar los libros según el
	 * criterio elegido por el usuario.
	 * 
	 * • Al cerrar el programa, los libros se guardarán en un fichero como objetos y
	 * serán cargados automáticamente la siguiente vez que se abra el programa.
	 * 
	 * 
	 * • Registrar devolución de libros: El usuario debe poder registrar la
	 * devolución de un determinado libro en el almacén. Se debe registrar la
	 * cantidad de unidades recibidas y la fecha de devolución. Se debe actualizar
	 * el stock del libro correspondiente. Esta información se guardará en un
	 * fichero de log de devoluciones y préstamos.
	 * 
	 * • Registrar préstamo de libros: El usuario debe poder registrar el préstamo
	 * de libros de la biblioteca. Se debe registrar la cantidad de unidades
	 * prestadas y la fecha de préstamo. Se debe actualizar el stock del libro
	 * correspondiente. Esta información se guardará en un fichero de log de
	 * devoluciones y préstamos.
	 * 
	 * • Consultar historial de devoluciones y prestamos: El usuario debe poder
	 * consultar el historial de devoluciones y prestamos de un libro. Se debe
	 * mostrar la fecha, la cantidad el tipo de movimiento (devolución o préstamo)
	 * para cada registro.
	 * 
	 */

	private static LinkedList<Libro> libros = new LinkedList<>();
	private static File librosGuardados = new File(".\\Biblioteca\\Libros.txt");
	private static File movimientos = new File(".\\Biblioteca\\Movimientos.txt");
	private static File stock = new File(".\\Biblioteca\\Informes\\Stock.txt");
	private static File prestamos = new File(".\\Biblioteca\\Informes\\Prestamos");

	// Como se utilizan unicamente en la misma clase, no hace falta utilizar get y
	// set de las variables
	public static LinkedList<Libro> getLibros() {
		return libros;
	}

	public static void setLibros(LinkedList<Libro> libros) {
		Ops.libros = libros;
	}

	public static File getPrestamos() {
		return prestamos;
	}

	public static void setPrestamos(File prestamos) {
		Ops.prestamos = prestamos;
	}

	public static File getStock() {
		return stock;
	}

	public static void setStock(File stock) {
		Ops.stock = stock;
	}

	public static File getMovimientos() {
		return movimientos;
	}

	public static void setMovimientos(File movimientos) {
		Ops.movimientos = movimientos;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////

	// GESTION DE LIBROS

	// CHECK
	/**
	 * Añade un libro a la lista
	 * 
	 * @param nombre
	 * @param descripcion
	 * @param categoria
	 * @param cantidadCopias
	 */
	public static void addBook(String nombre, String descripcion, String categoria, int cantidadCopias) {
		/*
		 * Manejo de errores
		 */

		libros.add(new Libro(nombre, descripcion, categoria, cantidadCopias));
	}

	// CHECK
	/**
	 * Elimina un libro de la lista
	 * 
	 * @param nombre
	 */
	public static void removeBook(String nombre) {
		/*
		 * Manejo de errores
		 */

		libros.remove(new Libro(nombre, "", "", 0));

	}

	// CHECK
	/**
	 * Devuelve el libro con el nombre introducido, ya que los libros se identifican
	 * por el nombre, solo habrá uno.
	 * 
	 * @param nombre
	 * @return
	 */
	public static Libro searchByName(String nombre) {
		/*
		 * Manejo de errores
		 */

		for (Libro l : libros)
			if (l.getNombre().equals(nombre))
				return l;
		return null;
	}

	// CHECK
	/**
	 * Devuelve una lista con todos los libros cuya categoria sea la introducida
	 * 
	 * @param categoria
	 * @return lista con todos los libros cuya categoria sea la introducida
	 */
	public static ArrayList<Libro> searchByCategory(String categoria) {
		/*
		 * Manejo de errores
		 */

		ArrayList<Libro> librosCategoria = new ArrayList<>();

		for (Libro l : libros)
			if (l.getCategoria().equals(categoria))
				librosCategoria.add(l);

		return librosCategoria;

	}

	// CHECK
	/**
	 * listBooks() devuelve la lista ordenada, no el fichero, por lo que al mostrar
	 * los libros ordenados, se recorre la lista, no al fichero
	 * 
	 * @param criterio Criterio por el cual se ordenan los libros
	 * @throws Exception 
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static void listBooks(String criterio) throws Exception {
		if (criterio.equals("Nombre"))
			libros.sort(Comparator.comparing(Libro::getNombre));
		else if (criterio.equals("Descipcion"))
			libros.sort(Comparator.comparing(Libro::getDescripcion));
		else if (criterio.equals("Categoria"))
			libros.sort(Comparator.comparing(Libro::getCategoria));
		else if (criterio.equals("Copias"))
			libros.sort(Comparator.comparing(Libro::getCantidadCopias));
		else 
			throw new Exception();
		for (Libro l : libros) {
			System.out.println(l.toString());
		}
	}

	// CHECK
	/**
	 * Guarda los libros de la lista de Ops en un fichero Libros.txt Este método se
	 * llamará cada vez que se cambie de menu
	 * 
	 * @param libros la lista a guardar
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void saveBooks(LinkedList<Libro> libros) throws FileNotFoundException, IOException {

		/*
		 * Manejo de errores
		 */

		ObjectOutputStream oos = null;
		librosGuardados.delete();
		librosGuardados = new File(".\\Biblioteca\\Libros.txt");
		librosGuardados.createNewFile();
		oos = new ObjectOutputStream(new FileOutputStream(librosGuardados));

		for (Libro l : libros)
			oos.writeObject(l);

		oos.close();
	}

	// CHECK
	/**
	 * Introduce en la lista de Ops los libros del fichero Libros.txt si existiera
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void inputBooks() throws FileNotFoundException, IOException, ClassNotFoundException {

		/*
		 * Manejo de errores
		 */

		if (librosGuardados.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(librosGuardados));

			boolean finalArchivo = false;
			while (!finalArchivo)
				try {

					libros.add((Libro) ois.readObject());
				} catch (EOFException e) {
					finalArchivo = true;
				}
			ois.close();
		}
	}

	// GESTION DE PRESTAMOS Y DEVOLUCIONES

	// CHECK
	/**
	 * Introduce un objeto Movimiento con los datos especificados en el fichero
	 * Movimientos.txt
	 * 
	 * @param nombre
	 * @param fechaMov
	 * @param tipo
	 * @param numCopias
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void returnOrLoanBook(String nombre, String fechaMov, String tipo, int numCopias)
			throws FileNotFoundException, IOException, ParseException {

		/*
		 * Manejo de errores
		 */

		Libro libro = null;
		for (Libro l : libros)
			if (l.getNombre().equals(nombre))
				libro = l; // Si no encuentra, lanza excepcion
		ObjectOutputStream oos;
		if (!movimientos.exists()) {
			movimientos = new File(".\\Biblioteca\\Movimientos.txt");
			movimientos.createNewFile();
			oos = new ObjectOutputStream(new FileOutputStream(movimientos));
		} else
			oos = new Object_Output_StreamSinHeader(new FileOutputStream(movimientos, true));

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
		Date fechaMovimiento = sdf.parse(fechaMov);

		Movimiento m = null;
		if (tipo.equals("Devolucion")) {
			m = new Movimiento(libro, fechaMovimiento, "devolucion", numCopias);
			libro.setCantidadCopias(libro.getCantidadCopias() + numCopias);
		} else if (tipo.equals("Prestamo")) {
			m = new Movimiento(libro, fechaMovimiento, "prestamo", numCopias);
			libro.setCantidadCopias(libro.getCantidadCopias() - numCopias);
			if (libro.getCantidadCopias() < 0) {
				/* Lanza excepcion */
			}

		}
		oos.writeObject(m);

		oos.close();
	}

	// CHECK
	/**
	 * Devuelve una lista con todos los objetos Movimiento que tengan un libro cuyo
	 * nombre sea el especificado
	 * 
	 * @param nombre
	 * @return una lista de objetos Movimiento
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static ArrayList<Movimiento> checkMovesByBook(String nombre)
			throws FileNotFoundException, IOException, ClassNotFoundException {

		/*
		 * Manejo de errores
		 */

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(movimientos));
		ArrayList<Movimiento> movimientosLibro = new ArrayList<>();
		boolean finalArchivo = false;
		while (!finalArchivo)
			try {
				Movimiento m = (Movimiento) ois.readObject();
				if (m.getLibro().getNombre().equals(nombre))
					movimientosLibro.add(m);
			} catch (EOFException e) {
				finalArchivo = true;
			}

		ois.close();
		return movimientosLibro;
	}

	// GENERACION DE INFORMES

	//
	/**
	 * Genera un nuevo informe en la carpeta Informes con los datos de todos los libros 
	 * del fichero Libros.txt
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void genInfoBooks() throws FileNotFoundException, IOException, ClassNotFoundException {

		stock.delete();
		stock.createNewFile();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(librosGuardados));
		FileWriter fr = new FileWriter(stock);

		boolean finalArchivo = false;
		while (!finalArchivo)
			try {
				Libro m = (Libro) ois.readObject();
				fr.write(m.toString());
			} catch (EOFException e) {
				finalArchivo = true;
			}

		ois.close();
		fr.close();
	}

	//
	/**
	 * Genera un informe en la carpeta Prestamos con todos los objetos Movimiento cuya 
	 * fecha esté comprendida entre las especificadas al llamar al método
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws ParseException
	 */
	public static void genInfoLoans(String fechaInicial, String fechaFinal) throws IOException, ParseException, ClassNotFoundException {

		/*
		 * Manejo de errores
		 */
		String fName = "Realizados entre " + fechaInicial + " y " + fechaFinal;
		File f = new File(".\\Biblioteca\\Informes\\Prestamos\\" + fName + ".txt");
		f.createNewFile();

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(movimientos));
		FileWriter fr = new FileWriter(f);

		Date initialDate = (new SimpleDateFormat("dd-MM-yy")).parse(fechaInicial);
		Date finalDate = (new SimpleDateFormat("dd-MM-yy")).parse(fechaFinal);

		boolean finalArchivo = false;
		while (!finalArchivo)
			try {
				Movimiento m = (Movimiento) ois.readObject();
				if (m.getFecha().after(initialDate) && m.getFecha().before(finalDate))
					fr.write((String) m.toString());
			} catch (EOFException e) {
				finalArchivo = true;
			}

		ois.close();
		fr.close();
	}
	
}