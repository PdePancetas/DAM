package view;

import javax.swing.*;

import repository.Ops;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Ventana extends JFrame {

	private JFileChooser selecArchivo = new JFileChooser();
	private JTextField txtPalabraIntroducida;
	private JLabel lblBuscador;
	private JButton btnBuscar;
	private JList<String> ficheros;
	private JTextArea txtAreaFichSelecc;
	private JLabel lblFicheroSeleccionado;
	private JLabel lblNewLabel;

	public Ventana() {
		super("Buscador de Terminos");
		getContentPane().setLayout(null);

		txtPalabraIntroducida = new JTextField();
		txtPalabraIntroducida.setBounds(38, 35, 250, 20);
		getContentPane().add(txtPalabraIntroducida);
		txtPalabraIntroducida.setColumns(10);

		lblBuscador = new JLabel("Introduce el t\u00E9rmino que quieras buscar");
		lblBuscador.setBounds(38, 11, 250, 14);
		getContentPane().add(lblBuscador);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtPalabraIntroducida.getText().equals(""))
					JOptionPane.showMessageDialog(rootPane, "El campo de texto esta vacio!");
				else {
					selecArchivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int seleccion = selecArchivo.showOpenDialog(btnBuscar);
					File f = selecArchivo.getSelectedFile();
					if ((seleccion == JFileChooser.APPROVE_OPTION)) {
						DefaultListModel<String> modelo = new DefaultListModel<>();
						ficheros.setModel(modelo);
						for (int i = 0; i < f.listFiles().length; i++)
							if (f.listFiles()[i].getName().endsWith(".txt"))
								try {
									if (Ops.buscarTermino(f.listFiles()[i], txtPalabraIntroducida.getText()))
										modelo.addElement(f.listFiles()[i].getAbsolutePath());
								} catch (IOException e1) {
								}
						/**
						 
						 */
						
						
						
						
					}
				}
			}
		});
		btnBuscar.setBounds(118, 66, 89, 23);
		getContentPane().add(btnBuscar);

		ficheros = new JList<>();
		ficheros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblFicheroSeleccionado.setText(ficheros.getSelectedValue());
				try {
					txtAreaFichSelecc.setText(Ops.readFile(new File(ficheros.getSelectedValue())));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		ficheros.setBounds(298, 37, 360, 135);
		getContentPane().add(ficheros);

		txtAreaFichSelecc = new JTextArea();
		txtAreaFichSelecc.setEditable(false);
		txtAreaFichSelecc.setBounds(10, 182, 648, 254);
		getContentPane().add(txtAreaFichSelecc);

		lblFicheroSeleccionado = new JLabel("");
		lblFicheroSeleccionado.setBounds(10, 154, 278, 14);
		getContentPane().add(lblFicheroSeleccionado);

		lblNewLabel = new JLabel("Se encontro " + txtPalabraIntroducida.getText() + " en: ");
		lblNewLabel.setBounds(298, 11, 360, 14);
		getContentPane().add(lblNewLabel);

	}

	public static void main(String[] args) {

		final Ventana v = new Ventana();
		SwingUtilities.invokeLater(() -> {

			v.setVisible(true);
			v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			v.setLocation(300, 300);
			v.setSize(800, 400);

		});

	}
}