package system;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.zip.DataFormatException;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import data.*;
import exception.VoidException;
import repository.Ops;

public class Ventana extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static HashSet<Grupo> grupos = new HashSet<>();
	private static HashSet<Estudiante> estudiantes = new HashSet<>();

	public static HashSet<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(HashSet<Grupo> grupos) {
		Ventana.grupos = grupos;
	}

	public static HashSet<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(HashSet<Estudiante> estudiantes) {
		Ventana.estudiantes = estudiantes;
	}

	private JTextField txtLoad;
	private JTextField txtSave;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnTxt;
	private JRadioButton rdbtnDat;
	private JButton btnLoad;
	private JButton btnSave;
	private JTextArea textAreaEstudiantes;
	private JLabel lblStatus;
	private JLabel lblStatusInfo;
	private JLabel lblFormat;
	private JLabel lblNewLabel_1;

	public Ventana() {
		getContentPane().setLayout(null);

		txtLoad = new JTextField();
		txtLoad.setBounds(45, 38, 390, 20);
		getContentPane().add(txtLoad);
		txtLoad.setColumns(10);

		rdbtnTxt = new JRadioButton(".txt");
		buttonGroup.add(rdbtnTxt);
		rdbtnTxt.setBounds(476, 37, 64, 23);
		getContentPane().add(rdbtnTxt);

		rdbtnDat = new JRadioButton(".dat");
		buttonGroup.add(rdbtnDat);
		rdbtnDat.setBounds(542, 37, 64, 23);
		getContentPane().add(rdbtnDat);

		btnLoad = new JButton("Load");
		btnLoad.setBounds(631, 37, 89, 23);
		getContentPane().add(btnLoad);
		btnLoad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (rdbtnTxt.isSelected()) {

					try {
						int num = Ops.loadEstudentsTxt(txtLoad.getText());
						lblStatusInfo.setText("Fichero cargado correctamente. Añadidos " + num + " estudiantes.");
						displayEstudents();

					} catch (VoidException e1) {
						JOptionPane.showMessageDialog(rootPane, "No se ha encontrado el fichero "+txtLoad.getText()+".txt");
						txtLoad.setText("");
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				} else if (rdbtnDat.isSelected()) {
					try {
						int num = Ops.loadEstudentsDat(txtLoad.getText());
						lblStatusInfo.setText("Fichero cargado correctamente. Añadidos " + num + " estudiantes.");
						displayEstudents();
					} catch (VoidException e1) {
						JOptionPane.showMessageDialog(rootPane, "No se ha encontrado el fichero "+txtLoad.getText()+".dat");
						txtLoad.setText("");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else
					JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado el tipo de fichero!");

			}
		});

		btnSave = new JButton("Save");
		btnSave.setBounds(631, 82, 89, 23);
		getContentPane().add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Ops.saveEstudents(txtGetEstudiantes(txtSave.getText()));
					lblStatusInfo.setText("Fichero guardado correctamente");
					displayEstudents();
				} catch (IOException e1) {

				} catch (DataFormatException e1) {
					JOptionPane.showMessageDialog(rootPane,
							"El formato del texto no es correcto para añadir los estudiantes!");
					txtSave.setText("");
				}

			}
		});

		txtSave = new JTextField();
		txtSave.setColumns(10);
		txtSave.setBounds(45, 83, 561, 20);
		getContentPane().add(txtSave);

		lblStatus = new JLabel("Status:");
		lblStatus.setBounds(45, 436, 46, 14);
		getContentPane().add(lblStatus);

		textAreaEstudiantes = new JTextArea();
		textAreaEstudiantes.setEditable(false);
		textAreaEstudiantes.setBounds(45, 114, 675, 311);
		getContentPane().add(textAreaEstudiantes);

		lblStatusInfo = new JLabel("");
		lblStatusInfo.setBounds(101, 436, 439, 14);
		getContentPane().add(lblStatusInfo);

		lblFormat = new JLabel("Formato del texto para añadir estudiantes: (edad,nombre,grupo;edad,nombre,grupo;....)");
		lblFormat.setBounds(45, 69, 561, 14);
		getContentPane().add(lblFormat);

		lblNewLabel_1 = new JLabel("Nombre del fichero:");
		lblNewLabel_1.setBounds(45, 11, 390, 14);
		getContentPane().add(lblNewLabel_1);

	}

	protected HashSet<Estudiante> txtGetEstudiantes(String text) throws DataFormatException {
		HashSet<Estudiante> students = new HashSet<>();

		try {
			for (String student : text.split(";")) {
				Estudiante s = new Estudiante();
				Object[] datos = student.split(",");

				s.setNombre((String) datos[1]);
				s.setEdad(Integer.parseInt(student.split(",")[0]));
				s.setGrupo(new Grupo((String) datos[2]));

				students.add(s);
			}
		} catch (Exception e) {
			throw new DataFormatException();
		}

		return students;
	}

	protected void displayEstudents() {
		String txtEst = "";
		for (Estudiante est : estudiantes)
			txtEst += est.toString() + "\n";
		textAreaEstudiantes.setText(txtEst);
	}

	public static void main(String[] args) {

		Ventana v = new Ventana();
		SwingUtilities.invokeLater(() -> {

			v.setDefaultCloseOperation(EXIT_ON_CLOSE);
			v.setBounds(50, 50, 800, 500);
			v.setVisible(true);

		});

	}
}
