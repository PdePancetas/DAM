package ejercicios_1_a_8;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.*;
import java.awt.Font;

@SuppressWarnings("serial")
public class Ventana5 extends JFrame {

	private JTextField tituloField;
	private JTextField directorField;
	private JComboBox<String> eleccion;
	JButton añadirPeliBtn;
	private Map<String, String> pelisYDir = new HashMap<>();
	private JLabel elegirPeli;

	public Ventana5() {
		super("Lista de Peliculas");
		getContentPane().setLayout(null);

		eleccion = new JComboBox<>();
		eleccion.insertItemAt("PELICULAS", 0);
		eleccion.setBounds(346, 82, 116, 22);
		getContentPane().add(eleccion);

		JLabel titulo = new JLabel("Titulo:");
		titulo.setBounds(35, 82, 277, 14);
		getContentPane().add(titulo);

		JLabel director = new JLabel("Director:");
		director.setBounds(35, 135, 66, 14);
		getContentPane().add(director);

		tituloField = new JTextField();
		tituloField.setBounds(35, 107, 184, 20);
		getContentPane().add(tituloField);
		tituloField.setColumns(10);

		directorField = new JTextField();
		directorField.setBounds(35, 160, 184, 20);
		directorField.setColumns(10);
		getContentPane().add(directorField);

		añadirPeliBtn = new JButton("A\u00F1adir Pel\u00EDcula");
		añadirPeliBtn.setFont(new Font("Tahoma", Font.ITALIC, 11));
		añadirPeliBtn.setBounds(35, 191, 184, 23);
		getContentPane().add(añadirPeliBtn);

		elegirPeli = new JLabel("Elige una pelicula:");
		elegirPeli.setBounds(346, 52, 116, 14);
		getContentPane().add(elegirPeli);

		añadirPeliBtn.addActionListener(new IntroducirPelicula());
		eleccion.addActionListener(new DisplayDatos());

	}

	class IntroducirPelicula implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			pelisYDir.put(tituloField.getText(), directorField.getText());
			eleccion.addItem(tituloField.getText());
			tituloField.setText("");
			directorField.setText("");
		}
	}

	class DisplayDatos implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!eleccion.getSelectedItem().equals("PELICULAS")) {
				Set<Entry<String, String>> valores = pelisYDir.entrySet();
				for (Entry<String, String> entry : valores)
					if (eleccion.getSelectedItem().equals(entry.getKey())) {
						tituloField.setText(entry.getKey());
						directorField.setText(entry.getValue());
					}
			} else {
				tituloField.setText("");
				directorField.setText("");
			}
				

		}

	}

	public static void main(String[] args) {

		final Ventana5 v = new Ventana5();
		SwingUtilities.invokeLater(() -> { // FUNCION LAMBDA PARA COMPACTAR CODIGO
			v.setVisible(true);
			v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			v.setLocation(300, 300);
			v.setSize(700, 300);
		});

	}
}
