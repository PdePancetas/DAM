package view;

import java.awt.EventQueue;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.Estudiante;
import data.Grupo;
import ops.Ops;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;
import javax.swing.ButtonGroup;

public class Sistema extends JFrame {

	private static Set<Estudiante> estudiantes = new HashSet<>();
	private static Set<Grupo> grupos = new HashSet<>();

	public static Set<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public static void setEstudiantes(Set<Estudiante> estudiantes) {
		Sistema.estudiantes = estudiantes;
	}

	public static Set<Grupo> getGrupos() {
		return grupos;
	}

	public static void setGrupos(Set<Grupo> grupos) {
		Sistema.grupos = grupos;
	}

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField load;
	private JTextField save;
	private JTextArea textArea;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton txt;
	private JRadioButton dat;
	private JButton botonLoad;
	private JButton botonSave;
	private JLabel status;
	private JLabel statusInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sistema frame = new Sistema();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Sistema() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 903, 563);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		load = new JTextField();
		load.setBounds(52, 52, 494, 25);
		contentPane.add(load);
		load.setColumns(10);

		save = new JTextField();
		save.setColumns(10);
		save.setBounds(52, 110, 654, 25);
		contentPane.add(save);

		txt = new JRadioButton("txt");
		buttonGroup.add(txt);
		txt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txt.setBounds(562, 54, 71, 21);
		contentPane.add(txt);

		dat = new JRadioButton("dat");
		buttonGroup.add(dat);
		dat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dat.setBounds(635, 54, 71, 21);
		contentPane.add(dat);

		botonLoad = new JButton("Load");
		botonLoad.setFont(new Font("Tahoma", Font.PLAIN, 15));
		botonLoad.setBounds(733, 54, 96, 23);
		contentPane.add(botonLoad);
		botonLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txt.isSelected()) {
					try {
						String ests = "";
						estudiantes.addAll(Ops.cargarEstudiantesTxt(new File(".\\Estudiantes.txt")));
						statusInfo.setText("Fichero cargado correctamente");
						for (Estudiante es : estudiantes) {
							ests += es.toString() + "\n";
						}
						textArea.setText(ests);

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else if (dat.isSelected()) {
					try {
						String ests = "";
						estudiantes.addAll(Ops.cargarEstudiantesDat(new File(".\\Estudiantes.dat")));
						statusInfo.setText("Fichero cargado correctamente");
						for (Estudiante es : estudiantes) {
							ests += es.toString() + "\n";
						}
						textArea.setText(ests);

					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				}

			}
		});
		botonLoad.setFont(new Font("Tahoma", Font.PLAIN, 15));
		botonLoad.setBounds(733, 54, 96, 23);
		contentPane.add(botonLoad);

		botonSave = new JButton("Save");
		botonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Ops.guardarEstudiantes(save.getText());
					statusInfo.setText("Fichero guardado correctamente");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		botonSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		botonSave.setBounds(733, 112, 96, 23);
		contentPane.add(botonSave);

		textArea = new JTextArea();
		textArea.setBounds(52, 176, 792, 282);
		contentPane.add(textArea);

		status = new JLabel("Status:");
		status.setFont(new Font("Tahoma", Font.PLAIN, 15));
		status.setBounds(52, 486, 88, 21);
		contentPane.add(status);
		
		
		statusInfo = new JLabel("");
		statusInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		statusInfo.setBounds(150, 486, 300, 21);
		contentPane.add(statusInfo);

	}
}
