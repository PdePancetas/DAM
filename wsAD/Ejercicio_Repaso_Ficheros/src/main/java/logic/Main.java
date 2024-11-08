package logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import beans.Libro;
import jakarta.xml.bind.JAXBException;
import net.sf.jasperreports.engine.JRException;
import utilidadesTeclado.Teclado;

public class Main {

//	
//	 Funcionalidades:
//		-	Mostrar libros escritos por un autor
//		-	Pasar la información a un archivo XML (JAXB)
//		-	Utilizando ese archivo, mostrar todos los libros con stock menor de uno dado (DOM). 
//		Además se le ofrecerá al usuario la posibilidad de generar un informe en PDF con esa información

	private static boolean conString;
	
	public static void main(String[] args) throws FileNotFoundException, IOException {

		int op = -1;
		do {

			System.out.println("\n Escoge una opcion: ");
			System.out.println("1. Mostrar libros por autor");
			System.out.println("2. Pasar informacion a XML");
			System.out.println("3. Mostrar libros con stock menor");
			System.out.println("0. Salir");
			System.out.print("\n -> ");

			try {
				op = Teclado.leerEntero();

				switch (op) {
				case 1:
					System.out.print("Nombre del autor a buscar: ");
					String nombreAutor = Teclado.leerCadena();
					try {
						System.out.println();
						if (Func.mostrarPorAutor(nombreAutor).getLibros().size() != 0) {
							System.out.println("Libros en los que aparece como autor " + nombreAutor + ": ");
							for (Libro l : Func.mostrarPorAutor(nombreAutor).getLibros())
								System.out.println("- " + l.getTitulo());
							System.out.print("\nQuiere generar un informe de estos libros?\n-> ");
							switch (Teclado.leerCadena()) {
							case "si":
								try {
									Func.genInforme(Func.mostrarPorAutor(nombreAutor), "InformeAutor");
									System.out.println("Informe generado en ./datos/");
								} catch (JRException e) {
									e.printStackTrace();
								}
								break;
							case "no":
								System.out.println("Volviendo al menu principal");
								break;
							}
						} else
							System.out.println(nombreAutor + " no se encuentra en los libros.");

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (JAXBException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

					break;
				case 2:
					System.out.println("Creando Libros.xml a partir de Libros.json");

					try {
						conString = false;
						Func.crearXML(conString);
						System.out.println("Fichero creado en la carpeta ./datos/");
					} catch (IOException e) {
						e.printStackTrace();
					} catch (JAXBException e) {
						e.printStackTrace();
					}

					break;
				case 3:
					if (!Func.getFicheroXML().exists())
						System.err.println("Aun no se ha creado el XML");
					else {
						System.out.print("Introduce un stock: ");
						int stock = Teclado.leerEntero();

						try {
							if (Func.mostrarLibrosConStockMenor(stock).getLibros().size() != 0) {
								System.out.println();
								System.out.println("Libros con stock menor a " + stock + ": ");
								for (Libro l : Func.mostrarLibrosConStockMenor(stock).getLibros())
									System.out.println("- " + l.getTitulo() + ", stock: " + l.getStock() + " u");
								System.out.print("\nQuiere generar un informe de estos libros?\n-> ");
								switch (Teclado.leerCadena()) {
								case "si":
									try {
										Func.genInforme(Func.mostrarLibrosConStockMenor(stock), "InformeStock");
										System.out.println("Informe generado en ./datos/");
									} catch (ParserConfigurationException e) {
										e.printStackTrace();
									} catch (SAXException e) {
										e.printStackTrace();
									} catch (JRException e) {
										e.printStackTrace();
									}
									break;
								case "no":
									System.out.println("Volviendo al menu principal");
									break;
								}
							} else
								System.out.println("No se han encontrado libros con stock menor a " + stock + ".");
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (ParserConfigurationException e) {
							e.printStackTrace();
						} catch (SAXException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					break;
				case 0:
					System.out.println("Saliendo..");
					break;
				default:
					System.out.println("Opcion no valida.");
				}
			} catch (InputMismatchException | NumberFormatException e) {
				System.err.println("Se introdujo un dato no valido.");
			}

		} while (op != 0);

	}
}
