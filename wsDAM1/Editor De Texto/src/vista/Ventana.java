package vista;

import javax.swing.*;

import ficheros.Ficheros;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;
import java.util.Set;

@SuppressWarnings("serial")
public class Ventana extends JFrame {

	JTextArea textArea;
	private JMenu menuArchivo;
	private JMenuItem menuNuevo;
	private JMenuItem menuAbrir;
	private JMenuItem menuGuardar;
	private JMenuItem menuGuardarComo;
	private JMenuItem menuCerrar;
	private JMenu menuUtilidades;
	private JMenuItem menuTamaño;
	private JMenuItem menuContarLineas;
	private JMenuItem menuSelecMay;
	private JMenuItem menuSelecMin;
	private JMenuBar menuBar;
	private JLabel nombreArchivoTrabajo;

	public Ventana() {
		super("Text Editor");
		getContentPane().setBackground(Color.CYAN);
		getContentPane().setLayout(null);

		textArea = new JTextArea();
		textArea.setBounds(136, 41, 493, 326);
		getContentPane().add(textArea);

		nombreArchivoTrabajo = new JLabel("");
		nombreArchivoTrabajo.setHorizontalAlignment(SwingConstants.CENTER);
		nombreArchivoTrabajo.setBounds(175, 11, 390, 19);
		getContentPane().add(nombreArchivoTrabajo);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menuArchivo = new JMenu("Archivo");
		menuBar.add(menuArchivo);

		menuNuevo = new JMenuItem("Nuevo");
		menuArchivo.add(menuNuevo);

		menuAbrir = new JMenuItem("Abrir");
		menuArchivo.add(menuAbrir);

		menuGuardar = new JMenuItem("Guardar");
		menuArchivo.add(menuGuardar);

		menuGuardarComo = new JMenuItem("Guardar como...");
		menuArchivo.add(menuGuardarComo);

		menuCerrar = new JMenuItem("Cerrar");
		menuArchivo.add(menuCerrar);

		menuUtilidades = new JMenu("Utilidades");
		menuBar.add(menuUtilidades);

		menuTamaño = new JMenuItem("Tamaño");
		menuUtilidades.add(menuTamaño);

		menuContarLineas = new JMenuItem("Contar Líneas");
		menuUtilidades.add(menuContarLineas);

		menuSelecMay = new JMenuItem("Selecc a May");
		menuUtilidades.add(menuSelecMay);

		menuSelecMin = new JMenuItem("Selecc a Min");
		menuUtilidades.add(menuSelecMin);

		menuNuevo.addActionListener(new CapturaMenu());
		menuAbrir.addActionListener(new CapturaMenu());
		menuGuardar.addActionListener(new CapturaMenu());
		menuGuardarComo.addActionListener(new CapturaMenu());
		menuCerrar.addActionListener(new CapturaMenu());
		menuTamaño.addActionListener(new CapturaMenu());
		menuContarLineas.addActionListener(new CapturaMenu());
		menuSelecMay.addActionListener(new CapturaMenu());
		menuSelecMin.addActionListener(new CapturaMenu());

	}

	class CapturaMenu implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == menuNuevo) {

				String fichero = null;
				String mensaje = "";
				do {

					mensaje = (fichero != null) ? "El fichero ya existe. " : "";
					fichero = JOptionPane.showInputDialog(mensaje + "Introduce el nombre del nuevo archivo:");
				} while (Ficheros.ficheros.containsKey(fichero));
				Ficheros.newFichero(fichero, "");
				nombreArchivoTrabajo.setText(fichero);
				textArea.setText("");

			} else if (e.getSource() == menuAbrir) {

				if (Ficheros.ficheros.size() == 0)
					JOptionPane.showMessageDialog(rootPane, "Aun no hay archivos que abrir!");
				else {
					int op = JOptionPane.showConfirmDialog(rootPane, "Quiere guardar antes de abrir un archivo?",
							"Guardar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (op == JOptionPane.YES_OPTION) {
						Ficheros.saveFichero(nombreArchivoTrabajo.getText(), textArea.getText());

						String fichero = null;
						comprobarSiExiste(fichero);

						nombreArchivoTrabajo.setText(fichero);
						textArea.setText(Ficheros.openFichero(fichero));

					} else if (op == JOptionPane.NO_OPTION) {
						String fichero = null;
						comprobarSiExiste(fichero);

						nombreArchivoTrabajo.setText(fichero);
						textArea.setText(Ficheros.openFichero(fichero));

					}

				}

			} else if (e.getSource() == menuGuardar) {
				Ficheros.saveFichero(nombreArchivoTrabajo.getText(), textArea.getText());

			} else if (e.getSource() == menuGuardarComo) {

				String nombreAnt = nombreArchivoTrabajo.getText();
				String nombre = JOptionPane.showInputDialog(rootPane,
						"Modifica el nombre del archivo: \n(Ojo! El fichero se sobreescribirá)",
						nombreArchivoTrabajo.getText());
				Ficheros.saveAsFichero(nombreAnt, nombre, textArea.getText());
				nombreArchivoTrabajo.setText(nombre);

			} else if (e.getSource() == menuCerrar) {

				int op = JOptionPane.showConfirmDialog(rootPane,
						"Pulse Yes para cerrar el editor\n" + "Pulse No para limpiar el editor\n"
								+ "Pulse Cancel para cancelar esta accion",
						"Guardar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (op == JOptionPane.YES_OPTION)
					System.exit(0);
				else if (op == JOptionPane.NO_OPTION) {
					nombreArchivoTrabajo.setText("");
					textArea.setText("");
				}

			} else if (e.getSource() == menuTamaño) {
				String fichero = JOptionPane.showInputDialog(
						"Introduce un nombre de entre estos archivos:\n" + Ficheros.ficheros.keySet().toString());
				
				try {
					int numChars = Ficheros.calcularTamaño(fichero);
					JOptionPane.showMessageDialog(rootPane, "Numero de caracteres: " + numChars);
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(rootPane, "No hay caracteres introducidos");
				}
				

			} else if (e.getSource() == menuContarLineas) {
				String fichero = JOptionPane.showInputDialog(
						"Introduce un nombre de entre estos archivos:\n" + Ficheros.ficheros.keySet().toString());
				try {
					int numLineas = Ficheros.calcularNumLineas(fichero);
					JOptionPane.showMessageDialog(rootPane, "Numero de lineas: " + numLineas);
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(rootPane, "No hay caracteres introducidos");
				}
				

			} else if (e.getSource() == menuSelecMay) {
				try {
					String textoSelected = Ficheros.selecMay(nombreArchivoTrabajo.getText(),
							textArea.getSelectedText());
					textArea.replaceSelection(textoSelected);
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(rootPane, "No has seleccionado ningun texto para convertir!");
				}
			} else if (e.getSource() == menuSelecMin) {
				try {
					String textoSelected = Ficheros.selecMin(nombreArchivoTrabajo.getText(),
							textArea.getSelectedText());
					textArea.replaceSelection(textoSelected);
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(rootPane, "No has seleccionado ningun texto para convertir!");
				}
			}

		}

		private void comprobarSiExiste(String fichero) {

			String mensaje = "";
			do {

				mensaje = (fichero != null) ? "El fichero no existe. " : "";
				fichero = JOptionPane.showInputDialog(mensaje + "Introduce un nombre de entre estos archivos:\n"
						+ Ficheros.ficheros.keySet().toString());

			} while (!Ficheros.ficheros.containsKey(fichero));
		}
	}

	public static void main(String[] args) {

		final Ventana v = new Ventana();
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				v.setVisible(true);
				v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				v.setLocation(300, 300);
				v.setSize(750, 432);
			}

		});

	}
}