package view;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import exception.ExistsException;
import exception.ProfesorException;
import exception.VoidException;
import repository.Repositorio;

@SuppressWarnings("serial")
public class FrmCrearAlumProf extends JInternalFrame {

	private JLabel lblNifProfesor;
	private JTextField txtNifProfesor;
	private JButton btnCreate;
	private JButton btnVolver;

	public FrmCrearAlumProf() {
		super("Crear fichero con alumnos por nif de profesor");
		this.getContentPane().setLayout(null);

		lblNifProfesor = new JLabel("Introduce el nif del profesor:");
		lblNifProfesor.setBounds(10, 10, 200, 13);
		this.getContentPane().add(lblNifProfesor);

		txtNifProfesor = new JTextField();
		txtNifProfesor.setBounds(20, 33, 96, 19);
		this.getContentPane().add(txtNifProfesor);
		txtNifProfesor.setColumns(10);

		btnCreate = new JButton("Crear");
		btnCreate.setBounds(126, 32, 85, 21);
		this.getContentPane().add(btnCreate);
		btnCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					JOptionPane.showMessageDialog(getRootPane(), "<html><p>Fichero creado con exito!</p>"
								+ "<p>Guardado en "+(Repositorio.crearFicheroNombresAlumnosProfesor(txtNifProfesor.getText()).getAbsolutePath())+"</p></html>");
				} catch (ProfesorException e1) {
					JOptionPane.showMessageDialog(getRootPane(),
							"El profesor de nif " + txtNifProfesor.getText() + " no existe!");
				} catch (IOException e2) {
					e2.printStackTrace();
				} catch (HeadlessException e3) {
					e3.printStackTrace();
				} catch (ExistsException e4) {
					JOptionPane.showMessageDialog(getRootPane(), "Este profesor no tiene alumnos asignados!");
				} catch (VoidException e5) {
					JOptionPane.showMessageDialog(getRootPane(), e5.getMessage());
				}
				
			}
		});

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnVolver.setBounds(221, 32, 85, 21);
		this.getContentPane().add(btnVolver);

		this.setVisible(false);
	}

}
