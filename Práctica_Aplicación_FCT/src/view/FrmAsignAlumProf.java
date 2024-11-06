package view;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import exception.AlumnoException;
import exception.ExistsException;
import exception.ProfesorException;
import exception.VoidException;
import repository.Repositorio;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class FrmAsignAlumProf extends JInternalFrame {

	private JLabel lblNiaAlum;
	private JTextField txtNiaAlum;
	private JLabel lblNifProf;
	private JTextField txtNifProf;
	private JButton btnAsign;
	private JButton btnVolver;

	public FrmAsignAlumProf() {
		super("Asignar alumno a profesor");
		getContentPane().setLayout(null);

		lblNiaAlum = new JLabel("Introduce el NIA del alumno:");
		lblNiaAlum.setBounds(31, 31, 223, 14);
		this.getContentPane().add(lblNiaAlum);

		txtNiaAlum = new JTextField();
		txtNiaAlum.setBounds(31, 56, 156, 20);
		this.getContentPane().add(txtNiaAlum);
		txtNiaAlum.setColumns(10);

		lblNifProf = new JLabel("Introduce el nif del profesor:");
		lblNifProf.setBounds(31, 110, 223, 14);
		this.getContentPane().add(lblNifProf);

		txtNifProf = new JTextField();
		txtNifProf.setBounds(31, 135, 156, 20);
		this.getContentPane().add(txtNifProf);
		txtNifProf.setColumns(10);

		btnAsign = new JButton("Asignar");
		btnAsign.setBounds(293, 106, 130, 23);
		this.getContentPane().add(btnAsign);
		btnAsign.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					Repositorio.asignarAlumnoProfesor(txtNiaAlum.getText(), txtNifProf.getText());
					reset();
					JOptionPane.showMessageDialog(getRootPane(), "Alumno asignado correctamente!");
				} catch (AlumnoException e1) {
					JOptionPane.showMessageDialog(getRootPane(),
							"El alumno de NIA " + txtNiaAlum.getText() + " no existe!");
				} catch (ProfesorException e2) {
					JOptionPane.showMessageDialog(getRootPane(),
							"El profesor de nif " + txtNifProf.getText() + " no existe!");
				} catch (VoidException e3) {
					JOptionPane.showMessageDialog(getRootPane(), e3.getMessage());
				} catch (ExistsException e4) {
					JOptionPane.showMessageDialog(getRootPane(), "Este profesor ya tiene a este alumno!");
				}

			}
		});

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				reset();
			}
		});
		btnVolver.setBounds(300, 219, 107, 23);
		this.getContentPane().add(btnVolver);

		this.setVisible(false);

	}

	protected void reset() {
		txtNiaAlum.setText("");
		txtNifProf.setText("");
	}

}
